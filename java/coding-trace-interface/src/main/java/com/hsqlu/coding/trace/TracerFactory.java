package com.hsqlu.coding.trace;

/**
 * Created: 2016/8/12.
 * Author: Qiannan Lu
 */
public class TracerFactory {
    private static final TracerFactory tracerFactory = new TracerFactory();
    private IHttpClientTracer.Creator httpClientTracerCreator = null;
    private IHttpServerTracer.Creator httpServerTracerCreator = null;
    private IClientTracer.Creator clientTracerCreator = null;

    public TracerFactory() {
    }

    public static TracerFactory instance() {
        return tracerFactory;
    }

    public void setHttpServerTracerCreator(IHttpServerTracer.Creator httpServerTracerCreator) {
        this.httpServerTracerCreator = httpServerTracerCreator;
    }

    public void setHttpClientTracerCreator(IHttpClientTracer.Creator httpClientTracerCreator) {
        this.httpClientTracerCreator = httpClientTracerCreator;
    }

    public void setClientTracerCreator(IClientTracer.Creator clientTracerCreator) {
        this.clientTracerCreator = clientTracerCreator;
    }

    public IHttpServerTracer createHttpServerTracer() {
        IHttpServerTracer httpServerTracer = null;
        if(this.httpServerTracerCreator != null) {
            httpServerTracer = this.httpServerTracerCreator.create();
        }
        return httpServerTracer;
    }

    public IHttpClientTracer createHttpClientTracer() {
        IHttpClientTracer httpClientTracer = null;
        if(this.httpClientTracerCreator != null) {
            httpClientTracer = this.httpClientTracerCreator.create();
        }
        return httpClientTracer;
    }

    public IClientTracer createClientTracer() {
        IClientTracer clientTracer = null;
        if(this.clientTracerCreator != null) {
            clientTracer = this.clientTracerCreator.create();
        }
        return clientTracer;
    }
}
