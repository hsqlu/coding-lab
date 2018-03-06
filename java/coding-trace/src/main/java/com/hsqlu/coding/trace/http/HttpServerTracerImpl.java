package com.hsqlu.coding.trace.http;

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.ServerRequestInterceptor;
import com.github.kristofa.brave.ServerResponseInterceptor;
import com.github.kristofa.brave.ServerTracer;
import com.github.kristofa.brave.http.HttpServerRequestAdapter;
import com.github.kristofa.brave.http.HttpServerResponseAdapter;
import com.github.kristofa.brave.http.SpanNameProvider;
import com.hsqlu.coding.trace.TraceConfig;
import com.hsqlu.coding.trace.IHttpServerTracer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created: 2016/8/12.
 * Author: Qiannan Lu
 */
public class HttpServerTracerImpl implements IHttpServerTracer {
    private final SpanNameProvider spanNameProvider = new ServerSpanNameProviderImpl();
    private ServerRequestInterceptor serverRequestInterceptor = null;
    private ServerResponseInterceptor serverResponseInterceptor = null;

    public HttpServerTracerImpl() {
    }

    @Override
    public void begin(HttpServletRequest httpServletRequest) {
        if (null == this.serverRequestInterceptor) {
            Brave ignore = TraceConfig.instance().build(httpServletRequest.getServerName());
            if (ignore == null) {
                return;
            }
            this.serverRequestInterceptor = ignore.serverRequestInterceptor();
            this.serverResponseInterceptor = ignore.serverResponseInterceptor();
        }

        try {
            this.serverRequestInterceptor.handle(new HttpServerRequestAdapter(new HttpServerRequestImpl(httpServletRequest), this.spanNameProvider));
        } catch (Exception ignored) {
        }
    }

    @Override
    public void end(HttpServletResponse httpServletResponse) {
        if (null != this.serverResponseInterceptor) {
            try {
                this.serverResponseInterceptor.handle(new HttpServerResponseAdapter(new HttpServerResponseImpl(httpServletResponse)));
            } catch (Exception ignored) {
            }
        }
    }

    @Override
    public void handleException(Exception e) {
        ServerTracer serverTracer = TraceConfig.instance().getServerTracer();
        if (null != serverTracer) {
            serverTracer.submitBinaryAnnotation("exception", e.getMessage());
        }
    }

    @Override
    public void stopTrace() {
        ServerTracer serverTracer = TraceConfig.instance().getServerTracer();
        if (null != serverTracer) {
            serverTracer.setStateNoTracing();
        }
    }

    @Override
    public boolean isTrace() {
        return this.serverRequestInterceptor != null;
    }

    public static class CreatorImpl implements Creator {
        public CreatorImpl() {
        }

        public IHttpServerTracer create() {
            return new HttpServerTracerImpl();
        }
    }
}
