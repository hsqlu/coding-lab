package com.hsqlu.coding.concurrent.resource;

/**
 * Created: 2016/4/26.
 * Author: Qiannan Lu
 */
public class Job implements Runnable {
    private PrintQueue printQueue;

    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        System.out.printf("%s: Going to print a job\n", Thread.currentThread().getName());
        printQueue.printJob(new Object());
        System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
    }

    public static void main(String args[]) {
        PrintQueue printQueue = new PrintQueue();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; ++i) {
            threads[i] = new Thread(new Job(printQueue), "Thread" + i);
        }
        for (int i = 0; i < 10; ++i) {
            threads[i].start();
        }
    }
}
