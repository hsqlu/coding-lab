package com.hsqlu.coding.batch.exec;

import com.hsqlu.coding.batch.AwaitNotifier;
import com.hsqlu.coding.batch.ExecuteMessage;
import com.hsqlu.coding.batch.OrganizationState;
import com.hsqlu.coding.batch.process.AbstractOrganizeAble;
import com.hsqlu.coding.lifecycle.OutputResult;

/**
 * Created: 2016/5/6.
 * Author: Qiannan Lu
 */
public class BaseExecutor<LEADER extends AbstractOrganizeAble<?>> extends AbstractOrganizeAble<LEADER> {
    private final AbstractExecutor<LEADER,?,?> exec;
    private boolean holdChannel = false;


    public BaseExecutor(int index,String orgName, AbstractExecutor<LEADER,?,?> exec) {
        super(orgName+"-["+index+"]号"+"执行器");
        notifier = new AwaitNotifier<String, ExecuteMessage>(orgName,new ExecutorAwaitNotifyToDo()).init();
        this.organizationType = "执行器";
        this.exec = exec;
        this.exec.setExecutor(this);
        this.exec.setIndex(index);
        this.exec.setOrgName(this.getOrganizationName());
    }

    public void holdChannel(){
        this.holdChannel = true;
    }

    public void unHoldChannel(){
        this.holdChannel = false;
    }

    @Override
    protected void preExecute() throws Throwable {

    }

    @Override
    protected void assertExecute() {

    }

    @Override
    public OutputResult startWorking() {
        return null;
    }

    @Override
    public void stopWorking() {

    }

    @Override
    public OrganizationState curState() {
        return null;
    }
}
