package com.hsqlu.coding.concurrent;

/**
 * Created: 2016/4/11.
 * Author: Qiannan Lu
 */
public class Counting {
    public static void main(String[] args) throws InterruptedException {
        class Counter {
            private int count = 0;
            public synchronized void increment() {
//            public void increment() {
                ++count;
            }
            public int getCount() {
                return count;
            }
        }
        final Counter counter = new Counter();
        class CountingThread extends Thread {
            public void run() {
                for (int x = 0; x < 100000; ++x) {
                    counter.increment();
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
        System.out.println(counter.getCount() + "time elapsed " + (end - start));
    }
}
