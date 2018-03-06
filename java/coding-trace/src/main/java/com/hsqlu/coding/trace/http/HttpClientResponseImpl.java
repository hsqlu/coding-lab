package com.hsqlu.coding.trace.http;

import com.github.kristofa.brave.http.HttpResponse;

/**
 * Created: 2016/8/12.
 * Author: Qiannan Lu
 */
public class HttpClientResponseImpl implements HttpResponse {
    private final org.apache.http.HttpResponse response;

    public HttpClientResponseImpl(org.apache.http.HttpResponse response) {
        this.response = response;
    }

    @Override
    public int getHttpStatusCode() {
        return this.response.getStatusLine().getStatusCode();
    }
}
