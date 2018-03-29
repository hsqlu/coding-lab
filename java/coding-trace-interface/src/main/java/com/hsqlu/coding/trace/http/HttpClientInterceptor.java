package com.hsqlu.coding.trace.http;

import com.hsqlu.coding.trace.IHttpClientTracer;
import com.hsqlu.coding.trace.TracerFactory;
import org.apache.http.*;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

/**
 * Created: 2016/8/12.
 * Author: Qiannan Lu
 */
public class HttpClientInterceptor implements HttpRequestInterceptor, HttpResponseInterceptor {
    private final IHttpClientTracer httpClientTracer = TracerFactory.instance().createHttpClientTracer();

    public HttpClientInterceptor() {
    }

    public HttpClientInterceptor(String spanName) {
        if(this.httpClientTracer != null) {
            this.httpClientTracer.setSpanName(spanName);
        }
    }

    @Override
    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        if(this.httpClientTracer != null) {
            this.httpClientTracer.process(request, context);
        }
    }

    @Override
    public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
        if(this.httpClientTracer != null) {
            this.httpClientTracer.process(response, context);
        }
    }
}
