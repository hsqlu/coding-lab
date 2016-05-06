package com.hsqlu.coding.concurrent.customer;

import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created: 2016/4/27.
 * Author: Qiannan Lu
 */
public class MyScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor {
    public MyScheduledThreadPoolExecutor(int corePoolSize) {
        super(corePoolSize);
    }

    @Override
    protected <V> RunnableScheduledFuture<V> decorateTask(Runnable runnable, RunnableScheduledFuture<V> task) {
        return new MyScheduledTask<>(runnable, null, task, this);
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        ScheduledFuture<?> myTask = super.scheduleAtFixedRate(command, initialDelay, period, unit);
        MyScheduledTask<?> task = (MyScheduledTask<?>) myTask;
        ((MyScheduledTask<?>) myTask).setPeriod(TimeUnit.MILLISECONDS.convert(period, unit));
        return  task;
    }
}
