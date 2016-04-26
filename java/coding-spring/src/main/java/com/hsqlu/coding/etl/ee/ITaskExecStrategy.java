package com.hsqlu.coding.etl.ee;

import com.code.metadata.etl.cluster.ETLTask;

/**
 * Created: 2016/4/25.
 * Author: Qiannan Lu
 */
public interface ITaskExecStrategy {

    void startTask(ETLTask task) throws Exception;

    void handleResult(ETLTask task);
}
