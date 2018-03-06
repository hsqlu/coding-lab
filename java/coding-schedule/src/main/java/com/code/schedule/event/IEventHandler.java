package com.code.schedule.event;

import com.code.schedule.model.ScheduleMessage;

/**
 * Created: 19/04/2017.
 * Author: Qiannan Lu
 */
public interface IEventHandler extends IEventSource {
    ScheduleMessage handle(Event event);
}
