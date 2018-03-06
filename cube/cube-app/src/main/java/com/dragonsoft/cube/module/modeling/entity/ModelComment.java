package com.dragonsoft.cube.module.modeling.entity;

import com.dragonsoft.cube.common.constant.IDGenerator;
import com.dragonsoft.cube.common.entity.BizEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created: 2016/9/12.
 * Author: Qiannan Lu
 */
@Entity
@Table(name = "T_MODEL_COMMENT")
@GenericGenerator(name = "id", strategy = IDGenerator.IDENTITY)
public class ModelComment extends BizEntity<Long> {
	private String modelId;

	private String content;

	@Column(updatable = false)
	private String createBy;

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
}
