package com.hsqlu.coding.batch;

import java.util.Collection;

/**
 * Created: 2016/5/6.
 * Author: Qiannan Lu
 */
public interface IChannelHandler<LEADER, LOWER> extends IOrganizable<LEADER> {

    Collection<LOWER> getLowers();
}
