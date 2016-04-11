package com.hsqlu.coding.concurrent;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created: 2016/4/11.
 * Author: Qiannan Lu
 */
public class Philosopher extends Thread {
    private final Chopstick left;
    private final Chopstick right;
    private Random random;

    public Philosopher(Chopstick left, Chopstick right) {
        this.left = left;
        this.right = right;
    }

    public void run() {
        try {
            while (true) {
                Thread.sleep(random.nextInt(1000));
                synchronized (left) {
                    synchronized (right) {
                        Thread.sleep(random.nextInt(1000));
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
