package com.hsqlu.coding.concurrent;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;

/**
 * Created: 2016/4/26.
 * Author: Qiannan Lu
 */
public class Main {
    public static void main(String[] args) {
        Deque<Event> deque = new ArrayDeque<>();
        WriterTask writer = new WriterTask(deque);
        for (int i = 0; i < 3; ++i) {
            Thread thread = new Thread(writer);
            thread.start();
        }
        CleanerTask cleaner = new CleanerTask(deque);
        cleaner.start();
    }

    /*private static void demoTask() {
        Deque<Event> deque = new ArrayDeque<>();
        WriterTask writer = new WriterTask(deque);
        for (int i = 0; i < 3; ++i) {
            Thread thread = new Thread(writer);
            thread.start();
        }
        CleanerTask cleaner = new CleanerTask(deque);
        cleaner.start();
    }*/

    private void demoLoader() {
        DataSourcesLoader dataSourcesLoader = new DataSourcesLoader();
        NetworkConnectionsLoader networkConnectionsLoader = new NetworkConnectionsLoader();
        Thread t1 = new Thread(dataSourcesLoader, "DataSourceThread");
        Thread t2 = new Thread(networkConnectionsLoader, "NetworkConnectionsThread");
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Main: Configuration has been loaded: %s\n", new Date());
    }
}
