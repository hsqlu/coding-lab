package com.hsqlu.coding.concurrent.customer;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created: 2016/4/27.
 * Author: Qiannan Lu
 */
public class Task implements Runnable {
    @Override
    public void run() {
        System.out.print("Task: Begin.\n");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("Task: End.\n");
    }

    public static void main(String[] args) throws InterruptedException {
        MyScheduledThreadPoolExecutor executor = new MyScheduledThreadPoolExecutor(2);

        Task task = new Task();

        System.out.printf("Main: %s\n", new Date());
        executor.schedule(task, 1, TimeUnit.SECONDS);

        TimeUnit.SECONDS.sleep(3);
        System.out.printf("Main: %s\n", new Date());

        task = new Task();

        System.out.printf("Main: %s\n", new Date());
        executor.scheduleAtFixedRate(task, 1, 3, TimeUnit.SECONDS);

        TimeUnit.SECONDS.sleep(10);

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);

        System.out.printf("Main: End of the program\n");

    }
}
