package com.hsqlu.coding.etl.ee;

import com.code.common.comm.RecvHandler;
import com.code.common.comm.impl.TSMQClient;
import com.code.common.log.LogUtil;
import com.code.common.utils.io.SerializableMsgCodec;
import com.code.metadata.etl.cluster.ETLClusterMeta;
import com.code.metadata.etl.cluster.ETLControlCenterState;
import com.code.metadata.etl.cluster.ETLExecEngineState;
import com.code.metadata.etl.cluster.ETLTask;
import com.hsqlu.coding.zookeeper.client.CuratorZookeeperClient;
import com.hsqlu.coding.zookeeper.client.ZookeeperClient;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created: 2016/4/25.
 * Author: Qiannan Lu
 */
@Component
public class ExecEngineImpl extends AbstractETLCluster implements IExecEngine {

    @Value("${etl.instance.code}")
    private String etlInstanceCode;

    @Value("${local.port}")
    private int localPort;

    private int index = -1;

    private int weight = 0;

    private TSMQClient<ETLTask> messageClient;

    @Override
    public void start() {
        try {
            LOGGER.debug(getBName() + "启动....");
            nameSpace = DEF_NAME_SPACE + "/" + etlInstanceCode;
            _instance = etlClusterMetaService.getOrSaveExecEngine(etlInstanceCode, name, false, index, weight, address, localPort);
            this.etlTaskService._reviseErrorLog(_instance);
            _instance.setCurState(ETLControlCenterState.DISABLED.name());
            zookeeperClient = new CuratorZookeeperClient();
            zookeeperClient.start(zkAddress + ":" + globalConfig.getZkClientPort(), nameSpace, false, new ETLEngineConnStateListener());
        } catch (Throwable e) {
            LOGGER.error(e.getMessage());
            changeState(ETLExecEngineState.DESTORY, getBName() + "启动异常，异常消息[" + e.getLocalizedMessage() + "]");
            zookeeperClient.close();
            throw new ETLClusterException(LogUtil.exceptionMsg(getBName() + "启动异常", e), e);
        }
    }

    private void initEngineInfo() {
        try {

            changeState(ETLExecEngineState.CONTROLL, getBName() + "向控制中心发起受控请求");
            if (zookeeperClient.existNode(getSelfPath())) {
                zookeeperClient.deleteNode(getSelfPath(), true);
            }
            zookeeperClient.createNode(getSelfPath(), CreateMode.EPHEMERAL, true, SerializableMsgCodec.encode(_instance));
            zookeeperClient.addNodeChangedListener(getSelfPath(), new SelfStateListener());
            zookeeperClient.addChildNodeListener(DIR_ETL_CONTROLLER, new ControllerStateListener());
            LOGGER.debug(_instance.getInfo() + "启动完毕....");
        } catch (Throwable e) {
            e.printStackTrace();
            LOGGER.error(LogUtil.exceptionMsg(name + "启动异常！", e), e);
            zookeeperClient.close();
            changeState(ETLExecEngineState.DESTORY, LogUtil.exceptionMsg(name + "启动异常！", e));
        }
    }

    private void changeState(ETLExecEngineState destroy, String s) {

    }

