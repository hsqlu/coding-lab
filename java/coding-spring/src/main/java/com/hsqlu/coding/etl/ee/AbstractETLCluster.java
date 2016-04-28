package com.hsqlu.coding.etl.ee;

import com.code.metadata.etl.cluster.ETLClusterMeta;
import com.code.metaservice.core.GlobalCfg;
import com.code.metaservice.etl.cluster.ETLClusterMetaService;
import com.code.metaservice.etl.cluster.ETLTaskService;
import com.code.metaservice.etl.trans.TransMetaService;
import com.hsqlu.coding.zookeeper.client.ZookeeperClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created: 2016/4/25.
 * Author: Qiannan Lu
 */
public abstract class AbstractETLCluster {
    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    protected final ReentrantReadWriteLock.ReadLock readLock;
    protected final ReentrantReadWriteLock.WriteLock writeLock;

    public static final String DEF_NAME_SPACE = "ETL";

    public static final String DIR_ETL_ENGINE = "etlEngines";

    public static final String DIR_ETL_CONTROLLER = "etlControllers";

    public static final String DIR_ETL_S_TASKS = "sTasks";

    public static final String DIR_ETL_SCH_TASKS = "schTasks";

    public static final String DIR_ETL_D_TASKS = "dTasks";

    @Value("${zookeeper.address}")
    protected String zkAddress;

    @Value("${namespace}")
    protected String nameSpace;

    @Value("${address}")
    protected String address;

    @Value("${port}")
    protected int port;

    @Value("${name}")
    protected String name;

    @Autowired
    protected ETLClusterMeta _instance;

    @Autowired
    protected ETLClusterMetaService etlClusterMetaService;

    @Autowired
    protected TransMetaService transMetaService;

    @Autowired
    protected ETLTaskService etlTaskService;

    protected ZookeeperClient zookeeperClient;

    protected GlobalCfg globalConfig;

    public AbstractETLCluster() {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        this.readLock = lock.readLock();
        this.writeLock = lock.writeLock();
    }

    protected void stateLog(String msg){
        etlClusterMetaService._logExec(_instance, msg);
    }

    public abstract String getBName();

    public String getZkAddress() {
        return zkAddress;
    }

    public void setZkAddress(String zkAddress) {
        this.zkAddress = zkAddress;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
