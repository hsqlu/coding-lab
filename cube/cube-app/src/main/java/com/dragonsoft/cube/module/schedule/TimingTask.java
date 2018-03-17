package com.dragonsoft.cube.module.schedule;

import com.sun.tools.javac.util.List;
import org.quartz.JobDetail;
import org.quartz.JobListener;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

/**
 * Created: 20/09/2016.
 * Author: Qiannan Lu
 */
public class TimingTask implements ITask {
	@Override
	public JobDetail getJob() {
		return null;
	}

	@Override
	public Trigger getTrigger() {
		return null;
	}

	@Override
	public List<JobListener> getJobListener() {
		return null;
	}

	@Override
	public List<TriggerListener> getTriggerListener() {
		return null;
	}
}
