package com.hsqlu.coding.concurrent.customer;

import java.util.Date;
import java.util.concurrent.*;

/**
 * Created: 2016/4/27.
 * Author: Qiannan Lu
 */
public class MyExecutor extends ThreadPoolExecutor {
    private ConcurrentHashMap<String, Date> startTimes;

    public MyExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        startTimes = new ConcurrentHashMap<>();
    }

    @Override
    public void shutdown() {
        super.shutdown();
    }
}
