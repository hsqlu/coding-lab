package com.hsqlu.coding.concurrent;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;

public class Reordering {

    static int x = 0, y = 0, a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        Bag bag = new HashBag();
        for (int i = 0; i < 10000; i++) {
            x = y = a = b = 0;
            Thread one = new Thread() {
                public void run() {
                    a = 1;
                    x = b;
                }
            };
            Thread two = new Thread() {
                public void run() {
                    b = 1;
                    y = a;
                }
            };
            one.start();
            two.start();
            one.join();
            two.join();
            bag.add(x + "_" + y);
        }
        System.out.println(bag.getCount("0_1"));
        System.out.println(bag.getCount("1_0"));
        System.out.println(bag.getCount("1_1"));
        System.out.println(bag.getCount("0_0"));
    }
}
