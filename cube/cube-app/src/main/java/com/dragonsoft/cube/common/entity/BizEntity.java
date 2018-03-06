package com.dragonsoft.cube.common.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * Created: 2016/9/6.
 * Author: Qiannan Lu
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BizEntity<I extends Serializable> extends BaseEntity<I> {
	@LastModifiedDate
	private Date updateTime;

	@CreatedDate
	@Column(updatable = false)
	private Date createTime;

	private boolean deleted;

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
