package com.hsqlu.coding.batch;

/**
 * Created: 2016/5/6.
 * Author: Qiannan Lu
 */
public interface IDataChannel<DATA> {

    int getChannelCount();

    void addBranch(String branchName, IDataBranch<DATA> branch);

    void putProduct(DATA data) throws InterruptedException;

    void addEndNode(EndNode node);

    DataChannelStatus getStatus();

    void stopFlow();

    enum DataChannelStatus{
        ING_FLOW,//数据通道流通还未结束，不会再有新数据进入
        STOP_FLOW//数据通道流通已经结束，通道里有可能还有数据
    }

    interface EndNode{
        /**
         * 通知下游节点上游节点已经完成 ，数据通道的数据不会再增加了
         * @param count 上游节点总共向通道发送数据条数
         */
        void receiveStopFlow(long count);
    }
}
