package com.hsqlu.coding.trace.http;

import com.github.kristofa.brave.http.HttpServerRequest;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

/**
 * Created: 2016/8/12.
 * Author: Qiannan Lu
 */
public class HttpServerRequestImpl implements HttpServerRequest {
    private final HttpServletRequest httpServletRequest;

    public HttpServerRequestImpl(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public String getHttpHeaderValue(String headerName) {
        return this.httpServletRequest.getHeader(headerName);
    }

    @Override
    public URI getUri() {
        Object uri = this.httpServletRequest.getAttribute("org.springframework.web.servlet.HandlerMapping.bestMatchingPattern");
        if(uri != null) {
            try {
                return URI.create((new URLCodec()).encode((String)uri));
            } catch (EncoderException ignored) {
            }
        }
        return URI.create(this.httpServletRequest.getRequestURI());
    }

    @Override
    public String getHttpMethod() {
        return this.httpServletRequest.getMethod();
    }
}
