package com.code.common.event;

public interface IEventHandler<EVENT_TYPE, EVENT> {
    void handle(EVENT_TYPE eventType, EVENT event);

    void handle(EVENT_TYPE eventType);
}
