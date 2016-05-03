package com.hsqlu.coding.concurrent.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created: 2016/4/27.
 * Author: Qiannan Lu
 */
public class Server {
    private ThreadPoolExecutor executor;

    public Server() {
        executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    }

    public Server(int fixedThreadCount) {
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(fixedThreadCount);
    }

    public void executeTask(Task task) {
        System.out.println("Server: A new task has arrived");
        executor.execute(task);
        System.out.printf("Server: Pool Size: %d\n", executor.getPoolSize());
        System.out.printf("Server: Active Count: %d\n", executor.getActiveCount());
        System.out.printf("Server: Task Count: %d\n", executor.getTaskCount());
        System.out.printf("Server: Completed Tasks: %d\n", executor.getCompletedTaskCount());
    }

    public void endServer() {
        executor.shutdown();
    }

    public static void main(String[] args) {
        Server server = new Server(5);

        for (int i = 0; i < 100; ++i) {
            Task task = new Task("Task" + i);
            server.executeTask(task);
        }
        server.endServer();
    }
}
