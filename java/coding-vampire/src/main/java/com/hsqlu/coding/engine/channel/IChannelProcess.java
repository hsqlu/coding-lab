package com.hsqlu.coding.engine.channel;

import com.hsqlu.coding.engine.base.IExecutor;
import com.hsqlu.coding.engine.data.IDataBranch;
import com.hsqlu.coding.engine.data.IDataChannel;
import com.hsqlu.coding.engine.data.Routable;

/**
 * Created: 2016/7/7.
 * Author: Qiannan Lu
 */
public interface IChannelProcess<
        LEADER extends  IChannelPipe<?, ?>,
        INPUT_DATA extends Routable,
        OUTPUT_DATA extends Routable,
        EXECUTOR extends IExecutor<?, INPUT_DATA, OUTPUT_DATA>> {
    void addExecutor(EXECUTOR... executors);

    IDataBranch<?> getInputBranch();

    void setInputBranch(IDataBranch<INPUT_DATA> inputBranch);

    IDataChannel<OUTPUT_DATA> getOutputChannel();

    void setOutputBranch(IDataBranch<OUTPUT_DATA> outputBranch);
}
