package com.hsqlu.coding.trace.http;

import com.github.kristofa.brave.http.HttpRequest;
import com.github.kristofa.brave.http.SpanNameProvider;

/**
 * Created: 2016/8/12.
 * Author: Qiannan Lu
 */
public class ClientSpanNameProviderImpl implements SpanNameProvider {
    private final String span;

    public ClientSpanNameProviderImpl() {
        this.span = "http.client";
    }

    public ClientSpanNameProviderImpl(String span) {
        this.span = span;
    }

    @Override
    public String spanName(HttpRequest httpRequest) {
        return this.span;
    }
}
