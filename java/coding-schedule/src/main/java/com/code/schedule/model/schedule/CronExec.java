package com.code.schedule.model.schedule;

import com.code.schedule.model.ScheduleModeType;

public class CronExec extends AbstractExec {
    private String cronExpression;

    public CronExec(String cronExpression) {
        this.cronExpression = cronExpression;
        this.type = ScheduleModeType.CRON;
    }

    public CronExec() {
        this.type = ScheduleModeType.CRON;
    }

    @Override
    public String getCronExpression() {
        return this.cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }
}
