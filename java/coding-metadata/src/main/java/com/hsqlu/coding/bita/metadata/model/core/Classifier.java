package com.hsqlu.coding.bita.metadata.model.core;

import com.google.common.collect.Maps;
import com.hsqlu.coding.bita.metadata.base.typemapping.TypeSystem;

import java.io.Serializable;
import java.util.Map;

/**
 * Created: 2016/4/21.
 * Author: Qiannan Lu
 */
public class Classifier<OWNER extends Namespace, FEATURE extends Feature> extends Namespace<OWNER> implements Serializable {
    private static final long serialVersionUID = -7752083216001469316L;

    private String featureQuantifier;
    private TypeSystem typeSystem;
    protected Map<String, FEATURE> features = Maps.newTreeMap();

    public void addFeature(FEATURE feature) {
        feature.setOwner(this);
        this.features.put(feature.getCode(), feature);
    }

    public FEATURE getFeature(String featureName) {
        return features.get(featureName);
    }

    public TypeSystem getTypeSystem() {
        return typeSystem;
    }

    public void setTypeSystem(TypeSystem typeSystem) {
        this.typeSystem = typeSystem;
    }

    public String getFeatureQuantifier() {
        return featureQuantifier;
    }

    public void setFeatureQuantifier(String featureQuantifier) {
        this.featureQuantifier = featureQuantifier;
    }

    public Map<String, FEATURE> getFeatures() {
        return features;
    }

    public void setFeatures(Map<String, FEATURE> features) {
        this.features = features;
    }
}
