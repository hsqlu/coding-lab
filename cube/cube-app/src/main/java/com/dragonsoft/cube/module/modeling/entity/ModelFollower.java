package com.dragonsoft.cube.module.modeling.entity;

import com.dragonsoft.cube.common.constant.IDGenerator;
import com.dragonsoft.cube.common.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created: 2016/9/6.
 * Author: Qiannan Lu
 */
@Entity
@Table(name = "T_MODEL_FOLLOWER")
@GenericGenerator(name = "id", strategy = IDGenerator.IDENTITY)
@EntityListeners(AuditingEntityListener.class)
public class ModelFollower extends BaseEntity<Long> {
	private String modelId;
	private String userId;

	@CreatedDate
	@Column(updatable = false)
	private Date followTime;

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getFollowTime() {
		return followTime;
	}

	public void setFollowTime(Date followTime) {
		this.followTime = followTime;
	}
}
