package com.dragonsoft.cube.module.label.entity;

import com.dragonsoft.cube.common.constant.IDGenerator;
import com.dragonsoft.cube.common.entity.BizEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created: 20/09/2016.
 * Author: Qiannan Lu
 */
@Entity
@Table(name = "T_LABEL_ITEM")
@GenericGenerator(name = "id", strategy = IDGenerator.IDENTITY)
public class LabelItem extends BizEntity<Long> {
	private String itemId;
	private String itemIdType;
}
