package com.dragonsoft.cube.model.step;

/**
 * Created: 25/09/2016.
 * Author: Qiannan Lu
 */
public interface StepFaceBuilder<F, S> {
	String build(F flow, S step);
}
