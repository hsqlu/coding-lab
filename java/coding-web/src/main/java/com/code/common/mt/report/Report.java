package com.code.common.mt.report;

/**
 * Created: 2016/4/12.
 * Author: Qiannan Lu
 */
public interface Report<T> {
    void report(T event);
}
