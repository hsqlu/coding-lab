package com.code.common.mt.event;

import com.code.common.mt.report.EventWrapper;

import java.util.List;

/**
 * Created: 2016/4/19.
 * Author: Qiannan Lu
 */
public abstract class AbstractEventHandler implements EventHandler {

    private EventConfiguration configuration;

    private List<EventListener> listenerList;

    public void preHandler() {

    }


    @Override
    public void handle(EventWrapper eventWrapper) {
//        listenerList.forEach(listener -> listener.beforeProcess(eventWrapper));


//        listenerList.forEach(listener -> listener.afterProcess(eventWrapper));
    }
}
