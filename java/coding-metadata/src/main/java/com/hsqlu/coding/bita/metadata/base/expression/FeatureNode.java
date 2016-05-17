package com.hsqlu.coding.bita.metadata.base.expression;

import com.google.common.collect.Maps;

import java.io.Serializable;
import java.util.Map;

/**
 * Created: 2016/4/21.
 * Author: Qiannan Lu
 */
public class FeatureNode extends ExpressionNode implements Serializable {
    private static final long serialVersionUID = 1285350548734072769L;

    private Map<String, ExpressionNode> arguments = Maps.newLinkedHashMap();

    public ExpressionNode getArgument(String argumentName) {
        return arguments.get(argumentName);
    }

    public void addArgument(ExpressionNode expressionNode) {
        arguments.put(expressionNode.getCode(), expressionNode);
    }

    public Map<String, ExpressionNode> getArguments() {
        return arguments;
    }

    public void setArguments(Map<String, ExpressionNode> arguments) {
        this.arguments = arguments;
    }
}
