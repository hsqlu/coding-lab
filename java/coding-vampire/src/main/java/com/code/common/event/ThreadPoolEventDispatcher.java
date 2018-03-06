package com.code.common.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class ThreadPoolEventDispatcher<EVENT_TYPE extends Enum<EVENT_TYPE>, EVENT>
        extends AbstractEventDispatcher<EVENT_TYPE, EVENT> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolEventDispatcher.class);

    public ThreadPoolEventDispatcher(String name, int threadCount, int queueSize) {
        super(name);
        executor = new ThreadPoolExecutor(
                threadCount, threadCount, 0L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(queueSize),
                new ThreadFactory() {
                    int i = 0;

                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, name + "[" + (i++) + "]");
                    }
                },
                BLOCKING_POOL_POLICY);
    }

    @Override
    protected void doHandler(IEventHandler<EVENT_TYPE, EVENT> handler, EVENT_TYPE eventType, EVENT event) {
        executor.execute(() -> handler.handle(eventType, event));
    }
}
