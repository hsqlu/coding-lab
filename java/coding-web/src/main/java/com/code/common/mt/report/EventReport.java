package com.code.common.mt.report;

/**
 * Created: 2016/4/12.
 * Author: Qiannan Lu
 */
public interface EventReport<S extends EventWrapper> extends Report<S> {
    S getSource();
}
