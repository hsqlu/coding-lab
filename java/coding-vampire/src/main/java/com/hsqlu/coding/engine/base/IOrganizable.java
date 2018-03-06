package com.hsqlu.coding.engine.base;

import com.code.common.batch.IGroup;

/**
 * Created: 2016/7/7.
 * Author: Qiannan Lu
 */
public interface IOrganizable {
    int getIndex();

    int getOrganizationLevel();

    String getOrganizationName();

    String getOrganizationType();

    IGroup getLeader();

    void setLeader(IGroup leader);

    long getStartTime();

    long getEndTime();

    boolean isFinished();
}
