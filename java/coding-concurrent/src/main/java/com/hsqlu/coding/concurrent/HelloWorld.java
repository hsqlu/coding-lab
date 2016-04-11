package com.hsqlu.coding.concurrent;

/**
 * Created: 2016/4/11.
 * Author: Qiannan Lu
 */
public class HelloWorld {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            public void run() {
                System.out.println("Hello from new thread");
            }
        };
        thread.start();
        Thread.yield();
//        Thread.sleep(1);
        System.out.println("Hello from main thread");
        thread.join();
    }
}
