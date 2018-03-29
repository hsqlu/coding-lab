package com.code.common.batch.worker;

import com.code.common.batch.AbstractOrganizable;
import com.code.common.batch.ExecutionStatus;
import com.code.common.batch.message.CommunicationMessage;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractWorker<E, T> extends AbstractOrganizable {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractWorker.class);

    public static final int STATUS_WORKING = 0;
    public static final int STATUS_FINISH = 1;
    public static final int STATUS_ERROR = 3;

    protected AtomicInteger status = new AtomicInteger(STATUS_WORKING);

    /**
     * 工人编号
     */
    protected int index;

    public AbstractWorker() {
        super();
        this.organizationType = "工人";
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }

    protected abstract void executeWork() throws Exception;

    protected abstract void finalWork() throws Exception;

    protected void exceptionHandle() throws Exception {
    }

    public ExecutionStatus startWorking() {
        this.start();
        return finished;
    }

    //正在执行
    protected boolean working() {
        return this.status.get() == STATUS_WORKING;
    }

    //或者上游车间已经完成，但队列里头还有数据没处理
    protected boolean finishWithRestData() {
        return this.status.get() == STATUS_FINISH && !leader.isRawResEmpty(this.index);
    }

    @Override
    public void run() {
        String message;
        try {
            executeWork();
            message = "[" + getOrganizationName() + "]执行成功!";
            finished.setError(false);
            finished.finished(message);
            LOGGER.info(message);
        } catch (Throwable e) {
            LOGGER.error("执行过程出错", e);
            try {
                exceptionHandle();
            } catch (Exception e2) {
                LOGGER.error("出错执行过程异常处理出错", e);
            }
            message = "[" + getOrganizationName() + "]执行过程中出现异常，异常消息[" + e.toString() + "]";
            finished.setError(true);
            finished.finished(message);
            this.leader.receiveError(CommunicationMessage.errorMessage(this.organizationName, this.organizationLevel, message));
        } finally {
            try {
                this.finalWork();
            } catch (Exception e) {
                LOGGER.error("结束执行过程出错", e);
            }
        }
        this.leader.receiveFinish(CommunicationMessage.finishMessage(this.organizationName, this.organizationLevel));
    }

    public void receiveError(CommunicationMessage errorMsg) {
        LOGGER.info("[{0}]已经通知[{1}]生产出现异常的通知，马上终止手头工作!",
                errorMsg.getSender(),
                organizationName);
        this.status.set(STATUS_ERROR);
        this.interrupt();
    }

    public void receiveFinishOnHand(CommunicationMessage finishMessage) {
        Preconditions.checkState(finishMessage.getOrganizationLevel() < this.organizationLevel, "手头工作结束消息必须由领导发起");
        LOGGER.info("[{0}]已经通知[{1}]尽快完成手头工作!",
                finishMessage.getSender(),
                organizationName);
        this.status.set(STATUS_FINISH);
    }

    public void putProduct(T object) throws InterruptedException{
        if(object != null){
            this.leader.getNextGroup().putProduct(object);
        }
    }
}
