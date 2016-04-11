package com.hsqlu.coding.concurrent;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created: 2016/4/11.
 * Author: Qiannan Lu
 */
public class ConditionalPhilosopher extends Thread {
    private boolean eating;

    private ConditionalPhilosopher left;
    private ConditionalPhilosopher right;

    private ReentrantLock table;
    private Condition condition;
    private Random random;

    public ConditionalPhilosopher(ReentrantLock table) {
        eating = false;
        this.table = table;
        condition = table.newCondition();
        random = new Random();
    }

    public void setLeft(ConditionalPhilosopher left) {
        this.left = left;
    }

    public void setRight(ConditionalPhilosopher right) {
        this.right = right;
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();
                eat();
            }
        } catch (InterruptedException e) {
        }
    }

    private void eat() throws InterruptedException {
        table.lock();
        try {
            while (left.eating || right.eating) {
                condition.await();
            }
            eating = true;
        } finally {
            table.unlock();
        }
        Thread.sleep(1000);
    }

    private void think() throws InterruptedException {
        table.lock();
        try {
            eating = false;
            left.condition.signal();
            right.condition.signal();
        } finally {
            table.unlock();
        }
        Thread.sleep(1000);
    }
}
