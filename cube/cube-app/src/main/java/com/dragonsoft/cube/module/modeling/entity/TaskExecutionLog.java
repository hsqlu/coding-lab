package com.dragonsoft.cube.module.modeling.entity;

import com.dragonsoft.cube.common.constant.IDGenerator;
import com.dragonsoft.cube.common.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created: 2016/9/7.
 * Author: Qiannan Lu
 */
@Entity
@Table(name = "T_TASK_EXECUTION_LOG")
@GenericGenerator(name = "id", strategy = IDGenerator.IDENTITY)
public class TaskExecutionLog extends BaseEntity<Long> {
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "task_id")
	private ModelTask modelTask;

	private Date beginTime;
	private Date endTime;

	public ModelTask getModelTask() {
		return modelTask;
	}

	public void setModelTask(ModelTask modelTask) {
		this.modelTask = modelTask;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
