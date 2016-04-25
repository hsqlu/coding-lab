package com.hsqlu.coding.bita.metadata.base.partition;

import com.google.common.collect.Sets;
import com.hsqlu.coding.bita.metadata.model.core.Classifier;
import com.hsqlu.coding.bita.metadata.model.core.Feature;
import com.hsqlu.coding.bita.metadata.base.expression.ExpressionNode;

import java.util.Set;

/**
 * Created: 2016/4/21.
 * Author: Qiannan Lu
 */
public class ClassifierPartition<OWNER extends Classifier> extends Classifier<OWNER, Feature<Classifier>> {

    private static final long serialVersionUID = -8020459035115132937L;

    private Classifier classifier;
    private ExpressionNode expressionNode;
    private String partitionType;
    private Set<PartitionRange> ranges = Sets.newHashSet();

    public void addRange(PartitionRange range) {
        range.setOwner(this);
        range.setPartition(this);
        ranges.add(range);
    }

    public Classifier getClassifier() {
        return classifier;
    }

    public void setClassifier(Classifier classifier) {
        this.classifier = classifier;
    }

    public ExpressionNode getExpressionNode() {
        return expressionNode;
    }

    public void setExpressionNode(ExpressionNode expressionNode) {
        this.expressionNode = expressionNode;
    }

    public String getPartitionType() {
        return partitionType;
    }

    public void setPartitionType(String partitionType) {
        this.partitionType = partitionType;
    }

    public Set<PartitionRange> getRanges() {
        return ranges;
    }

    public void setRanges(Set<PartitionRange> ranges) {
        this.ranges = ranges;
    }
}
