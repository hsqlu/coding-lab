package com.code.common.mt.event;

import com.code.common.mt.report.EventWrapper;

import java.util.Map;

/**
 * Created: 2016/4/19.
 * Author: Qiannan Lu
 */
public class EventDispatcher {
    private EventFilter filter;
    private Map<String, EventHandler> handlers;

    public EventHandler dispatchEvent(Event event) {
        return new EventHandler() {
            @Override
            public void handle(EventWrapper eventWrapper) {

            }
        };
//        filter.doFilter(event);
//        eventWrapper.getEventHandler().handle(eventWrapper);
    }
}
