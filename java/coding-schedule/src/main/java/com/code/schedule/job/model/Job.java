package com.code.schedule.job.model;

/**
 * Created: 19/04/2017.
 * Author: Qiannan Lu
 */
public interface Job {
    boolean isShutDown();

    void setShutDown(boolean shutDown);

    void enterExecQueue();

    boolean isEnterExecQueue();

    String getJobType();

    long getJobRunNo();

    String getJobName();

    String getJobId();

    String getCurrentStatus();

    void setCurrentStatus(String currentStatus);

    String getStartTime();

    void setStartTime(String startTime);

    String getEndTime();

    void setEndTime(String endTime);

    String getExecInfo();

    void setExecInfo(String execInfo);

    String getScheduleMode();

    void setScheduleMode(String scheduleMode);

    void addWaitCount();

    long getWaitCount();

    void setWaitCount(long waitCount);
}
