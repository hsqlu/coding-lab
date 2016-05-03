package com.hsqlu.coding.concurrent.collections;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created: 2016/4/27.
 * Author: Qiannan Lu
 */
public class AddTask implements Runnable {
    private ConcurrentLinkedDeque<String> list;

    public AddTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 0; i < 10000; ++i) {
            list.add(name + ": Element" + i);
        }
    }
}
