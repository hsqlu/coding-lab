package com.dragonsoft.cube.module.admin.entity;

import com.dragonsoft.cube.common.constant.IDGenerator;
import com.dragonsoft.cube.common.entity.BizEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created: 2016/9/12.
 * Author: Qiannan Lu
 */
@Entity
@Table(name = "T_CATEGORY")
@GenericGenerator(name = "id", strategy = IDGenerator.IDENTITY)
public class Category extends BizEntity<Long> {
	private String name;
	private String type;
	private String description;

//	@OneToOne(cascade = CascadeType.REFRESH)
//	@JoinColumn(name = "parent_id")
//	private Category parent;

	private String createBy;
	private String updateBy;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public Category getParent() {
//		return parent;
//	}
//
//	public void setParent(Category parent) {
//		this.parent = parent;
//	}

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
