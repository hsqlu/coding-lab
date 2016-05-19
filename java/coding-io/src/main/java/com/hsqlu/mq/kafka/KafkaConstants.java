package com.hsqlu.mq.kafka;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created: 2016/5/19.
 * Author: Qiannan Lu
 */
public final class KafkaConstants {
    private static final List<String> TOPICS;

    static {
        TOPICS = Lists.newArrayList();
        TOPICS.add("foo");
        TOPICS.add("bar");
    }
}
