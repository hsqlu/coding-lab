package com.hsqlu.coding.engine.base;

import java.util.Collection;

/**
 * Created: 2016/7/7.
 * Author: Qiannan Lu
 */
public interface Lifecycle<STATE extends Enum<STATE>> {
    STATE initWork();

    OperateResult startWork();

    void stopWork();

    void addListener(STATE state, LifecycleListener listener);

    void addListenerAllState(LifecycleListener listener);

    Collection<LifecycleListener> getListeners(STATE state);

    STATE currentState();
}
