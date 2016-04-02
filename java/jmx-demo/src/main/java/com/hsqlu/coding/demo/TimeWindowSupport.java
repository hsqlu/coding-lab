package com.hsqlu.coding.demo;

/**
 * Created: 2016/3/28.
 * Author: Qiannan Lu
 */
public class TimeWindowSupport {
    final long timeWindow;

    TimeWindowSupport(long timeWindow) {
        this.timeWindow = timeWindow;
    }

    long currentSlot() {
        return System.currentTimeMillis() / timeWindow;
    }
}
