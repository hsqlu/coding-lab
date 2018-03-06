package com.code.common.batch.group;

import com.code.common.batch.AbstractOrganizable;
import com.code.common.batch.ExecutionStatus;
import com.code.common.batch.IGroup;
import com.code.common.batch.message.CommunicationMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public abstract class AbstractGroup extends AbstractOrganizable implements IGroup {
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractGroup.class);

    private int index;

    protected IGroup next;

    protected CountDownLatch finishCount;

    protected ArrayBlockingQueue<Object>[] cacheQueues;

    protected int cacheQueuesSize;

    private ReentrantLock lock = new ReentrantLock();

    /**
     * todo what's the n means?
     */
    private int n;

    private int indexQueue(int n, int count) {
        return n < count ? n : 0;
    }

    public AbstractGroup(String organizationName) {
        this.organizationType = "车间";
        this.organizationName = organizationName;
        this.setName(organizationName);
    }

    protected abstract int lowerLength();

    protected abstract void startLowersWorking();

    protected abstract void notifyErrorToLowers(CommunicationMessage errorMessage);

    @Override
    public ExecutionStatus startWorking() {
        start();
        return finished;
    }

    @Override
    public void run() {
        LOGGER.info("[{}]准备开始运行!", organizationName);
        finishCount = new CountDownLatch(lowerLength());
        startLowersWorking();
        try {
            finishCount.await();
        } catch (InterruptedException ignored) {
        }
        LOGGER.info("[{}]生产工作结束!", organizationName);
        if (this.finished.isError()) {
            this.finished.finished();
        } else {
            this.finished.finished("[" + organizationName + "]生产工作结束!");
        }

        //最顶层领导没有领导
        if (leader != null) {
            leader.receiveFinish(CommunicationMessage.finishMessage(this.organizationName, this.organizationLevel));
        }
    }

    /**
     * 结束时增加结束标记
     *
     * @param finishSign 结束标记
     * @throws InterruptedException
     */
    public void putFinishSign(CommunicationMessage finishSign) throws InterruptedException {
        for (ArrayBlockingQueue<Object> queue : this.cacheQueues) {
            queue.put(finishSign);
        }
        LOGGER.info("[{0}]获得{1}个完成标记", organizationName, cacheQueues.length);
    }

    public void putProduct(Object object) throws InterruptedException {
        lock.lock();
        //  循环取索引号
        try {
            this.cacheQueues[n].put(object);
            n = indexQueue(++n, this.cacheQueues.length);
        } finally {
            lock.unlock();
        }
    }

    public void receiveError(CommunicationMessage errorMessage) {
        if (!this.finished.isError()) {
            this.finished.setError(true);
            this.finished.setMessage(errorMessage.getMessage());
        }

        //说明是下面的工人发送的错误消息，则通知工厂
        if (!errorMessage.isLeaderMessage(this.organizationLevel)) {
            LOGGER.info("[{0}]已经通知[{1}]生产出现异常!",
                    errorMessage.getSender(),
                    organizationName);
            if (leader != null) {
                leader.receiveError(CommunicationMessage.errorMessage(
                        this.organizationName,
                        this.organizationLevel,
                        errorMessage.getMessage()));
            }
        } else {
            LOGGER.info("[{0}]已经通知[{1}]生产出现异常，开始通知下属终止生产!",
                    errorMessage.getSender(),
                    organizationName);
        }
        notifyErrorToLowers(CommunicationMessage.errorMessage(this.organizationName, this.organizationLevel, errorMessage.getMessage()));
    }


    public void receiveFinish(CommunicationMessage finishMessage) {
        //释放一个主线程的等待条件
        finishCount.countDown();

        LOGGER.info("[{0}]工作已经完成，[{1}]还剩[{2}]个车间没完成工作!",
                finishMessage.getSender(),
                finishMessage,
                finishCount.getCount());
        receiveFinishOnHand(finishMessage);
    }

    public boolean isRawResEmpty(int index) {
        return this.cacheQueues[index].isEmpty();
    }

    public Object takeRawRes(int index) throws InterruptedException {
        return this.take(index);
    }

    /**
     * 从第1个队列里返回队列最前面的一条数据并弹出
     *
     * @return 数据
     * @throws InterruptedException
     */
    public Object take() throws InterruptedException {
        return this.cacheQueues[0].take();
    }

    /**
     * 从第1个队列里返回队列最前面的一条数据并弹出
     *
     * @return 数据
     * @throws InterruptedException
     */
    public Object take(int index) throws InterruptedException {
        return this.cacheQueues[index].take();
    }

    /**
     * 从第1个队列里返回队列最前面的一条数据不弹出
     */
    public Object peek() {
        return this.cacheQueues[0].peek();
    }

    public boolean isEmpty() {
        return this.cacheQueues[0].isEmpty();
    }

    @Override
    public IGroup getNextGroup() {
        return next;
    }

    public void setNextGroup(IGroup next) {
        this.next = next;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }
}
