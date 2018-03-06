package com.code.schedule.model;

import java.util.Set;

/**
 * Created: 19/04/2017.
 * Author: Qiannan Lu
 */
public interface JobMeta {
    String TYPE_STEP_JOB = "STEP_JOB";

    String ACTIVE = "1";

    String INACTIVE = "0";

    String SCHEDULE_STATUS_EXEC = "1";

    String SCHEDULE_STATUS_WAIT = "0";

    String getScheduleStatus();

    void setScheduleStatus(String scheduleStatus);

    String getJobId();

    String getJobName();

    Set<SchedulePlan> getSchedulePlans();

    String getJobType();

    String getActivate();

    void setActivate(String activate);

    boolean isActivate();

    String getLastStartTime();

    void setLastStartTime(String lastStartTime);

    String getLastEndTime();

    void setLastEndTime(String lastEndTime);

    String getLastSchemeStatus();

    void setLastSchemeStatus(String lastSchemeStatus);

    String getAlarmMethod();

    void setAlarmMethod(String alarmMethod);

    String getAlarmTarget();

    void setAlarmTarget(String alarmTarget);
}
