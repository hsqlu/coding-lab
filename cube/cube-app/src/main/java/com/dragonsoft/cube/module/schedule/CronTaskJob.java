package com.dragonsoft.cube.module.schedule;

import com.dragonsoft.cube.module.modeling.entity.ModelTask;

/**
 * Created: 2016/9/12.
 * Author: Qiannan Lu
 */
public class CronTaskJob {
	private ModelTask task;

	public ModelTask getTask() {
		return task;
	}

	public void setTask(ModelTask task) {
		this.task = task;
	}
}
