package com.hsqlu.coding.engine.base;

import com.hsqlu.coding.concurrent.AwaitNotifier;

import java.util.Map;

/**
 * Created: 2016/7/12.
 * Author: Qiannan Lu
 */
public class BaseExecutor<LEADER extends AbstractOrganizable<?>> extends AbstractOrganizable<LEADER> {
    private final AbstractExecutor<LEADER, ?, ?> executor;

    public BaseExecutor(int index, String organizationName, AbstractExecutor<LEADER, ?, ?> executor) {
        super(organizationName + "-[" + index + "] executor.");
        notifier = new AwaitNotifier<>(organizationName, new ExecutorAwaitNotifyToDo()).init();
        this.executor = executor;
        executor.setIndex(index);
        executor.setOrganizationName(getOrganizationName());
    }

    @Override
    protected void assertExecute() {

    }

    @Override
    public void preExecute() {

    }

    @Override
    public void stopWork() {

    }

    public class ExecutorAwaitNotifyToDo implements AwaitNotifier.AwaitNotifyToDo<String, ExecuteMessage> {

        @Override
        public void notifyDoSomething(String notifier, ExecuteMessage notifyInfo) {
            switch (notifyInfo.getMessageType()) {
                case FINISHED:
                    break;
                case ERROR:
                    LOGGER.info("【" + notifyInfo.getSender() + "】已经通知【" + getOrganizationName() + "】生产出现异常的通知，马上终止手头工作！");
                    executor.setStatus(IExecutor.ExecutorStatus.INTERRUPT);
                    interrupt();
                    break;
                case STOPPING:
                    LOGGER.info("【" + notifyInfo.getSender() + "】已经通知【" + getOrganizationName() + "】取消手头工作的通知，马上终止手头工作！");
                    stopWork();
                    executor.setStatus(IExecutor.ExecutorStatus.INTERRUPT);
                    interrupt();
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