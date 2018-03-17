package com.code.common.event;


import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;

public abstract class AbstractEventDispatcher<EVENT_TYPE extends Enum<EVENT_TYPE>, EVENT>
        implements IEventDispatcher<EVENT_TYPE, EVENT> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractEventDispatcher.class);

    protected static RejectedExecutionHandler BLOCKING_POOL_POLICY = (task, executor) -> {
        if (!executor.isShutdown()) {
            try {
                executor.getQueue().put(task);
            } catch (InterruptedException ignored) {
            }
        }
    };

    protected Map<EVENT_TYPE, IEventHandler<EVENT_TYPE, EVENT>> handlers = Maps.newHashMap();

    protected String name;

    /**
     * 是否忽略空事件
     */
    protected boolean ignoreNullEventType = true;

    public AbstractEventDispatcher(String name) {
        this.name = name;
    }

    protected ExecutorService executor;


    public void addHandler(EVENT_TYPE eventType, IEventHandler<EVENT_TYPE, EVENT> handler) {
        handlers.put(eventType, handler);
    }

    public void execute(final EVENT_TYPE type, final EVENT event) {
        final IEventHandler<EVENT_TYPE, EVENT> handler = this.handlers.get(type);
        if (handler == null && !ignoreNullEventType) {
            throw new InvalidEventException("找不到指定事件[" + type + "]的处理类");
        } else if (handler != null) {
            LOGGER.trace("处理事件type-[{0}], event-[{1}]", type, event);
            doHandler(handler, type, event);
        } else {
            LOGGER.warn("找不到指定事件[{}]的处理类,忽略处理!", type);
        }
    }

    protected abstract void doHandler(IEventHandler<EVENT_TYPE, EVENT> handler, final EVENT_TYPE eventType, final EVENT event);

    @Override
    public void shutdownNow() {
        this.executor.shutdownNow();
    }

    @Override
    public String info() {
        return "[" + name + "]";
    }


    @Override
    public String getName() {
        return name;
    }
}