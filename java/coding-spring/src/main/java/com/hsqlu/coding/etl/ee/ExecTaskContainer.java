package com.hsqlu.coding.etl.ee;

import com.code.common.comm.impl.TSMQClient;
import com.code.common.eventdispatche.IEventDispatcher;
import com.code.metadata.etl.cluster.ETLTask;
import com.code.metadata.etl.cluster.ETLTaskState;
import com.code.metaservice.etl.cluster.ETLTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created: 2016/4/25.
 * Author: Qiannan Lu
 */
public class ExecTaskContainer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExecTaskContainer.class);

    private int index = 0;

    private AbstractTaskExecStrategy execStrategy;

    private IEventDispatcher<ETLTaskState, ETLTask> _taskExecutor;

    private ExecEngineImpl execEngine;

    public ETLTaskService etlTaskService;

    protected TSMQClient<ETLTask> messageClient;

//    private TransFactory transFactory;
    /**执行状态 0空闲，1繁忙*/
    protected AtomicInteger _state = new AtomicInteger(0);
}
