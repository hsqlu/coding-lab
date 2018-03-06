package com.code.schedule.job.strategy;

import com.code.schedule.job.model.SimpleJob;
import com.code.schedule.job.model.Job;
import com.code.schedule.model.ScheduleMessage;

/**
 * Created: 19/04/2017.
 * Author: Qiannan Lu
 */
public interface IJobStrategy {
    ScheduleMessage execute(Job job);

    void interrupt();

    boolean isInterrupt();

    void cancelJobLog(Job job,String time);

    /**
     * 告警异常消息
     * @param job 作业执行实例信息
     * @param exceptionMessage 异常消息
     */
    void alarmException(SimpleJob job, String exceptionMessage);
}
