package com.code.common.batch;

public abstract class AbstractOrganizable extends Thread implements IOrganizable {
    protected IGroup leader;

    protected String organizationName;

    protected String organizationType;

    protected int organizationLevel = 0;

    protected ExecutionStatus finished = new ExecutionStatus();

    public synchronized boolean isFinished(){
        return finished.isFinished();
    }

    @Override
    public IGroup getLeader() {
        return leader;
    }

    @Override
    public void setLeader(IGroup leader) {
        this.leader = leader;
    }

    @Override
    public String getOrganizationType() {
        return organizationType;
    }

    @Override
    public String getOrganizationName() {
        return organizationName;
    }

    @Override
    public void setOrganizationName(String organizationName) {
        this.setName(organizationName);
        this.organizationName = organizationName;
    }

    @Override
    public int getOrganizationLevel() {
        return organizationLevel;
    }

    @Override
    public void setOrganizationLevel(int organizationLevel) {
        this.organizationLevel = organizationLevel;
    }
}
