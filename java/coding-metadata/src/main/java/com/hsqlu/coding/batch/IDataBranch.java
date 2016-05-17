package com.hsqlu.coding.batch;

/**
 * Created: 2016/5/6.
 * Author: Qiannan Lu
 */
public interface IDataBranch<DATA> {
    int getChannelCount();

    String getName();

    int addQueue();

    boolean isEmpty(int index);

    boolean isEmpty();

    DATA peek();

    DATA take(int index) throws InterruptedException;

    DATA take() throws InterruptedException;

    boolean existData();

    boolean existData(int index);

    void putProduct(DATA data) throws InterruptedException;

    void setEndNode(IDataChannel.EndNode node);

    void addStartNode();

    IDataChannel.DataChannelStatus getStatus();

    void stopFlow();
}
