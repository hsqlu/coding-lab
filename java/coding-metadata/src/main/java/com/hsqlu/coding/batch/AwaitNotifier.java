package com.hsqlu.coding.batch;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * Created: 2016/5/6.
 * Author: Qiannan Lu
 */
public class AwaitNotifier<NOTIFIER, NOTIFY_INFO> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AwaitNotifier.class);

    /**
     * 被通知对象名称
     */
    private String name;

    public String getName() {
        return name;
    }

    /**
     * 总共需要等待多少个通知人的集合
     */
    private Set<NOTIFIER> awaitNotifiers = Sets.newHashSet();

    /**
     * 接收到的通知消息
     */
    private Map<NOTIFIER, NOTIFY_INFO> notifyInfo;

    /**
     * 总共等待的通知人数
     */
    private CountDownLatch notifyCount;

    /**
     * 收到通知后需要做点什么
     */
    private AwaitNotifyToDo<NOTIFIER, NOTIFY_INFO> notifyToDo;

    public AwaitNotifier(String name) {
        this(name, NOTHING);
    }

    public AwaitNotifier(String name, AwaitNotifyToDo<NOTIFIER, NOTIFY_INFO> notifyToDo) {
        this.name = name;
        this.notifyToDo = notifyToDo;
        this.notifyInfo = Maps.newConcurrentMap();
    }

    public int getNotifiersSize() {
        return awaitNotifiers.size();
    }

    public void addAwaitNotifier(NOTIFIER notifier){
        awaitNotifiers.add(notifier);
    }

    public AwaitNotifier<NOTIFIER, NOTIFY_INFO> init() {
        this.notifyCount = new CountDownLatch(getNotifiersSize());
        return this;
    }

    public void awaitNotify() {
        boolean isInterrupted = false;

        try {
            this.notifyCount.await();
            notifyToDo.awaitDoSth(notifyInfo);
        } catch (InterruptedException e) {
            e.printStackTrace();
            isInterrupted = true;
        } finally {
            notifyToDo.finallyDoSth(notifyInfo, isInterrupted);
        }
    }

    public void reset() {
        this.notifyCount = null;
        this.notifyInfo.clear();
    }

    public void notify(NOTIFIER notifier, NOTIFY_INFO info) {
        notifyInfo.put(notifier, info);
        notifyToDo.notifyDoSth(notifier, info);
        if (awaitNotifiers.contains(notifier)) {
            notifyCount.countDown();
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("【" + notifier + "】发送消息[" + info + "]给【" + name + "】,"
                        + "【" + name + "】还剩[" + (notifyCount.getCount()) + "]事件需要等待！");
            }
        }
    }

    public NOTIFY_INFO getNotifyInfoByNotifier(NOTIFIER notifier) {
        return notifyInfo.get(notifier);
    }

    public NOTIFY_INFO getNotifyInfo() {
        return notifyInfo.values().iterator().next();
    }

    public Collection<NOTIFY_INFO> getAllNotifyInfo() {
        return notifyInfo.values();
    }

    public static final AwaitNotifyToDo NOTHING = new AwaitNotifyToDo() {
        @Override
        public void notifyDoSth(Object o, Object o2) {

        }

        @Override
        public void awaitDoSth(Map notifyInfo) {

        }

        @Override
        public void finallyDoSth(Map notifyInfo, boolean isInterrupted) {

        }
    };


    public interface AwaitNotifyToDo<NOTIFIER, NOTIFY_INFO> {
        void notifyDoSth(NOTIFIER notifier, NOTIFY_INFO notifyInfo);

        void awaitDoSth(Map<NOTIFIER, NOTIFY_INFO> notifyInfo);

        void finallyDoSth(Map<NOTIFIER, NOTIFY_INFO> notifyInfo, boolean isInterrupted);
    }
}
