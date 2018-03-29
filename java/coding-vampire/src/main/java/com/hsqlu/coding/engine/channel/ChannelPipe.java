package com.hsqlu.coding.engine.channel;

import org.springframework.util.Assert;

/**
 * Created: 2016/7/12.
 * Author: Qiannan Lu
 */
public class ChannelPipe<LEADER extends ChannelPipe<?, ?, ?>, THIS extends ChannelPipe<?, ?, ?>, LOWER extends AbstractChannelHandler<THIS, ?>>
        extends AbstractChannelHandler<LEADER, LOWER>
        implements IChannelPipe<LEADER, LOWER> {

    public ChannelPipe(String organizationName) {
        super(organizationName);
    }

    @Override
    public void addLower(LOWER handler) {
        Assert.notNull(handler, "lower can't be empty.");
        handler.setLeader((THIS) this);
        handler.setIndex(lowers.size());
        lowers.put(handler.getOrganizationName(), handler);
    }

    @Override
    public void endingWork() {

    }
}
