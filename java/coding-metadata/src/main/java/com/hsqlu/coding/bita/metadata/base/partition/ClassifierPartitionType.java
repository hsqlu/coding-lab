package com.hsqlu.coding.bita.metadata.base.partition;

/**
 * Created: 2016/4/21.
 * Author: Qiannan Lu
 */
public enum ClassifierPartitionType {
    PHYSICAL("PHYSICAL"),
    LOGIC("LOGIC");

    ClassifierPartitionType(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
}
