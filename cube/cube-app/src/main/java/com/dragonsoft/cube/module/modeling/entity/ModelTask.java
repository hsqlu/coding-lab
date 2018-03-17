package com.dragonsoft.cube.module.modeling.entity;

import com.dragonsoft.cube.common.constant.IDGenerator;
import com.dragonsoft.cube.common.entity.BizEntity;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created: 2016/9/7.
 * Author: Qiannan Lu
 */
@Entity
@Table(name = "T_MODEL_TASK")
@GenericGenerator(name = "id", strategy = IDGenerator.IDENTITY)
public class ModelTask extends BizEntity<Long> {

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "model_id")
	private Model model;

	private String name;

	private String description;

	private int status;

	private int type;

	private int scheduleType;

	private int scheduleInterval;

	private String cronExpression;

	private String createBy;

	private String updateBy;

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(int scheduleType) {
		this.scheduleType = scheduleType;
	}

	public int getScheduleInterval() {
		return scheduleInterval;
	}

	public void setScheduleInterval(int scheduleInterval) {
		this.scheduleInterval = scheduleInterval;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
}