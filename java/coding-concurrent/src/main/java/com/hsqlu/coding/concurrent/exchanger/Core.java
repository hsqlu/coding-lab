package com.hsqlu.coding.concurrent.exchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created: 2016/4/27.
 * Author: Qiannan Lu
 */
public class Core {
    public static void main(String[] args) {
        List<String> producerBuffer = new ArrayList<>();
        List<String> consumerBuffer = new ArrayList<>();

        Exchanger<List<String>> exchanger = new Exchanger<>();

        Producer producer = new Producer(producerBuffer, exchanger);
        Consumer consumer = new Consumer(consumerBuffer, exchanger);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);
        producerThread.start();
        consumerThread.start();
    }
}
