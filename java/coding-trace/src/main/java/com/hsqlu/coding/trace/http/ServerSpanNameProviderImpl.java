package com.hsqlu.coding.trace.http;

import com.github.kristofa.brave.http.HttpRequest;
import com.github.kristofa.brave.http.SpanNameProvider;

/**
 * Created: 2016/8/12.
 * Author: Qiannan Lu
 */
public class ServerSpanNameProviderImpl implements SpanNameProvider {
    public ServerSpanNameProviderImpl() {
    }

    @Override
    public String spanName(HttpRequest httpRequest) {
        return httpRequest.getUri().getPath();
    }
}
