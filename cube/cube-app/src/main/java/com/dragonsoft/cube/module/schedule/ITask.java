package com.dragonsoft.cube.module.schedule;

import com.sun.tools.javac.util.List;
import org.quartz.JobDetail;
import org.quartz.JobListener;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

/**
 * Created: 2016/9/12.
 * Author: Qiannan Lu
 */
public interface ITask {
	JobDetail getJob();

	Trigger getTrigger();

	List<JobListener> getJobListener();

	List<TriggerListener> getTriggerListener();
}
