/**
 * 版权所有：厦门市巨龙软件工程有限公司
 * Copyright 2010 Xiamen Dragon Software Eng. Co. Ltd.
 * All right reserved.
 * ====================================================
 * 文件名称: IntervalExec.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2010-7-22			李炜(创建:创建文件)
 * ====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 */
package com.code.schedule.model.schedule;

import com.code.schedule.model.ScheduleModeType;

import java.util.Date;

public class IntervalExec extends AbstractExec {

    private Date beginTime;

    private Date endTime;

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public IntervalExec() {
        this.minute = 1;
        this.hour = 0;
        this.day = 0;
        this.month = 0;
    }

    /**
     * @param minute
     * @param hour
     * @param day
     * @param month
     */
    public IntervalExec(int month, int day, int hour, int minute) {
        super();
        this.minute = minute;
        this.hour = hour;
        this.day = day;
        this.month = month;
        this.type = ScheduleModeType.INTERVAL;
    }

    /**
     * @param minute
     * @param hour
     * @param day
     * @param month
     */
    public IntervalExec(Date beginTime, Date endTime, int month, int day, int hour, int minute) {
        super();
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.minute = minute;
        this.hour = hour;
        this.day = day;
        this.month = month;
        this.type = ScheduleModeType.INTERVAL;
    }

}
