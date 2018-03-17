package com.code.schedule.model;

import com.code.schedule.model.schedule.TimingIntervalType;

/**
 * Created: 19/04/2017.
 * Author: Qiannan Lu
 */
public interface ScheduleMode {
    /**
     * 获取调度方式
     * @return ScheduleModeType
     * @author:lwei
     */
    ScheduleModeType getType();

    /**
     * @return the week
     */
    String getWeek();

    /**
     * @return the interval
     */
    TimingIntervalType getInterval();

    /**
     * @return the minute
     */
    int getMinute();

    /**
     * @return the month
     */
    int getMonth();

    /**
     * @return the hour
     */
    int getHour();

    /**
     * @return the day
     */
    int getDay();

    String getCronExpression();
}
