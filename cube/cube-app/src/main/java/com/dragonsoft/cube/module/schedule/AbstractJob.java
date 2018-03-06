package com.dragonsoft.cube.module.schedule;

import com.dragonsoft.cube.common.ApplicationContextHolder;
import com.dragonsoft.cube.module.modeling.flow.FlowProcessor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created: 2016/9/12.
 * Author: Qiannan Lu
 */
public abstract class AbstractJob<T extends FlowProcessor> implements Job {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("TaskExecuteJob...");
		FlowProcessor flowProcessor= ApplicationContextHolder.getApplicationContext().getBean(FlowProcessor.class);

//		String taskId=data.getString(Task.class.getName());
		try {
			flowProcessor.process(getTaskId());
		} catch (Exception e) {
			throw new JobExecutionException();
		}
	}

	public abstract String getTaskId();
}
