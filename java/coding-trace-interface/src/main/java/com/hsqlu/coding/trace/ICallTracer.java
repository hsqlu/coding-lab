package com.hsqlu.coding.trace;

/**
 * Created: 2016/8/12.
 * Author: Qiannan Lu
 */
public interface ICallTracer {
    void handleException(Exception e);

    void stopTrace();

    boolean isTrace();
}
