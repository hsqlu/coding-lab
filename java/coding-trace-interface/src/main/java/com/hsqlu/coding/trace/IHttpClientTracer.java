package com.hsqlu.coding.trace;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

/**
 * Created: 2016/8/12.
 * Author: Qiannan Lu
 */
public interface IHttpClientTracer extends ICallTracer {
    void setSpanName(String var1);

    void process(HttpRequest var1, HttpContext var2) throws HttpException, IOException;

    void process(HttpResponse var1, HttpContext var2) throws HttpException, IOException;

    interface Creator {
        IHttpClientTracer create();
    }
}
