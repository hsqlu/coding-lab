package com.hsqlu.coding.concurrent;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created: 2016/4/26.
 * Author: Qiannan Lu
 */
public class NetworkConnectionsLoader implements Runnable {
    @Override
    public void run() {
        System.out.printf("Beginning network connections loading loading: %s\n", new Date());
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Network connections loading has finished: %s\n", new Date());
    }
}
