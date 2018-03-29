package com.hsqlu.coding.trace;

import com.github.kristofa.brave.*;
import com.google.common.collect.Lists;
import com.hsqlu.coding.trace.TracerFactory;
import com.hsqlu.coding.trace.http.HttpClientTracerImpl;
import com.hsqlu.coding.trace.http.HttpServerTracerImpl;

import java.util.ArrayList;

/**
 * Created: 2016/8/12.
 * Author: Qiannan Lu
 */
public class TraceConfig {
    private static TraceConfig traceConfig = new TraceConfig();

    private Brave brave = null;
    private SpanCollector spanCollector = null;
    private ArrayList<TraceFilter> filters = Lists.newArrayList();

    public TraceConfig() {
//        this.filters.add(new EtcdFilter());
        TracerFactory.instance().setHttpServerTracerCreator(new HttpServerTracerImpl.CreatorImpl());
        TracerFactory.instance().setHttpClientTracerCreator(new HttpClientTracerImpl.CreatorImpl());
//        TracerFactory.instance().setClientTracerCreator(new SdkTracer.CreatorImpl());
    }

    public void addFilters(TraceFilter filter) {
        this.filters.add(filter);
    }

    public TraceConfig setSpanCollector(SpanCollector spanCollector) {
        this.spanCollector = spanCollector;
        return this;
    }

    private Brave buildImpl(String serviceName) {
        if (null == this.spanCollector) {
            return null;
        }

        try {
            Brave.Builder ignore = new Brave.Builder(serviceName);
            ignore.spanCollector(this.spanCollector);
            ignore.traceFilters(this.filters);
            this.brave = ignore.build();
        } catch (Exception ignored) {
        }
        return this.brave;
    }

    public Brave build() {
        return this.buildImpl("unknown");
    }

    public Brave build(String serviceName) {
        return this.buildImpl(serviceName);
    }

    public ClientTracer getClientTracer() {
        ClientTracer clientTracer = null;
        if (this.brave != null) {
            clientTracer = this.brave.clientTracer();
        }
        return clientTracer;
    }

    public ServerTracer getServerTracer() {
        ServerTracer serverTracer = null;
        if(this.brave != null) {
            serverTracer = this.brave.serverTracer();
        }
        return serverTracer;
    }

    public static TraceConfig instance() {
        return traceConfig;
    }
}
