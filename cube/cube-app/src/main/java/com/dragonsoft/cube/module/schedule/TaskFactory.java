package com.dragonsoft.cube.module.schedule;

import com.dragonsoft.cube.module.modeling.entity.ModelTask;

/**
 * Created: 2016/9/14.
 * Author: Qiannan Lu
 */
public class TaskFactory {

	public static ITask buildExecutableTask(ModelTask task) {
		return new TimingTask();
	}
}
