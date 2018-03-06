package com.code.common.batch;

import com.code.common.batch.message.CommunicationMessage;

public interface IGroup extends IOrganizable {

    void receiveFinish(CommunicationMessage finishMessage);

    int lowerCount();

    /**
     * 从第1个队列里返回队列最前面的一条数据不弹出
     */
    Object peek();

    boolean isEmpty();

    // TODO: 14/06/2017 rename to more understandable
    boolean isRawResEmpty(int index);

    Object takeRawRes(int index) throws InterruptedException;

    void putProduct(Object t) throws InterruptedException;

    /**
     * 结束时增加结束标记
     */
    void putFinishSign(CommunicationMessage finishSign) throws InterruptedException;

    void setNextGroup(IGroup group);

    IGroup getNextGroup();
}
