package com.hsqlu.coding.engine.transform;

import com.hsqlu.coding.engine.base.IOrganizable;
import com.hsqlu.coding.meta.TransformMeta;

/**
 * Created: 2016/7/7.
 * Author: Qiannan Lu
 */
public interface ITransform<LEADER> extends IOrganizable<LEADER> {
    TransformMeta getTransformMeta();

    String getTaskId();

    String getInstanceId();

    long getDataSetCount();
}
