package com.code.schedule.model.schedule;

/**
 * Created: 19/04/2017.
 * Author: Qiannan Lu
 */
public enum TimingIntervalType {
    INTERVAL_DAY("1","日"),
    INTERVAL_WEEK("2","周"),
    INTERVAL_MONTH("3","月");

    private final String value;
    private final String memo;

    TimingIntervalType(String value,String memo) {
        this.value = value;
        this.memo = memo;
    }

    public String getValue(){
        return this.value;
    }

    public String getMemo(){
        return this.memo;
    }

    public static TimingIntervalType convert(String value){
        for (TimingIntervalType type : TimingIntervalType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
