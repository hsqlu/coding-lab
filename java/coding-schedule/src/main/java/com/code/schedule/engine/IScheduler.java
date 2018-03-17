package com.code.schedule.engine;

import com.code.schedule.event.IEventSource;
import com.code.schedule.model.JobMeta;

/**
 * Created: 19/04/2017.
 * Author: Qiannan Lu
 */
public interface IScheduler extends IEventSource {
    String DEFAULT_NAME = "Quartz调度器";

    void startSchedule();

    /**
     * 将作业调度计划加入调度器调度
     * @param jobMeta  作业
     */
    void addSchedule(JobMeta jobMeta);

    /**
     * 将作业调度计划从调度器删除
     * @param jobMeta  作业
     */
    void removeSchedule(JobMeta jobMeta);
}
