package com.code.common.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractEventHandler<EVENT_TYPE, EVENT> implements IEventHandler<EVENT_TYPE, EVENT> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractEventHandler.class);

    @Override
    public void handle(EVENT_TYPE eventType, EVENT event) {
        try {
            LOGGER.debug("事件[{}]开始执行", eventType);
            doHandle(eventType, event);
            LOGGER.debug("事件[{}]执行结束", eventType);
        } catch (Throwable e) {
            LOGGER.error("事件[{0}]执行过程出现异常: {1}", eventType, e.toString(), e);
            LOGGER.debug("事件[{}]开始进行异常处理，执行过程出现异常!", eventType);
            doException(eventType, event, e);
            LOGGER.debug("事件[{}]异常处理完成!", eventType);
        }
    }

    @Override
    public void handle(EVENT_TYPE eventType) {
        try {
            LOGGER.debug("事件[{}]开始执行", eventType);
            doHandle(eventType);
            LOGGER.debug("事件[{}]执行结束", eventType);
        } catch (Throwable e) {
            LOGGER.error("事件[{0}]执行过程出现异常: {1}", eventType, e.toString(), e);
            LOGGER.debug("事件[{}]开始进行异常处理，执行过程出现异常!", eventType);
            doException(eventType, e);
            LOGGER.debug("事件[{}]异常处理完成!", eventType);
        }
    }

    protected void doHandle(EVENT_TYPE eventType, EVENT event) throws Throwable {
    }

    protected void doHandle(EVENT_TYPE eventType) {
    }

    protected void doException(EVENT_TYPE eventType, EVENT event, Throwable e) {
    }

    protected void doException(EVENT_TYPE eventType, Throwable e) {
    }
}
