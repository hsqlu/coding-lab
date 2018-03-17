package com.dragonsoft.cube.module.admin.entity;

import com.dragonsoft.cube.common.constant.IDGenerator;
import com.dragonsoft.cube.common.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created: 2016/9/6.
 * Author: Qiannan Lu
 */
@Entity
@Table(name = "T_SYS_USER")
@GenericGenerator(name = "id", strategy = IDGenerator.ASSIGNED)
public class SystemUser extends BaseEntity<String> {
	private String username;

	private String dept;
	private String level;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
}
