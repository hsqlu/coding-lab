package com.hsqlu.coding.lifecycle;

import java.util.Collection;

/**
 * Created: 2016/5/6.
 * Author: Qiannan Lu
 */
public interface Lifecycle<STATE extends Enum<STATE>> {
    OutputResult startWorking();

    void stopWorking();

    STATE initWorking();

    void addListener(STATE state, LifecycleListener listener);

    void addListenerAllState(LifecycleListener listener);

    Collection<LifecycleListener> getListeners(STATE state);

    STATE curState();
}
