package com.hsqlu.coding.trace;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created: 2016/8/12.
 * Author: Qiannan Lu
 */
public interface IHttpServerTracer extends ICallTracer {
    void begin(HttpServletRequest var1);

    void end(HttpServletResponse var1);

    interface Creator {
        IHttpServerTracer create();
    }
}
