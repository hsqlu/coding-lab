package com.hsqlu.coding.concurrent.collections;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created: 2016/4/27.
 * Author: Qiannan Lu
 */
public class PollTask implements Runnable {
    private ConcurrentLinkedDeque<String> list;

    public PollTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5000; ++i) {
            list.pollFirst();
            list.pollLast();
        }
    }

    public static void main(String[] args) {
        ConcurrentLinkedDeque<String> linkedDeque = new ConcurrentLinkedDeque<>();

        Thread[] threads = new Thread[100];

        for (int i = 0; i < threads.length; ++i) {
            AddTask task = new AddTask(linkedDeque);
            threads[i] = new Thread(task);
            threads[i].start();
        }

        System.out.printf("Main: %d AddTask threads have been launched\n", threads.length);

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Main: Size of the list: %d\n", linkedDeque.size());

        for (int i = 0; i < threads.length; ++i) {
            PollTask task = new PollTask(linkedDeque);
            threads[i] = new Thread(task);
            threads[i].start();
        }

        System.out.printf("Main: %d PollTask threads have been launched\n", threads.length);

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Main: Size of the list: %d\n", linkedDeque.size());

    }
}
