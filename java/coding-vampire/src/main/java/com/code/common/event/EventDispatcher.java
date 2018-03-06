package com.code.common.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;

public class EventDispatcher<EVENT_TYPE extends Enum<EVENT_TYPE>, EVENT>
        extends AbstractEventDispatcher<EVENT_TYPE, EVENT> {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventDispatcher.class);

    public EventDispatcher(String name, ExecutorService executor) {
        super(name);
        this.executor = executor;
    }

    public void setExecutor(ExecutorService executor) {
        this.executor = executor;
    }

    @Override
    protected void doHandler(IEventHandler<EVENT_TYPE, EVENT> handler, EVENT_TYPE eventType, EVENT event) {
        executor.execute(() -> {
            handler.handle(eventType, event);
        });
    }
}
