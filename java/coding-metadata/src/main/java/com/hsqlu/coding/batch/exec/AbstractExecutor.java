package com.hsqlu.coding.batch.exec;

import com.hsqlu.coding.batch.IDataBranch;
import com.hsqlu.coding.batch.IDataChannel;
import com.hsqlu.coding.batch.IExecute;
import com.hsqlu.coding.batch.process.AbstractOrganizeAble;

/**
 * Created: 2016/5/6.
 * Author: Qiannan Lu
 */
public abstract class AbstractExecutor<LEADER extends AbstractOrganizeAble<?>, INPUT_DATA, OUTPUT_DATA>
        implements IExecute<LEADER, INPUT_DATA, OUTPUT_DATA> {
    protected int index;

    protected String organizationName;

    protected EnumExecStatus status = EnumExecStatus.ING;

    private IDataChannel<OUTPUT_DATA> outputChannel;

    protected int queueId;

    private IDataBranch<INPUT_DATA> branch;

    protected ClassLoader loader;

    protected LEADER leader;

    private BaseExecutor<?> executor;

    /**启动时间*/
    protected long startTime;
    /**结束时间*/
    protected long endTime;

    public void setBranch(IDataBranch<INPUT_DATA> inputBranch) {
//        Assert.notNull(inputBranch,"输入通道不允许为空！");
        this.branch = inputBranch;
        setQueueId(branch.addQueue());
    }

    public void putProduct(OUTPUT_DATA data) throws InterruptedException{
        try {
            executor.holdChannel();
            if(this.outputChannel != null){
                this.outputChannel.putProduct(data);
            }
        }finally{
            executor.unHoldChannel();
        }

    }

    public INPUT_DATA takeProduct() throws InterruptedException{
        try {
            executor.holdChannel();
            return this.branch.take(this.queueId);
        }finally{
            executor.unHoldChannel();
        }
    }

    public void setExecutor(BaseExecutor<?> executor) {
        this.executor = executor;
    }

    public boolean hasNextChannel(){
        return this.outputChannel != null;
    }


    public boolean isInterrupt(){
        return !EnumExecStatus.ING.equals(this.status);
    }

    public boolean isPreviousChannelEmpty(){
        return this.branch.isEmpty(queueId);
    }

    public IDataChannel.DataChannelStatus getPreviousChannelStatus(){
        return this.branch.getStatus();
    }


    @Override
    public void setOutputChannel(IDataChannel<OUTPUT_DATA> outputChannel) {
        this.outputChannel = outputChannel;
    }

    public void setQueueId(int queueId) {
        this.queueId = queueId;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    @Override
    public EnumExecStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(EnumExecStatus status) {
        this.status = status;
    }

    @Override
    public LEADER getLeader() {
        return leader;
    }

    public void setLeader(LEADER leader) {
        this.leader = leader;
    }

    @Override
    public ClassLoader getLoader() {
        return loader;
    }

    public void setLoader(ClassLoader loader) {
        this.loader = loader;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
