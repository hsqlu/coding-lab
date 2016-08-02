package com.hsqlu.coding.engine.data;

/**
 * Created: 2016/7/12.
 * Author: Qiannan Lu
 */
public interface IDataBranch<DATA extends Routable> {
    int getChannelCount();

    String getName();

    int addQueue();

    boolean isEmpty(int index);

    boolean isEmpty();

    DATA peek();

    DATA take(int index);

    DATA take();

}
