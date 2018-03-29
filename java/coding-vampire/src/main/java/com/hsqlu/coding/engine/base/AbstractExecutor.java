package com.hsqlu.coding.engine.base;

import com.hsqlu.coding.engine.data.Routable;

/**
 * Created: 2016/7/12.
 * Author: Qiannan Lu
 */
public abstract class AbstractExecutor<LEADER extends AbstractOrganizable<?>, INPUT_DATA
        extends Routable, OUTPUT_DATA extends Routable> implements IExecutor<LEADER, INPUT_DATA, OUTPUT_DATA> {

}
