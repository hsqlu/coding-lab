package com.hsqlu.coding.engine.channel;

import com.hsqlu.coding.engine.base.IOrganizable;

import java.util.Collection;

/**
 * Created: 2016/7/7.
 * Author: Qiannan Lu
 */
public interface IChannelHandler<LEADER, LOWER> extends IOrganizable<LEADER> {
    Collection<LOWER> getLowers();
}
