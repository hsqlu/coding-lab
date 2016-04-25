package com.hsqlu.coding.bita.metadata.base.typemapping;

import com.google.common.collect.Maps;
import com.hsqlu.coding.bita.metadata.base.software.Software;
import com.hsqlu.coding.bita.metadata.model.core.Classifier;
import com.hsqlu.coding.bita.metadata.model.core.Package;
import com.hsqlu.coding.plugin.IPackage;

import java.util.Map;

/**
 * Created: 2016/4/21.
 * Author: Qiannan Lu
 */
public class TypeSystem extends Package<Software> implements IPackage {
    private static final long serialVersionUID = -8731354950458261872L;

    /**solr类型系统*/
    public static final String SOLR = "solr";
    public static final String STANDARD = "STANDARD";

    private String typeVersion;
    private Map<String, Classifier<?, ?>> types = Maps.newHashMap();
    private Map<String, Classifier<?, ?>> athTypes = Maps.newHashMap();

    private boolean isTypeChanged = false;

    public void addType(Classifier<?, ?> type) {
        type.setTypeSystem(this);
        types.put(type.getCode(), type);
    }

    public Classifier<?, ?> getType(String typeName) {
        return types.get(typeName);
    }

    private void changeTypes() {
        for (Object o : types.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            Classifier classifier = (Classifier) entry.getValue();
            athTypes.put(classifier.getId(), classifier);
        }
    }

    public Map<String, Classifier<?, ?>> getAthTypes() {
        if (!isTypeChanged) {
            changeTypes();
        }
        return athTypes;
    }

    public Map<String, Classifier<?, ?>> getTypes() {
        return types;
    }

    public void setTypes(Map<String, Classifier<?, ?>> types) {
        this.types = types;
    }

    public String getTypeVersion() {
        return typeVersion;
    }

    public void setTypeVersion(String typeVersion) {
        this.typeVersion = typeVersion;
    }
}
