package com.code.common.mt.report;

import com.code.common.mt.event.Event;
import com.code.common.mt.event.EventDispatcher;
import com.code.common.mt.event.EventHandler;


/**
 * Created: 2016/4/12.
 * Author: Qiannan Lu
 */
public class EventWrapper implements Runnable {
    private Event event;

    private EventDispatcher dispatcher;

    public EventWrapper(Event event) {
        this.dispatcher = getDispatcher(event);
    }


    private EventDispatcher getDispatcher(Event event) {
        return null;
    }

    @Override
    public void run() {
        EventHandler handler = dispatcher.dispatchEvent(event);
        handler.handle(this);
    }
}
