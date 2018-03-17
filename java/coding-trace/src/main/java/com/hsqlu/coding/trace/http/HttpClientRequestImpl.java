package com.hsqlu.coding.trace.http;

import com.github.kristofa.brave.http.HttpClientRequest;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpRequestWrapper;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created: 2016/8/12.
 * Author: Qiannan Lu
 */
public class HttpClientRequestImpl implements HttpClientRequest {
    private final HttpRequest httpRequest;

    public HttpClientRequestImpl(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    @Override
    public void addHeader(String header, String value) {
        this.httpRequest.addHeader(header, value);
    }

    @Override
    public URI getUri() {
        if(this.httpRequest instanceof HttpRequestWrapper) {
            try {
                HttpRequestWrapper e = (HttpRequestWrapper)this.httpRequest;
                return new URI(e.getOriginal().getRequestLine().getUri());
            } catch (URISyntaxException var2) {
                throw new IllegalStateException(var2);
            }
        } else {
            try {
                return new URI(this.httpRequest.getRequestLine().getUri());
            } catch (URISyntaxException var3) {
                throw new IllegalStateException(var3);
            }
        }
    }

    @Override
    public String getHttpMethod() {
        return this.httpRequest.getRequestLine().getMethod();
    }
}
