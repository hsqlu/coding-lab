package com.hsqlu.coding.engine.base;

/**
 * Created: 2016/7/7.
 * Author: Qiannan Lu
 */
public interface LifecycleListener<SOURCE, STATE, INFO> {
    void handleEvent(SOURCE source, STATE state, Throwable e, String message, INFO... infoArray);
}
