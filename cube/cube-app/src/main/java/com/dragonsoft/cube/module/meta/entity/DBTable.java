package com.dragonsoft.cube.module.meta.entity;

import com.dragonsoft.cube.common.entity.BizEntity;

import java.util.Set;

/**
 * Created: 2016/9/17.
 * Author: Qiannan Lu
 */
public class DBTable extends BizEntity<Long> {
	private DBScheme scheme;

	private String name;

	private String zhName;

	private String enName;

	private Set<DBTableColumn> columnSet;
}
