package com.hsqlu.coding.bita.metadata.base.expression;

import com.hsqlu.coding.bita.metadata.model.core.Classifier;

/**
 * Created: 2016/4/21.
 * Author: Qiannan Lu
 */
public class ElementNode extends ExpressionNode {
    private static final long serialVersionUID = -712828997118774044L;

    private Classifier<?, ?> dataType;

    public ElementNode(String name, String code, Classifier<?, ?> dataType) {
        this.name = name;
        this.code = code;
        this.dataType = dataType;
    }

    public ElementNode() {
        super();
    }

    public Classifier<?, ?> getDataType() {
        return dataType;
    }

    public void setDataType(Classifier<?, ?> dataType) {
        this.dataType = dataType;
    }
}
