package com.hsqlu.coding.bita.metadata.base.expression;

import java.io.Serializable;

/**
 * Created: 2016/4/21.
 * Author: Qiannan Lu
 */
public class ExpressionNode implements Serializable {
    private static final long serialVersionUID = -3341251610143247060L;

    protected String expressionNodeId;
    protected String javaClass;

    protected String name;
    protected String code;

    public String getExpressionNodeId() {
        return expressionNodeId;
    }

    public void setExpressionNodeId(String expressionNodeId) {
        this.expressionNodeId = expressionNodeId;
    }

    public String getJavaClass() {
        return javaClass;
    }

    public void setJavaClass(String javaClass) {
        this.javaClass = javaClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
