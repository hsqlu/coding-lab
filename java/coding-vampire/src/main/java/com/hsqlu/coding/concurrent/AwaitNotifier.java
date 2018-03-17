package com.hsqlu.coding.concurrent;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * Created: 2016/7/7.
 * Author: Qiannan Lu
 */
public class AwaitNotifier<NOTIFIER, NOTIFY_INFO> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AwaitNotifier.class);

    private String name;

    private Set<NOTIFIER> notifiers = Sets.newHashSet();

    private Map<NOTIFIER, NOTIFY_INFO> notifyInfo;

    private CountDownLatch notifyCount;

    private AwaitNotifyToDo<NOTIFIER, NOTIFY_INFO> notifyTodo;

    public static final AwaitNotifyToDo NOTHING = new AwaitNotifyToDo() {
        @Override
        public void notifyDoSomething(Object notifier, Object notifyInfo) {}

        @Override
        public void awaitDoSomething(Map notifyInfo) {}

        @Override
        public void finallyDoSomething(Map notifyInfo, boolean isInterrupted) {}
    };

    public AwaitNotifier(String name) {
        this(name, NOTHING);
    }

    public AwaitNotifier(String name, AwaitNotifyToDo<NOTIFIER, NOTIFY_INFO> notifyTodo) {
        this.name = name;
        this.notifyTodo = notifyTodo;
        this.notifyInfo = Maps.newConcurrentMap();
    }

    public AwaitNotifier<NOTIFIER, NOTIFY_INFO> init() {
        this.notifyCount = new CountDownLatch(notifiers.size());
        return this;
    }

    public void notify(NOTIFIER notifier,NOTIFY_INFO info) {
        notifyInfo.put(notifier, info);
        notifyTodo.notifyDoSomething(notifier, info);
        if (notifiers.contains(notifier)) {
            notifyCount.countDown();
            LOGGER.info("[{}] send message [{}] to {}, still have {} events awaiting. ", notifier, info, name, notifyCount.getCount());
        }
    }

    public void awaitNotify() {
        boolean isInterrupted = false;
        try {
            notifyCount.await();
            notifyTodo.awaitDoSomething(notifyInfo);
        } catch (InterruptedException e) {
            LOGGER.error("Thread was interrupted while await notify.", e);
            isInterrupted = true;
        } finally {
            notifyTodo.finallyDoSomething(notifyInfo, isInterrupted);
        }
    }

    public void reset() {
        notifyCount = null;
        notifyInfo.clear();
    }

    public NOTIFY_INFO getNotifyInfo(NOTIFIER notifier) {
        return notifyInfo.get(notifier);
    }

    public NOTIFY_INFO getNotifyInfo() {
        return notifyInfo.values().iterator().next();
    }

    public Collection<NOTIFY_INFO> getAllNotifyInfo() {
        return notifyInfo.values();
    }

    public int getNotifiersCount() {
        return notifiers.size();
    }

    public void addAwaitNotifier(NOTIFIER notifier) {
        notifiers.add(notifier);
    }

    public String getName() {
        return name;
    }

    public interface AwaitNotifyToDo<NOTIFIER, NOTIFY_INFO> {
        void notifyDoSomething(NOTIFIER notifier, NOTIFY_INFO notifyInfo);

        void awaitDoSomething(Map<NOTIFIER, NOTIFY_INFO> notifyInfo);

        void finallyDoSomething(Map<NOTIFIER, NOTIFY_INFO> notifyInfo, boolean isInterrupted);
    }
}
