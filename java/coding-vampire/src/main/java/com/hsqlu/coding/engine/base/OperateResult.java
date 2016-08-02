package com.hsqlu.coding.engine.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created: 2016/7/7.
 * Author: Qiannan Lu
 */
public class OperateResult {
    private static final Logger LOGGER = LoggerFactory.getLogger(OperateResult.class);

    private boolean finished = false;
    private boolean isError = false;

    private String message;

    public synchronized boolean await(){
        while(!finished){
            try {
                wait();
            } catch (InterruptedException ignored) {
            }
        }
        return finished;
    }

    public synchronized void finish() {
        if (finished)
            return;
        this.finished = true;
        notifyAll();
    }

    public synchronized void finish(String message) {
        this.message = message;
        if (finished)
            return;
        this.finished = true;
        notifyAll();
    }

    @Override
    public String toString() {
        return "Execute result: " + finished + ", exception: "  + isError + ", message: " + message;
    }

    public boolean isFinished() {
        return finished;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
