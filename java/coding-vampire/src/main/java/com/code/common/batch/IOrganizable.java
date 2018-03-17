package com.code.common.batch;

import com.code.common.batch.message.CommunicationMessage;

public interface IOrganizable {
    int getIndex();

    void setIndex(int index);

    int getOrganizationLevel();

    void setOrganizationLevel(int organizationLevel);

    String getOrganizationName();

    void setOrganizationName(String organizationName);

    IGroup getLeader();

    void setLeader(IGroup leader);

    boolean isFinished();

    String getOrganizationType();

    ExecutionStatus startWorking();

    public void receiveError(CommunicationMessage errorMessage);

    public void receiveFinishOnHand(CommunicationMessage finishMessage);
}
