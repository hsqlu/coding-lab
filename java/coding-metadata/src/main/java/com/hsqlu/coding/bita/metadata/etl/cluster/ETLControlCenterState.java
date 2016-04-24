package com.hsqlu.coding.bita.metadata.etl.cluster;

/**
 * Created: 2016/4/21.
 * Author: Qiannan Lu
 */
public enum ETLControlCenterState {
    NEW("初始化"),
    DISABLED("不可用"),
    RUNNING_NO_SLAVE("无执行器状态"),
    RUNNING_HAS_SLAVE("有执行器状态"),
    OVER_LOAD("超负荷"),
    DESTROY("消亡");

    ETLControlCenterState(String memo) {
        this.memo = memo;
    }

    private String memo;

    public String getMemo() {
        return memo;
    }

}
