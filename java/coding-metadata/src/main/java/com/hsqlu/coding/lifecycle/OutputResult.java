package com.hsqlu.coding.lifecycle;

/**
 * Created: 2016/5/6.
 * Author: Qiannan Lu
 */
public class OutputResult {
    private boolean finished = false;

    private boolean error = false;

    private String message;

    public synchronized void finished() {
        if (finished)
            return;
        this.finished = true;
        notifyAll();
    }

    public synchronized void finished(String message) {
        this.message = message;
        if (finished) {
            return;
        }
        this.finished = true;
        notifyAll();
    }

    public synchronized boolean await() {
        while (!finished) {
            try {
                wait();
                System.out.println("~~");
            } catch (InterruptedException ignored) {
            }
        }
        return finished;
    }

    public boolean isFinished() {
        return finished;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
