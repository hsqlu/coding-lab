package com.dragonsoft.cube.module.schedule;

import com.dragonsoft.cube.module.modeling.entity.ModelTask;
import org.aspectj.lang.annotation.Pointcut;
import org.quartz.*;
import org.quartz.impl.matchers.KeyMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created: 2016/9/5.
 * Author: Qiannan Lu
 */
@Component
public class ScheduleHolder {

	@Autowired
	private Scheduler scheduler;

	private void scheduleJob(ModelTask task) {
		this.scheduleJob(buildTask(task));
	}

	private void scheduleJob(ITask taskJob) {
		JobDetail job = taskJob.getJob();
		Trigger trigger = taskJob.getTrigger();
		try {
			for (JobListener jobListener : taskJob.getJobListener()) {
				scheduler.getListenerManager().addJobListener(jobListener,  KeyMatcher.keyEquals(job.getKey()));
			}
			for (TriggerListener triggerListener : taskJob.getTriggerListener()) {
				scheduler.getListenerManager().addTriggerListener(triggerListener, KeyMatcher.keyEquals(trigger.getKey()));
			}
			if (scheduler.isInStandbyMode() || !scheduler.isStarted()) {
				scheduler.start();
			}
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			try {
				scheduler.deleteJob(job.getKey());
			} catch (SchedulerException ex) {
				throw new IllegalStateException(ex.getMessage(), ex);
			}
			throw new IllegalStateException(e.getMessage(), e);
		}
	}

	public ITask buildTask(ModelTask task) {
		return TaskFactory.buildExecutableTask(task);
	}
}
