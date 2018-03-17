package com.dragonsoft.cube.module.schedule;

import com.dragonsoft.cube.module.modeling.entity.ModelTask;
import com.sun.tools.javac.util.List;
import org.quartz.*;

/**
 * Created: 2016/9/12.
 * Author: Qiannan Lu
 */
public class TaskJob extends AbstractJob implements ITask {
	public JobDetail job;
	public Trigger trigger;
	public List<JobListener> jobListener;
	public List<TriggerListener> triggerListener;

	@Override
	public JobDetail getJob() {
		return job;
	}

	public void setJob(JobDetail job) {
		this.job = job;
	}

	@Override
	public Trigger getTrigger() {
		return trigger;
	}

	public void setTrigger(Trigger trigger) {
		this.trigger = trigger;
	}

	@Override
	public List<JobListener> getJobListener() {
		return jobListener;
	}

	public void setJobListener(List<JobListener> jobListener) {
		this.jobListener = jobListener;
	}

	@Override
	public List<TriggerListener> getTriggerListener() {
		return triggerListener;
	}

	public void setTriggerListener(List<TriggerListener> triggerListener) {
		this.triggerListener = triggerListener;
	}


	@Override
	public String getTaskId() {
		return null;
	}
}
