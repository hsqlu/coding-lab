package com.hsqlu.coding.concurrent.resource;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created: 2016/4/26.
 * Author: Qiannan Lu
 */
public class PrintQueue {
    private final Semaphore semaphore;
    private boolean[] freePrinters;
    private Lock printerLock;
//    private int printer;

    public PrintQueue() {
        this.semaphore = new Semaphore(3);
        printerLock = new ReentrantLock();
        freePrinters = new boolean[3];
        for (int i = 0; i < 3; ++i) {
            freePrinters[i] = true;
        }

    }

    public void printJob(Object document) {
        try {
            semaphore.acquire();
            int assignedPrinter = getPrinter();
            long duration = (long) (Math.random() * 10);
            System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n", Thread.currentThread().getName(), duration);
            Thread.sleep(duration);
            freePrinters[assignedPrinter] = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    public int getPrinter() {
        int ret = -1;
        try {
            printerLock.lock();
            for (int i = 0; i < 3; ++i) {
                if (freePrinters[i]) {
                    ret = i;
                    freePrinters[i] = false;
                    break;
                }
            }
        } finally {
            printerLock.unlock();
        }
        return ret;
    }
}
