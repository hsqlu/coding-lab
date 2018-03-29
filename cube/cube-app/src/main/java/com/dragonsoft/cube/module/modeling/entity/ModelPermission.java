package com.dragonsoft.cube.module.modeling.entity;

import com.dragonsoft.cube.common.constant.IDGenerator;
import com.dragonsoft.cube.common.entity.BizEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created: 2016/9/7.
 * Author: Qiannan Lu
 */
@Entity
@Table(name = "T_MODEL_PERMISSION")
@GenericGenerator(name = "id", strategy = IDGenerator.IDENTITY)
public class ModelPermission extends BizEntity<Long> {
	private String modelId;
	private String permissionName;
	private String permissionValue;

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermissionValue() {
		return permissionValue;
	}

	public void setPermissionValue(String permissionValue) {
		this.permissionValue = permissionValue;
	}
}
