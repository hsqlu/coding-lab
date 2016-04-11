package com.hsqlu.coding.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created: 2016/4/11.
 * Author: Qiannan Lu
 */
public class AtomicCounting {
    public static void main(String[] args) throws InterruptedException {
        final AtomicInteger counter = new AtomicInteger();

        class CountingThread extends Thread {
            public void run() {
                for (int x = 0; x < 100000; ++x) {
                    counter.incrementAndGet();
                }
            }
        }
        long start = System.currentTimeMillis();

        CountingThread t1 = new CountingThread();
        CountingThread t2 = new CountingThread();

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        long end = System.currentTimeMillis();

        System.out.println(counter.get() + "time elapsed " + (end - start));
    }
}
