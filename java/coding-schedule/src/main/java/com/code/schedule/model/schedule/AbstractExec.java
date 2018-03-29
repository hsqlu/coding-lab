package com.code.schedule.model.schedule;

import com.code.schedule.model.ScheduleMode;
import com.code.schedule.model.ScheduleModeType;

/**
 * Created: 19/04/2017.
 * Author: Qiannan Lu
 */
public abstract class AbstractExec implements ScheduleMode {

    protected ScheduleModeType type;

    protected TimingIntervalType interval;

    protected int minute;

    protected int hour;

    protected int day;

    protected int month;

    protected String week;

    /**
     * @return the scheduleModeType
     */
    public ScheduleModeType getType() {
        return type;
    }

    /**
     * @return the week
     */
    public String getWeek() {
        return week;
    }

    /**
     * @return the interval
     */
    public TimingIntervalType getInterval() {
        return interval;
    }

    /**
     * @return the minute
     */
    public int getMinute() {
        return minute;
    }

    /**
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * @return the hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * @return the day
     */
    public int getDay() {
        return day;
    }

    public String getCronExpression() {
        return null;
    }
}
