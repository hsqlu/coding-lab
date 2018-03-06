package com.code.common.batch.worker;

public abstract class AbstractMiddleWorker<E, T> extends AbstractWorker<E, T> {
    public AbstractMiddleWorker() {
        super();
    }

    protected abstract void lastWork() throws Exception;
}
