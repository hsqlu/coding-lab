package com.code.common.mt.report;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created: 2016/4/13.
 * Author: Qiannan Lu
 */
public class EventReportScheduler {
    private ArrayBlockingQueue<EventWrapper> eventWrapperQueue;

    private CopyOnWriteArrayList<EventWrapper> eventWrapperList;

    public EventReportScheduler(ArrayBlockingQueue<EventWrapper> eventWrapperQueue) {
        this.eventWrapperQueue = eventWrapperQueue;
    }

/*    public void schedule() {
        ExecutorService service = Executors.newScheduledThreadPool(5);
        service.submit((Runnable) () -> {

        });
    }*/

    public void addEvent(EventWrapper eventWrapper) {
        eventWrapperQueue.add(eventWrapper);
    }
}
