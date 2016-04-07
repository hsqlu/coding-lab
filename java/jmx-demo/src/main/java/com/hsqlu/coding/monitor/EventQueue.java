package com.hsqlu.coding.monitor;

import com.hsqlu.coding.monitor.log.filter.IQuery;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created: 2016/4/7.
 * Author: Qiannan Lu
 */
public class EventQueue {
    private static ConcurrentLinkedQueue<IQuery> eventQueue = new ConcurrentLinkedQueue<>();

    public void add(IQuery o) {
        eventQueue.add(o);
    }
}
