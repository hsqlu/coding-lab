package com.hsqlu.coding.concurrent;

/**
 * Created: 2016/4/26.
 * Author: Qiannan Lu
 */
public class MyThreadGroup extends ThreadGroup {
    public MyThreadGroup(String name) {
        super(name);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.printf("The thread %s has thrown an Exception\n", t.getId());
        e.printStackTrace(System.out);
        System.out.printf("Termination the rest of the Threads\n");
        interrupt();
    }
}
