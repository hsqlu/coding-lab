package com.dragonsoft.cube.module.label.entity;

import com.dragonsoft.cube.common.constant.IDGenerator;
import com.dragonsoft.cube.common.entity.BizEntity;
import com.sun.tools.javac.util.List;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created: 2016/9/2.
 * Author: Qiannan Lu
 */
@Entity
@Table(name = "T_LABEL")
@GenericGenerator(name = "id", strategy = IDGenerator.IDENTITY)
public class Label extends BizEntity<Long> {
	private String name;
	private String code;
	private String memo;
	private String category;
	private String type;

//	@OneToMany(cascade = CascadeType.REFRESH)
//	private List<LabelItem> items;

	@Column(name = "create_by")
	private String createBy;
	@Column(name = "update_by")
	private String updateBy;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
