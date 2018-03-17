package com.code.schedule.model;

import com.code.schedule.model.schedule.*;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SchedulePlan {
    public static final SchedulePlan HAND_PLAN = new SchedulePlan();
    static{
        HAND_PLAN.setPlanId("1");
        HAND_PLAN.setScheduleMode(ScheduleModeType.HAND.getValue());
    }

    public static final SchedulePlan  EXEC_PLAN = new SchedulePlan();
    static{
        EXEC_PLAN.setPlanId("2");
        EXEC_PLAN.setScheduleMode(ScheduleModeType.EXEC.getValue());
    }

    private String planId;
    private String activate = "0";
    private String createUser;
    private String createTime;
    private String lastUpdateTime;
    private String lastUpdateUser;
    private String memo;
    private String scheduleMode;
    private String scheduleInterval;
    private String scheduleMonth = "0";
    private String scheduleWeek;
    private String scheduleDay = "0";
    private String scheduleHour = "0";
    private String scheduleMinute = "0";
    private String beginTime;
    private String endTime;
    private String lastSchemeStatus;
    private String lastSuccessTime;
    private int priority;
    private String scheduleStatus;

//    public ScheduleMode parseScheduleMode() {
//        ScheduleModeType modeType = ScheduleModeType.convert(getScheduleMode());
//        switch (modeType) {
//            case HAND:
//                return new HandExec();
//            case INTERVAL:
//                Date begin=null;
//                Date end=null;
//                try{
//                    begin=DateUtils.getDate(this.getBeginTime(), "yyyyMMddHHmmss");
//                    end=DateUtils.getDate(this.getEndTime(), "yyyyMMddHHmmss");
//                }catch(Exception e){
//                    e.printStackTrace();
//                    throw new java.lang.IllegalArgumentException(e.getMessage(),e);
//                }
//
//                return new IntervalExec(
//                        begin,
//                        end,
//                        Integer.parseInt(getScheduleMonth()),
//                        Integer.parseInt(getScheduleDay()),
//                        Integer.parseInt(getScheduleHour()),
//                        Integer.parseInt(getScheduleMinute()));
//            case ONCE:
//                OnceExec oe = new OnceExec();
//                oe.setBeginTime(this.getBeginTime());
//                return oe;
//            case TIMING:
//                TimingExec te = new TimingExec();
//                try {
//                    TimingIntervalType timingIntervalType = TimingIntervalType.convert(getScheduleInterval());
//                    Date beginDate = DateUtils.getDate(this.getBeginTime(), "yyyyMMddHHmmss");
//                    if(beginDate == null || DateUtils.dateCompare(new Date(), beginDate) < 0){
//                        te.setBeginTime(this.getBeginTime());
//                    }else{
//                        Calendar today = new GregorianCalendar();
//                        int year = today.get(Calendar.YEAR);
//                        int month = today.get(Calendar.MONTH);
//                        int hour = Integer.parseInt(getScheduleHour());
//                        int minute = Integer.parseInt(getScheduleMinute());
//                        switch(timingIntervalType){
//                            case INTERVAL_DAY:
//                                int day = today.get(Calendar.DAY_OF_MONTH);
//                                Calendar calDay = new GregorianCalendar(year,month,day,hour,minute);
//                                te.setBeginTime(DateUtils.getTimeStr(calDay.getTime(), DateUtils.DB_STORE_DATE));
//                                break;
//                            case INTERVAL_MONTH:
//                                int month_day = Integer.parseInt(getScheduleDay());
//                                Calendar calMonth = new GregorianCalendar(year,month,month_day,hour,minute);
//                                te.setBeginTime(DateUtils.getTimeStr(calMonth.getTime(), DateUtils.DB_STORE_DATE));
//                                break;
//                            default:
//                                Assert.fail("调度模式异常，不可能的模式值["+modeType+"]");
//                        }
//                    }
//                    te.setEndTime(this.getEndTime());
//                    te.setDay(NumFormat.$Intger(getScheduleDay()));
//                    te.setHour(NumFormat.$Intger(getScheduleHour()));
//                    te.setMinute(NumFormat.$Intger(getScheduleMinute()));
//                    te.setWeek(getScheduleWeek());
//                    te.setInterval(timingIntervalType);
//                    return te;
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                    throw new ScheduleException(LogUtil.tarceExceptionInfo(e));
//                }
//            case CRON:
//                return new CronExec(this.getScheduleMinute());
//            default:
//        }
//        return null;
//    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getActivate() {
        return activate;
    }

    public void setActivate(String activate) {
        this.activate = activate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getScheduleMode() {
        return scheduleMode;
    }

    public void setScheduleMode(String scheduleMode) {
        this.scheduleMode = scheduleMode;
    }

    public String getScheduleInterval() {
        return scheduleInterval;
    }

    public void setScheduleInterval(String scheduleInterval) {
        this.scheduleInterval = scheduleInterval;
    }

    public String getScheduleMonth() {
        return scheduleMonth;
    }

    public void setScheduleMonth(String scheduleMonth) {
        this.scheduleMonth = scheduleMonth;
    }

    public String getScheduleWeek() {
        return scheduleWeek;
    }

    public void setScheduleWeek(String scheduleWeek) {
        this.scheduleWeek = scheduleWeek;
    }

    public String getScheduleDay() {
        return scheduleDay;
    }

    public void setScheduleDay(String scheduleDay) {
        this.scheduleDay = scheduleDay;
    }

    public String getScheduleHour() {
        return scheduleHour;
    }

    public void setScheduleHour(String scheduleHour) {
        this.scheduleHour = scheduleHour;
    }

    public String getScheduleMinute() {
        return scheduleMinute;
    }

    public void setScheduleMinute(String scheduleMinute) {
        this.scheduleMinute = scheduleMinute;
    }

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

    public String getLastSchemeStatus() {
        return lastSchemeStatus;
    }

    public void setLastSchemeStatus(String lastSchemeStatus) {
        this.lastSchemeStatus = lastSchemeStatus;
    }

    public String getLastSuccessTime() {
        return lastSuccessTime;
    }

    public void setLastSuccessTime(String lastSuccessTime) {
        this.lastSuccessTime = lastSuccessTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(String scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }
}
