package com.code.common.batch;

public class ExecutionStatus {
    private String message;

    private boolean error = false;
    private boolean finished = false;

    public boolean isError() {
        return error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public synchronized void finished(){
        if(finished){
            return;
        }
        this.finished = true;
        notifyAll();
    }

    public synchronized void finished(String message){
        this.message = message;
        if(finished){
            return;
        }
        this.finished = true;
        notifyAll();
    }

    public boolean isFinished() {
        return finished;
    }

    public synchronized boolean await(){
        while(!finished){
            try {
                wait();
            } catch (InterruptedException ignored) {
            }
        }
        return finished;
    }

    @Override
    public String toString() {
        return "执行结果: [完成情况=" + finished + ", 是否异常="+error+", 消息=" + message + "]";
    }
}
