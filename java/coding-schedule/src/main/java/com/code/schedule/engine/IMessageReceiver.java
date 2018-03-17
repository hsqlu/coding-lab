package com.code.schedule.engine;

import com.code.schedule.event.IEventSource;
import com.code.schedule.job.model.Job;
import com.code.schedule.model.ScheduleMessage;

public interface IMessageReceiver extends IEventSource {
    ScheduleMessage interruptJob(Job job);

    ScheduleMessage jobStartProcess(Job job);

    void jobFinishProcess(String jobMetaId, ScheduleMessage message);

    void dynamicJobFinishProcess(String jobId, ScheduleMessage message);
}
