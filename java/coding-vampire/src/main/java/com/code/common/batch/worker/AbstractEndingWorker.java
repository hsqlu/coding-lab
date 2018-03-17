package com.code.common.batch.worker;

import com.code.common.batch.message.CommunicationMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractEndingWorker<E> extends AbstractStandardWorker<E, Object> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractEndingWorker.class);

    public AbstractEndingWorker() {
        super();
    }
}
