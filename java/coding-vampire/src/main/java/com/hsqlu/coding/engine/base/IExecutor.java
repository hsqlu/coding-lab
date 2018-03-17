package com.hsqlu.coding.engine.base;

/**
 * Created: 2016/7/12.
 * Author: Qiannan Lu
 */
public interface IExecutor<LEADER extends IOrganizable<?>, INPUT_DATA, OUTPUT_DATA> {
    void preExecute();

    void execute();

    void postExecute();

    void executeFinally();

    void handleException();

    void setIndex(int index);

    void setOrganizationName(String name);

    ExecutorStatus getStatus();

    void setStatus(ExecutorStatus status);

    void setBranch();

    enum ExecutorStatus {
        EXECUTING,
        INTERRUPT,
        ERROR,
        FINISH
    }
}
