package com.dragonsoft.cube.module.modeling.entity;

import com.dragonsoft.cube.common.constant.IDGenerator;
import com.dragonsoft.cube.common.entity.BizEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created: 2016/9/7.
 * Author: Qiannan Lu
 */
@Entity
@Table(name = "T_MODEL")
@GenericGenerator(name = "id", strategy = IDGenerator.UUID)
public class Model extends BizEntity<String> {
	private String name;
	private int type;
	private String parentId;
	private String context;

	private String updateBy;

	@Column(updatable = false)
	private String createBy;

	private boolean publish;
	private boolean enable;

	private int version;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public boolean isPublish() {
		return publish;
	}

	public void setPublish(boolean publish) {
		this.publish = publish;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
