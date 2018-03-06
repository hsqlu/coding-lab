package com.code.schedule.event;

/**
 * Created: 19/04/2017.
 * Author: Qiannan Lu
 */
public interface IEventSource {
    void sendEvent(Event event);

    void setDemultiplexer(EventDemultiplexer demultiplexer);

    public EventDemultiplexer getDemultiplexer();

    public String name();
}
