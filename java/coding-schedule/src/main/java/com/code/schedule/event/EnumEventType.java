package com.code.schedule.event;


public enum EnumEventType {
    DYNAMIC_JOB_START("动态作业启动事件"),
    DYNAMIC_JOB_FINISH("动态作业结束事件"),
    DYNAMIC_JOB_PROCESS("动态作业处理事件"),

    SINGLE_JOB_EXEC("单一作业运行事件"),
    SINGLE_JOB_START("单一作业启动事件"),
    SINGLE_JOB_FINISH("单一作业结束事件"),
    SINGLE_JOB_BLOCK_NOTIFY("单一作业阻塞唤醒事件"),

    CHILD_JOB_START("子作业启动事件"),
    CHILD_JOB_FINISH("子作业结束事件"),
    CHILD_JOB_BLOCK_NOTIFY("子作业阻塞唤醒事件"),

    STEP_JOB_START("步骤作业启动事件"),
    STEP_JOB_EXEC("步骤作业运行事件"),
    STEP_JOB_FINISH("步骤作业结束事件"),
    STEP_JOB_SHUTDOWN("步骤作业终止事件"),

    JOB_BLOCK_NOTIFY("作业阻塞唤醒事件"),
    JOB_SHUT_DOWN("作业终止事件"),
    JOB_STOP("作业停用事件"),
    JOB_ACTIVE("作业激活事件");

    private String eventName;

    EnumEventType(String eventName) {
        this.eventName = eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
