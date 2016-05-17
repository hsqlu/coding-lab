package com.hsqlu.coding.lifecycle;

/**
 * Created: 2016/5/6.
 * Author: Qiannan Lu
 */
public interface LifecycleListener<SOURCE, STATE, INFO> {
    void handleEvent(SOURCE source, STATE state, Throwable e, String message, INFO... info);
}
