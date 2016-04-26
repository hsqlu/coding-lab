package com.hsqlu.coding.concurrent;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created: 2016/4/26.
 * Author: Qiannan Lu
 */
public class DataSourcesLoader implements Runnable {
    @Override
    public void run() {
        System.out.printf("Beginning data sources loading: %s\n", new Date());
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Data sources loading has finished: %s\n", new Date());
    }
}
