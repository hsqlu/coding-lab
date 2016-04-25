package com.code.common.mt;

import com.code.common.mt.event.Event;
import com.code.common.mt.report.EventWrapper;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created: 2016/4/19.
 * Author: Qiannan Lu
 */
public class EventMonitor {
    public static BlockingQueue<Runnable> queue;

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 6, 1, TimeUnit.MINUTES, queue);
        while (queue.peek() != null) {
            executor.submit(queue.poll());
//            executor.execute(new Thread(new ThreadPoolTest(), "TestThread".concat(""+i)));
            int threadSize = queue.size();
            System.out.println("线程队列大小为-->"+threadSize);
        }
        executor.shutdown();
    }


    public void init() throws InterruptedException {
        queue = new LinkedBlockingQueue<>();
        queue.put(new EventWrapper(new Event()));
    }
}
