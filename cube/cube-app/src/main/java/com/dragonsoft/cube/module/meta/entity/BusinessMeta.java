package com.dragonsoft.cube.module.meta.entity;

import com.dragonsoft.cube.common.entity.BizEntity;

import java.util.Set;

/**
 * Created: 18/09/2016.
 * Author: Qiannan Lu
 */
public class BusinessMeta extends BizEntity<Long> {
	private Set<BusinessColumnMeta> columnMetaSet;

	private String name;

	private String zhName;

	private String enName;

	private DBTable table;

}
