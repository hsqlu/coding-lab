package com.hsqlu.coding.concurrent;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created: 2016/4/26.
 * Author: Qiannan Lu
 */
public class SearchTask implements Runnable {
    private Result result;

    public SearchTask(Result result) {
        this.result = result;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.printf("Thread %s: Start\n", name);
        try {
            doTask();
            result.setName(name);
        } catch (InterruptedException e) {
            System.out.printf("Thread %s: Interrupted\n", name);
            return;
        }
        System.out.printf("Thread %s: End\n", name);
    }

    private void doTask() throws InterruptedException {
        Random random = new Random((new Date().getTime()));
        int value = (int) (random.nextDouble() * 100);
        System.out.printf("Thread %s: %d\n", Thread.currentThread().getName(), value);
        TimeUnit.SECONDS.sleep(value);
    }

    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("Searcher");
        Result result = new Result();
        SearchTask task = new SearchTask(result);
        for (int i = 0; i < 10; ++i) {
            Thread thread = new Thread(group, task);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Number of Threads: %d\n", group.activeCount());
        System.out.print("Information about the Thread group\n");
        group.list();

        Thread[] threads = new Thread[group.activeCount()];
        group.enumerate(threads);
        for (int i = 0; i < group.activeCount(); ++i) {
            System.out.printf("Thread %s: %s\n", threads[i].getName(), threads[i].getState());
        }

        waitFinish(group);
        group.interrupt();
    }

    private static void waitFinish(ThreadGroup group) {
        while (group.activeCount() > 9) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
class Result {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}