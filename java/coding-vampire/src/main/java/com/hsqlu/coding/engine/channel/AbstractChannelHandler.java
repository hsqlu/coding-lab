package com.hsqlu.coding.engine.channel;

import com.google.common.collect.Maps;
import com.hsqlu.coding.concurrent.AwaitNotifier;
import com.hsqlu.coding.engine.base.AbstractOrganizable;
import com.hsqlu.coding.engine.base.ExecuteMessage;
import com.hsqlu.coding.engine.base.OrganizationState;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created: 2016/7/12.
 * Author: Qiannan Lu
 */
public abstract class AbstractChannelHandler<LEADER extends AbstractChannelHandler<?, ?>, LOWER extends AbstractOrganizable<?>>
        extends AbstractOrganizable<LEADER>
        implements IChannelHandler<LEADER, LOWER> {
    private static final String DEFAULT_ORGANIZATION_TYPE = "JOB";
    //多个执行器，用于多线程处理
    protected LinkedHashMap<String, LOWER> lowers = Maps.newLinkedHashMap();

    public AbstractChannelHandler(String organizationName) {
        super(organizationName);
        this.setOrganizationType(DEFAULT_ORGANIZATION_TYPE);
    }

    protected void startLowersWork() {
        Assert.isTrue(!lowers.isEmpty(), DEFAULT_ORGANIZATION_TYPE + " item can't without lowers");
        lowers.values().forEach(lower -> {
            LOGGER.info("[{}] started producing.", lower.getOrganizationName());
            lower.setOrganizationLevel(getOrganizationLevel() + 1);
            lower.startWork();
        });
    }

    @Override
    protected void assertExecute() {
        Assert.notEmpty(lowers, getOrganizationName() + " execute machine shouldn't empty.");
    }

    @Override
    public void preExecute() {
        String organizationName = getOrganizationName();
        LOGGER.info(organizationName + " initialization started!");
        this.startTime = System.nanoTime();
        this.endTime = System.nanoTime();
        notifyListener(false, OrganizationState.INITIALIZED, organizationName + " initialization finished!", null);
        for (LOWER lower : lowers.values()) {
            try {
                lower.preExecute();
            } catch (Throwable e) {
                LOGGER.error("Exception thrown while processing lower job.", e);
            }
        }
        notifier = new AwaitNotifier<>(organizationName, new ChannelHandlerAwaitNotifyToDo());
        for (LOWER organizeAble : lowers.values()) {
            notifier.addAwaitNotifier(organizeAble.getOrganizationName());
        }
        notifier.init();
        LOGGER.info("[{}] still have [{}] jobs to notify. ", organizationName, notifier.getNotifiersCount());
        LOGGER.info(organizationName + " initialization finished!");
    }

    @Override
    public Collection<LOWER> getLowers() {
        return lowers.values();
    }

    @Override
    public void stopWork() {
        if (!operateResult.isError()) {
            operateResult.setError(false);
            operateResult.setMessage("Cancel the job processing.");
        }
        notifyListener(false, OrganizationState.STOPPING, "Cancel the job processing.", null);
        notifyLowers(ExecuteMessage.stoppingMessage(getOrganizationName(), getOrganizationLevel(), "Cancel the job processing."));
    }

    public abstract void endingWork();

    public LOWER getLower(String name){
        return lowers.get(name);
    }

    private void notifyLowers(ExecuteMessage message) {
        lowers.values().stream()
                .filter(lower -> !lower.isFinished())
                .forEach(lower -> {
                    lower.getNotifier().notify(getOrganizationName(), message);
                });
    }

    private class ChannelHandlerAwaitNotifyToDo implements AwaitNotifier.AwaitNotifyToDo<String, ExecuteMessage> {

        @Override
        public void notifyDoSomething(String notifier, ExecuteMessage notifyInfo) {
            switch (notifyInfo.getMessageType()) {
                case FINISHED:
                    break;
                case ERROR:
                    state = OrganizationState.ERROR;
                    if (!operateResult.isError()) {
                        operateResult.setError(true);
                        operateResult.setMessage(notifyInfo.getMessage());

                    }
                    if (!notifyInfo.isLeaderMessage(getOrganizationLevel())) {//说明是下面的工人发送的错误消息，则通知工厂
                        LOGGER.info("[{}] has notified [{}] threw exception while producing!", notifyInfo.getSender(), getOrganizationName());
                        if (leader != null) {
                            leader.getNotifier().notify(getOrganizationName(),
                                    ExecuteMessage.errorMessage(
                                            getOrganizationName(),
                                            getOrganizationLevel(),
                                            notifyInfo.getMessage())
                            );
                        }
                    } else {
                        LOGGER.info("[{}] has notified [{}] threw exception while producing, and notified lowers to stop producing！", notifyInfo.getSender(), getOrganizationName());
                    }
                    notifyLowers(ExecuteMessage.errorMessage(getOrganizationName(), getOrganizationLevel(), notifyInfo.getMessage()));
                    break;
                case STOPPING:
                    stopWork();
                    break;
                default:
                    break;
            }
        }

        @Override
        public void awaitDoSomething(Map<String, ExecuteMessage> notifyInfo) {

        }

        @Override
        public void finallyDoSomething(Map<String, ExecuteMessage> notifyInfo, boolean isInterrupted) {

        }
    }
}
