package com.hsqlu.coding.batch;

/**
 * Created: 2016/5/6.
 * Author: Qiannan Lu
 */
public interface IExecute<LEADER extends IOrganizable<?>, INPUT_DATA, OUTPUT_DATA> {
    void preExec() throws Throwable;

    void execWork() throws Throwable;

    void lastWork() throws Throwable;

    void finallyExec(Throwable e) throws Throwable;

    void exceptionHandle() throws Throwable;


    void setIndex(int index);

    void setOrgName(String orgName);

    EnumExecStatus getStatus();

    void setStatus(EnumExecStatus status);

    void setBranch(IDataBranch<INPUT_DATA> inputBranch);

    void setOutputChannel(IDataChannel<OUTPUT_DATA> outputChannel);

    ClassLoader getLoader();

    LEADER getLeader();

    enum EnumExecStatus{
        ING,
        INTERRUPT,
        ERROR,
        FINISH
    }

}
