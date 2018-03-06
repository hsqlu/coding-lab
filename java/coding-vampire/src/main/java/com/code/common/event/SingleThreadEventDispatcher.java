package com.code.common.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SingleThreadEventDispatcher<EVENT_TYPE extends Enum<EVENT_TYPE>, EVENT>
        extends AbstractEventDispatcher<EVENT_TYPE, EVENT> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SingleThreadEventDispatcher.class);

    public SingleThreadEventDispatcher(String name, int queueSize) {
        super(name);
        executor = new ThreadPoolExecutor(1,
                1,
                0L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(queueSize),
                task -> new Thread(task, name),
                BLOCKING_POOL_POLICY);
    }

    @Override
    protected void doHandler(IEventHandler<EVENT_TYPE, EVENT> handler, EVENT_TYPE eventType, EVENT event) {
        executor.execute(() -> handler.handle(eventType, event));
    }

    @Override
    public void shutdownNow() {
        this.executor.shutdownNow();
    }
}
