package com.hsqlu.coding.trace.http;

import com.github.kristofa.brave.ClientRequestInterceptor;
import com.github.kristofa.brave.ClientResponseInterceptor;
import com.github.kristofa.brave.ClientTracer;
import com.github.kristofa.brave.http.HttpClientRequestAdapter;
import com.github.kristofa.brave.http.HttpClientResponseAdapter;
import com.github.kristofa.brave.http.ServiceNameProvider;
import com.github.kristofa.brave.http.SpanNameProvider;
import com.hsqlu.coding.trace.TraceConfig;
import com.hsqlu.coding.trace.IHttpClientTracer;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

/**
 * Created: 2016/8/12.
 * Author: Qiannan Lu
 */
public class HttpClientTracerImpl implements IHttpClientTracer {
    private final ServiceNameProvider serviceNameProvider = new ServiceNameProviderImpl();
    private SpanNameProvider spanNameProvider = new ClientSpanNameProviderImpl();
    private ClientRequestInterceptor clientRequestInterceptor = null;
    private ClientResponseInterceptor clientResponseInterceptor = null;

    public HttpClientTracerImpl() {
    }

    @Override
    public void setSpanName(String spanName) {
        this.spanNameProvider = new ClientSpanNameProviderImpl(spanName);
    }

    @Override
    public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
        if(null == this.clientRequestInterceptor) {
            ClientTracer ignore = TraceConfig.instance().getClientTracer();
            if(null == ignore) {
                return;
            }
            this.clientRequestInterceptor = new ClientRequestInterceptor(ignore);
            this.clientResponseInterceptor = new ClientResponseInterceptor(ignore);
        }

        try {
            this.clientRequestInterceptor.handle(new HttpClientRequestAdapter(new HttpClientRequestImpl(request), this.serviceNameProvider, this.spanNameProvider));
        } catch (Exception ignored) {
        }
    }

    @Override
    public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
        if(null != this.clientResponseInterceptor) {
            try {
                this.clientResponseInterceptor.handle(new HttpClientResponseAdapter(new HttpClientResponseImpl(response)));
            } catch (Exception ignored) {
            }
        }
    }

    @Override
    public void handleException(Exception e) {
        ClientTracer clientTracer = TraceConfig.instance().getClientTracer();
        if(clientTracer != null) {
            clientTracer.submitBinaryAnnotation("exception", e.getMessage());
        }
    }

    @Override
    public void stopTrace() {

    }

    @Override
    public boolean isTrace() {
        return this.clientResponseInterceptor != null;
    }

    public static class CreatorImpl implements Creator {
        public CreatorImpl() {
        }

        public IHttpClientTracer create() {
            return new HttpClientTracerImpl();
        }
    }
}
