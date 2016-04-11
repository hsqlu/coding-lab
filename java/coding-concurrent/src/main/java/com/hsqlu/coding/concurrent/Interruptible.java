package com.hsqlu.coding.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created: 2016/4/11.
 * Author: Qiannan Lu
 */
public class Interruptible {
    public static void main(String[] args) throws InterruptedException {

        final ReentrantLock l1 = new ReentrantLock();
        final ReentrantLock l2 = new ReentrantLock();

        Thread t1 = new Thread() {
            public void run() {
                try {
                    l1.lockInterruptibly();
                    Thread.sleep(1000);
                    l2.lockInterruptibly();
                } catch (InterruptedException e) {
                    System.out.println("t1 interrupted");
                    e.printStackTrace();
                }
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                try {
                    l2.lockInterruptibly();
                    Thread.sleep(1000);
                    l1.lockInterruptibly();
                } catch (InterruptedException e) {
                    System.out.println("t2 interrupted");
                    e.printStackTrace();
                }
            }
        };

        t1.start();
        t2.start();
        Thread.sleep(2000);
        t1.interrupt();
        t2.interrupt();
        t1.join();
        t2.join();
    }
}