    private class ControllerStateListener implements PathChildrenCacheListener {
        @Override
        public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent event) throws Exception {
            if (LOGGER.isDebugEnabled()) {
                if (event.getData() != null) {
                    LOGGER.debug(event.getType().name() + " " + event.getData().getPath());
                } else {
                    LOGGER.debug(event.getType().name());
                }
            }
            switch (event.getType()) {
                case CHILD_REMOVED:
                    changeState(ETLExecEngineState.DETACHED, getBName() + "控制中心出现问题，执行引擎处于托管状态");
                    break;
                default:
                    break;
            }
        }
    }

    private String getSelfPath() {
        return DIR_ETL_ENGINE + "/" + name;
    }

    @Override
    public String getBName() {
        return "【执行引擎[" + index + "]-" + name + "】";
    }

    private class ETLEngineConnStateListener implements ConnectionStateListener {
        @Override
        public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
            LOGGER.debug(name + "连接ZK的状态变为[" + connectionState + "]");
            switch (connectionState) {
                case CONNECTED:
                    stateLog(name + "连接上ZK，正在与控制中心取得联系[" + connectionState + "]");
                    initEngineInfo();
                    break;
                case RECONNECTED:
                    stateLog(name + "重新连接上ZK，正在与控制中心取得联系[" + connectionState + "]");
                    initEngineInfo();
                    break;
                case LOST:
                    changeState(ETLExecEngineState.DETACHED,
                            name + "session丢失，与控制中心失去联系[" + connectionState + "]");
                default:
                    stateLog(name + "连接ZK的状态变为[" + connectionState + "]");
                    break;
            }
        }
    }

    private class SelfStateListener implements ZookeeperClient.NodeChangedListener {
        @Override
        public void nodeChanged(Stat stat, byte[] bytes) {


            if (stat != null) {
                ETLClusterMeta clusterMeta = (ETLClusterMeta) SerializableMsgCodec.decode(bytes);
                if (clusterMeta != null) {
                    _instance = clusterMeta;
                }

                ETLExecEngineState execEngineState = ETLExecEngineState.valueOf(clusterMeta.getCurState());
                switch (execEngineState) {
                    case CONTROLLED://接受到控制中心受控回复
                        LOGGER.debug(_instance.getInfo() + "收到受控确认请求，");
                        _instance.setClusterIndex(clusterMeta.getClusterIndex());
                        index = _instance.getClusterIndex();
                        _instance.setCurState(ETLExecEngineState.CONTROLLED.name());
                        /*if(messageClient == null){
                            messageClient = new TSMQClient<>(getName(), getZkAddress(), getPort(), getLocalPort(), 200,true);
                            messageClient.setMaxHeartBeatDelay(3);
                            messageClient.setHeartbeatSchedule(150);
                            messageClient.setRecvHandler(new RecvHandler<ETLTask>() {
                                @Override
                                public void exec(ETLTask task) {
                                    if(task != null){
                                        LOGGER.debug("收到消息:"+task.getExecStatus()+"->"+task.getInfo());
                                    }else{
                                        LOGGER.debug("收到空消息！");
                                    }
                                    ExecTaskContainer container = _taskContainers.get(task.getTaskContainerIndex());
                                    if(container != null){
                                        container.receiveTask(task);
                                    }else{
                                        LOGGER.debug("收到空消息！");
                                    }
                                }
                            });
                            messageClient.start();
                        }




                        if(_taskContainers.isEmpty()){
                            //根据现在空闲容器的多少来发命令
                            for (int i = 0; i < weight; i++) {
                                ExecTaskContainer container = beanFactory.getBean("taskContainer", ExecTaskContainer.class);
                                container.setExecStrategy(beanFactory.getBean("taskExecStrategy", AbstractTaskExecStrategy.class));
                                container.setExecEngine(getThis());
                                container.setIndex(i);
                                _taskContainers.put(container.getIndex(),container);
                                container.reInit(_msgSender);
                                container.applyTask();
                            }
                            _instance.setWeight(weight);
                            _instance.setCurWeight(_curWeight.get());
                            etlClusterMetaService._update(_instance);
                        }else{
                            for (ExecTaskContainer container : _taskContainers.values()) {
                                container.reInit(_msgSender);
                                if(container.isFree()){
                                    container.applyTask();
                                }
                            }
                        }*/
                        break;
                    case DETACHED:
                        _instance = clusterMeta;
                        index = -1;
                        break;
                }
            }
        }
    }

    public int getLocalPort() {
        return localPort;
    }

    public void setLocalPort(int localPort) {
        this.localPort = localPort;
    }
}
