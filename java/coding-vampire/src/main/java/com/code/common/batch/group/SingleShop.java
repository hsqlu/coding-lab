package com.code.common.batch.group;

import com.code.common.batch.IGroup;
import com.code.common.batch.IOrganizable;
import com.code.common.batch.message.CommunicationMessage;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;

public class SingleShop extends ParallelGroup {
    private static final Logger LOGGER = LoggerFactory.getLogger(SingleShop.class);

    public SingleShop(IGroup leader, String organizationName, int queueSize) {
        super(organizationName);
        Preconditions.checkState(queueSize > 0, "缓存大小必须大于0");
        Preconditions.checkNotNull(leader, "领导不允许为空");
        this.leader = leader;
        this.cacheQueuesSize = queueSize;
    }

    /**
     * 初始化管道,根据工人数初始化管道数
     */
    @SuppressWarnings("unchecked")
    public void init(){
        LOGGER.info("[{0}]初始化[{1}]个管道", organizationName, this.lowerCount());
        this.cacheQueues = new ArrayBlockingQueue[this.lowers.size()];
        for (int i = 0; i < this.lowers.size(); i++) {
            this.cacheQueues[i] = new ArrayBlockingQueue<Object>(this.cacheQueuesSize);
        }
    }


    @Override
    public void receiveFinish(CommunicationMessage finishMessage) {
        //最后一个工人执行完成,并且没有错误发生
        if(finishCount.getCount() == 1 && !finished.isError()){
            try {
                //最后一个车间的nextGroup为null
                if(this.next != null){
                    this.next.putFinishSign(finishMessage);
                }
            } catch (Exception e) {
                LOGGER.error("[{}]发布完成标记时被中断!", organizationName, e);
            }
        }
        //释放一个主线程的等待条件
        finishCount.countDown();
        LOGGER.info("[{0}]工作已经完成，[{1}]还剩[{2}]个工人没完成工作!",
                finishMessage.getSender(),
                organizationName,
                this.finishCount.getCount());
    }

    public void receiveFinishOnHand(CommunicationMessage finishMessage){
        //通知下一个车间上游车间已经工作完成
        lowers.stream().filter(lower -> !lower.isFinished()).forEach(lower -> {
            LOGGER.info("[{0}]开始通知下级[{1}]尽快完成剩余生产!", this.organizationName, lower.getOrganizationName());
            lower.receiveFinishOnHand(CommunicationMessage.finishMessage(this.organizationName, this.organizationLevel));
        });
    }
}
