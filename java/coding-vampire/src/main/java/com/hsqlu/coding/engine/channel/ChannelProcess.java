package com.hsqlu.coding.engine.channel;

import com.hsqlu.coding.engine.base.BaseExecutor;

/**
 * Created: 2016/7/12.
 * Author: Qiannan Lu
 */
public class ChannelProcess<LEADER extends ChannelPipe<?, ?, ?>, INPUT_DATA, OUTPUT_DATA, EXECUTOR>
        extends AbstractChannelHandler<LEADER, BaseExecutor<?>>
        implements IChannelProcess<LEADER, INPUT_DATA, OUTPUT_DATA, EXECUTOR> {

    public ChannelProcess(String organizationName) {
        super(organizationName);
    }

    @Override
    public void endingWork() {

    }
}
