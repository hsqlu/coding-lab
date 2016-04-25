package com.hsqlu.coding.bita.metadata.base.partition;

import com.hsqlu.coding.bita.metadata.model.core.ModelElement;

/**
 * Created: 2016/4/21.
 * Author: Qiannan Lu
 */
public class PartitionRange extends ModelElement {
    private static final long serialVersionUID = 4140851826518496901L;

    private ClassifierPartition partition;
    private String partitionValue;

    public PartitionRange() {
    }

    public PartitionRange(ClassifierPartition partition, String partitionValue) {
        this.partition = partition;
        this.partitionValue = partitionValue;
    }

    public ClassifierPartition getPartition() {
        return partition;
    }

    public void setPartition(ClassifierPartition partition) {
        this.partition = partition;
    }

    public String getPartitionValue() {
        return partitionValue;
    }

    public void setPartitionValue(String partitionValue) {
        this.partitionValue = partitionValue;
    }
}
