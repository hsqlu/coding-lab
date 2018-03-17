package com.hsqlu.coding.trace;

/**
 * Created: 2016/8/12.
 * Author: Qiannan Lu
 */
public interface IClientTracer {
    void begin(String var1, String var2);

    void addBinaryAnnotation(String var1, String var2);

    void end();

    interface Creator {
        IClientTracer create();
    }
}
