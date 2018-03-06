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
@Table(name = "T_CATEGORY_ITEM")
@GenericGenerator(name = "id", strategy = IDGenerator.IDENTITY)
public class CategoryItem extends BizEntity<Long> {


	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "category_id")
	private Category category;

	private String itemId;

	private String createBy;
	private String updateBy;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
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
