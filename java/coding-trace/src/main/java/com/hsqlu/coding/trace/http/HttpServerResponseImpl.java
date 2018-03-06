package com.hsqlu.coding.trace.http;

import com.github.kristofa.brave.http.HttpResponse;

import javax.servlet.http.HttpServletResponse;

/**
 * Created: 2016/8/12.
 * Author: Qiannan Lu
 */
public class HttpServerResponseImpl implements HttpResponse {
    private final HttpServletResponse httpServletResponse;

    public HttpServerResponseImpl(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    @Override
    public int getHttpStatusCode() {
        return this.httpServletResponse.getStatus();
    }
}
