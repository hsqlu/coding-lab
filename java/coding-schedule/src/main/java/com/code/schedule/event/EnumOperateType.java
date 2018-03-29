package com.code.schedule.event;

public enum EnumOperateType {
    INIT_ENGINE("1", "启动引擎"),

    ACTIVE_JOB("2", "激活作业"),
    EXEC_JOB("3", "执行作业"),
    SHUTDOWN_JOB("4", "终止作业"),
    STOP_JOB("5", "停止作业"),
    STOP_ENGINE("6", "停止引擎"),
    EXEC_ENGINE("7", "引擎运行中");

    EnumOperateType(String value, String memo) {
        this.value = value;
        this.memo = memo;
    }

    private final String value;
    private final String memo;

    public static EnumOperateType convert(String value) {
        for (int i = 0; i < EnumOperateType.values().length; i++) {
            if (EnumEngineStatus.values()[i].getValue().equals(value)) {
                return EnumOperateType.values()[i];
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.memo;
    }
}
