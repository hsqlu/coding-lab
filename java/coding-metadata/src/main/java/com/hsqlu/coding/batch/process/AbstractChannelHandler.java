package com.hsqlu.coding.batch.process;

import com.google.common.collect.Maps;
import com.hsqlu.coding.batch.AwaitNotifier;
import com.hsqlu.coding.batch.ExecuteMessage;
import com.hsqlu.coding.batch.IChannelHandler;
import com.hsqlu.coding.batch.OrganizationState;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created: 2016/5/6.
 * Author: Qiannan Lu
 */
public abstract class AbstractChannelHandler<LEADER extends AbstractChannelHandler<?, ?>, LOWER extends AbstractOrganizeAble<?>>
        extends AbstractOrganizeAble<LEADER>
        implements IChannelHandler<LEADER, LOWER> {
    public AbstractChannelHandler(String name) {
        super(name);
        this.organizationType = "作业";
    }

    protected LinkedHashMap<String, LOWER> lowers = Maps.newLinkedHashMap();

    public LOWER getLowerBy(String name) {
        return lowers.get(name);
    }

    public Collection<LOWER> getLowers() {
        return lowers.values();
    }

    protected void startLowerWorking() {
        for (LOWER lower : lowers.values()) {
            LOGGER.info("【" + lower.getOrganizationName() + "】开始启动生产！");
            lower.setOrganizationLevel(organizationLevel + 1);
            lower.startWorking();
        }
    }

    @Override
    protected void preExecute() {
        LOGGER.info(getOrganizationName() + "初始化工作开始！");
        this.startTime = System.nanoTime();
        this.endTime = System.nanoTime();

        notifyListener(true, OrganizationState.INITIALIZED, "", null);

        lowers.values().forEach(lower -> {
            try {
                lower.preExecute();
            } catch (Throwable ignored) {
            }
        });

        notifier = new AwaitNotifier<>(organizationName, new ChannelHandlerAwaitNotifyToDo());
        lowers.values().forEach(lower -> notifier.addAwaitNotifier(lower.getOrganizationName()));
        notifier.init();
        LOGGER.info("【" + organizationName + "】需要等待[" + notifier.getNotifiersSize() + "]个通知工作结束通知！");
        LOGGER.info(organizationName + "初始化工作结束!");
    }

    @Override
    protected void assertExecute() {
        Assert.notEmpty(lowers, organizationName + "执行器集合不允许为空");
    }

    @Override
    public void run() {
        notifyListener(true, OrganizationState.EXECUTING, "【" + organizationName + "】准备开始运行", null);
        LOGGER.info("【" + organizationName + "】准备开始运行");
        startLowerWorking();
        notifier.awaitNotify();
        LOGGER.info("【" + organizationName + "】生产工作结束,开始收尾工作");
        endingWork();
        this.endTime = System.nanoTime();
        if (finished.isError()) {
            finished.finished();
        } else {
            finished.finished(" 生产工作结束,收尾工作结束,耗时: " + (endTime - startTime));
        }
        String message = " 生产工作结束,收尾工作结束,耗时: " + (endTime - startTime);

        if (OrganizationState.ERROR.equals(state)) {
            notifyListener(true, OrganizationState.ERROR, finished.getMessage(), null);
        } else {
            notifyListener(true, OrganizationState.FINISHED, message, null);
        }

        if (leader != null) {//最顶层领导没有领导
            leader.getNotifier().notify(getOrganizationName(), ExecuteMessage.finishMessage(organizationName, organizationLevel));
        }
    }

    @Override
    public void stopWorking() {
        if (!finished.isError()) {
            finished.setError(false);
            finished.setMessage("取消正在执行的工作！");
        }

        notifyListener(true, OrganizationState.STOPPING, "取消正在执行的工作！", null);
        notifyToLowers(ExecuteMessage.stoppingMessage(organizationName, organizationLevel, "取消正在执行的工作！"));
    }

    protected abstract void endingWork();

    private void notifyToLowers(ExecuteMessage message) {
        lowers.values()
                .stream()
                .filter(lower -> !lower.isFinished())
                .forEach(lower -> lower.getNotifier().notify(organizationName, message));
    }

    private class ChannelHandlerAwaitNotifyToDo implements AwaitNotifier.AwaitNotifyToDo<String, ExecuteMessage> {
        @Override
        public void notifyDoSth(String notifier, ExecuteMessage message) {
            switch (message.getMessageType()) {
                case FINISHED:
                    break;
                case ERROR:
                    state = OrganizationState.ERROR;
                    if (!finished.isError()) {
                        finished.setError(true);
                        finished.setMessage(message.getMessage());
                    }

                    if (!message.isLeaderMessage(organizationLevel)) {
                        LOGGER.info("【" + message.getSender() + "】已经通知【" + organizationName + "】生产出现异常！");
                        if (leader != null) {
                            leader.getNotifier().notify(organizationName, ExecuteMessage.errorMessage(organizationName, organizationLevel, message.getMessage()));
                        }
                    } else {
                        LOGGER.info("【" + message.getSender() + "】已经通知【" + organizationName + "】生产出现异常，开始通知下属终止生产！");
                    }
                    notifyToLowers(ExecuteMessage.errorMessage(organizationName, organizationLevel, message.getMessage()));
                    break;
                case STOPPING:
                    stopWorking();
                    break;
                default:
                    break;
            }
        }

        @Override
        public void awaitDoSth(Map<String, ExecuteMessage> notifyInfo) {

        }

        @Override
        public void finallyDoSth(Map<String, ExecuteMessage> notifyInfo, boolean isInterrupted) {

        }
    }
}
