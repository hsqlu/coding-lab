package com.dragonsoft.cube.model.step;

import com.dragonsoft.cube.module.meta.entity.BusinessColumnMeta;

import java.util.List;

/**
 * Created: 25/09/2016.
 * Author: Qiannan Lu
 */
public interface StepFace {
	String getId();
	String getCode();
	String getName();
	List<BusinessColumnMeta> getOutputColumns();
}
