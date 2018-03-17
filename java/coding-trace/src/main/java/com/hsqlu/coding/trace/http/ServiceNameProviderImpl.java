package com.hsqlu.coding.trace.http;

import com.github.kristofa.brave.http.HttpRequest;
import com.github.kristofa.brave.http.ServiceNameProvider;

/**
 * Created: 2016/8/12.
 * Author: Qiannan Lu
 */
public class ServiceNameProviderImpl implements ServiceNameProvider {
    public ServiceNameProviderImpl() {
    }

    @Override
    public String serviceName(HttpRequest httpRequest) {
        return httpRequest.getUri().getHost();
    }
}
