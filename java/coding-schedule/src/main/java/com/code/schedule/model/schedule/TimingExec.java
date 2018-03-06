package com.code.schedule.model.schedule;

import com.code.schedule.model.ScheduleModeType;

public class TimingExec extends AbstractExec {

    private String beginTime;
    private String endTime;

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * @param week the week to set
     */
    public void setWeek(String week) {
        this.week = week;
    }

    /**
     * 构造器
     */
    public TimingExec() {
        this.type = ScheduleModeType.TIMING;
    }

    /**
     * @param interval the interval to set
     */
    public void setInterval(TimingIntervalType interval) {
        this.interval = interval;
    }

    /**
     * @param minute the minute to set
     */
    public void setMinute(int minute) {
        this.minute = minute;
    }

    /**
     * @param hour the hour to set
     */
    public void setHour(int hour) {
        this.hour = hour;
    }

    /**
     * @param day the day to set
     */
    public void setDay(int day) {
        this.day = day;
    }

}
