package com.dragonsoft.cube.module.modeling.entity;

import com.dragonsoft.cube.common.constant.IDGenerator;
import com.dragonsoft.cube.common.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created: 2016/9/7.
 * Author: Qiannan Lu
 */
@Entity
@Table(name = "T_TASK_RESULT")
@GenericGenerator(name = "id", strategy = IDGenerator.IDENTITY)
public class TaskResult extends BaseEntity<Long> {
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "task_id")
	private ModelTask modelTask;

	public ModelTask getModelTask() {
		return modelTask;
	}

	public void setModelTask(ModelTask modelTask) {
		this.modelTask = modelTask;
	}
}
