package com.code.common.mt.event;

import com.code.common.mt.report.EventWrapper;

/**
 * Created: 2016/4/19.
 * Author: Qiannan Lu
 */
public interface EventHandler {
    void handle(EventWrapper eventWrapper);
}
