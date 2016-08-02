package com.hsqlu.coding.engine.channel;

/**
 * Created: 2016/7/12.
 * Author: Qiannan Lu
 */
public interface IChannelPipe<LEADER extends IChannelPipe<?, ?>, LOWER extends IChannelHandler<?, ?>>
        extends IChannelHandler<LEADER, LOWER> {
    void addLower(LOWER handler);

    LOWER getLower(String name);
}
