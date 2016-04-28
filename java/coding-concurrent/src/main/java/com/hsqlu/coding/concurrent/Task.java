package com.hsqlu.coding.concurrent;

/**
 * Created: 2016/4/26.
 * Author: Qiannan Lu
 */
public class Task implements Runnable {

    @Override
    public void run() {
        int number = Integer.parseInt("TTT");
    }

    public static void main(String[] args) {
        Task task = new Task();
        Thread thread = new Thread(task);
//        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        thread.start();
    }
}
