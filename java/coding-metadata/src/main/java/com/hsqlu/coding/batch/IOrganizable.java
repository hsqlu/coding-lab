package com.hsqlu.coding.batch;

import com.hsqlu.coding.batch.OrganizationState;
import com.hsqlu.coding.lifecycle.Lifecycle;

/**
 * Created: 2016/5/6.
 * Author: Qiannan Lu
 */
public interface IOrganizable<LEADER> extends Lifecycle<OrganizationState> {
    int getIndex();

    int getOrganizationLevel();

    String getOrganizationType();

    LEADER getLeader();

    boolean isFinished();

    String getOrganizationName();

    long getEndTime();

    long getStartTime();



}
