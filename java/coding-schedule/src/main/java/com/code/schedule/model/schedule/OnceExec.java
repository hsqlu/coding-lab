package com.code.schedule.model.schedule;


import com.code.schedule.model.ScheduleModeType;

public class OnceExec extends AbstractExec {
    /**
     * 开始时间
     */
    private String beginTime;

    /**
     * @return the beginTime
     */
    public String getBeginTime() {
        return beginTime;
    }

    /**
     * @param beginTime the beginTime to set
     */
    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * 构造器
     */
    public OnceExec() {
        this.type = ScheduleModeType.ONCE;
    }
}
