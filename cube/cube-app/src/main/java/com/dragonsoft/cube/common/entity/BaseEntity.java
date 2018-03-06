package com.dragonsoft.cube.common.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created: 2016/9/6.
 * Author: Qiannan Lu
 */
@MappedSuperclass
public class BaseEntity<ID extends Serializable> {
	@Id
	@GeneratedValue(generator = "id")
	private ID id;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}
}
