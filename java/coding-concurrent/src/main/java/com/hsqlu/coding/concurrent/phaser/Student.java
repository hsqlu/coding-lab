package com.hsqlu.coding.concurrent.phaser;

import java.util.Date;
import java.util.concurrent.Phaser;

/**
 * Created: 2016/4/26.
 * Author: Qiannan Lu
 */
public class Student implements Runnable {
    private Phaser phaser;

    public Student(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
        System.out.printf("%s: Has arrived to do the exam. %s\n", Thread.currentThread().getName(), new Date());
        phaser.arriveAndAwaitAdvance();

    }
}
