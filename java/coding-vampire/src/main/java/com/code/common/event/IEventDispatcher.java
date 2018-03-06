package com.code.common.event;

public interface IEventDispatcher<EVENT_TYPE extends Enum<EVENT_TYPE>, EVENT> {
    String info();

    String getName();

    void addHandler(EVENT_TYPE eventType, IEventHandler<EVENT_TYPE, EVENT> handler);

    void execute(EVENT_TYPE type, final EVENT event);

    void shutdownNow();
}
