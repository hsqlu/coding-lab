package com.code.schedule.model;

/**
 * Created: 19/04/2017.
 * Author: Qiannan Lu
 */
public enum ScheduleModeType {
    /**
     * <font color="#2a00ff">"0"</font>,<font color="#2a00ff">"手动执行"</font>
     */
    HAND("0", "手动执行"),
    /**
     * <font color="#2a00ff">"3"</font>,<font color="#2a00ff">"间隔执行"</font>
     */
    INTERVAL("3", "间隔执行"),
    /**
     * <font color="#2a00ff">"2"</font>,<font color="#2a00ff">"单次执行"</font>
     */
    ONCE("2", "单次执行"),
    /**
     * <font color="#2a00ff">"1"</font>,<font color="#2a00ff">"定时执行"</font>
     */
    EXEC("4", "被执行"),
    /**
     * <font color="#2a00ff">"1"</font>,<font color="#2a00ff">"定时执行"</font>
     */
    TIMING("1", "定时执行"),
    /**
     * <font color="#2a00ff">"5"</font>,<font color="#2a00ff">"表达式执行"</font>
     */
    CRON("5", "Cron表达式执行");

    private final String value;
    private final String memo;

    /**
     * @param value 值
     * @param memo  说明
     */
    ScheduleModeType(String value, String memo) {
        this.value = value;
        this.memo = memo;
    }

    public static ScheduleModeType convert(String value) {
        for (ScheduleModeType type : ScheduleModeType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public String getMemo() {
        return memo;
    }
}
