package com.hsqlu.coding.engine.base;

/**
 * Created: 2016/7/7.
 * Author: Qiannan Lu
 */
public interface IOrganizable<LEADER> extends Lifecycle<OrganizationState> {
    int getIndex();

    int getOrganizationLevel();

    String getOrganizationName();

    String getOrganizationType();

    LEADER getLeader();

    long getStartTime();

    long getEndTime();

    boolean isFinished();
}
