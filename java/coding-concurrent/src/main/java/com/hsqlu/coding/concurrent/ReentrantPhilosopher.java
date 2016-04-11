package com.hsqlu.coding.concurrent;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created: 2016/4/11.
 * Author: Qiannan Lu
 */
public class ReentrantPhilosopher extends Thread {
    private final ReentrantLock leftChopstick;
    private final ReentrantLock rightChopstick;
    private Random random;

    public ReentrantPhilosopher(ReentrantLock leftChopstick, ReentrantLock rightChopstick) {
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
        random = new Random();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(random.nextInt(1000));
                leftChopstick.lock();
                try {
                    if (rightChopstick.tryLock(1000, TimeUnit.MILLISECONDS)) {
                        try {
                            Thread.sleep(random.nextInt(1000));
                        } finally {
                            rightChopstick.unlock();
                        }
                    }
                } finally {
                    leftChopstick.unlock();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
