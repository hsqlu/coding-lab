package com.hsqlu.coding.bita.metadata.model.core;

import java.io.Serializable;

/**
 * Created: 2016/4/21.
 * Author: Qiannan Lu
 */
public class ModelElement<OWNER extends ModelElement> implements Comparable<ModelElement>, Serializable {
    private static final long serialVersionUID = 2083510309971692268L;

    protected String id;
    protected String name;
    protected Integer version;
    protected String type;
    //// TODO: 2016/4/21 what's means by this property
    protected String memo;
    protected String code;
    protected OWNER owner;
    protected String ownerId;

    /**
     * default constructor
     */
    public ModelElement() {
        this.type = this.getClass().getSimpleName();
    }


    /**
     * full constructor
     */
    public ModelElement(String name, Integer version, String memo, String code) {
        this.name = name;
        this.version = version;
        this.type = this.getClass().getSimpleName();
        this.memo = memo;
        this.code = code;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        ModelElement other = (ModelElement) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        if (version == null) {
            if (other.version != null)
                return false;
        } else if (!version.equals(other.version))
            return false;
        return true;
    }

    @Override
    public int compareTo(ModelElement o) {
        return this.getCode().compareTo(o.getCode());
    }

    public OWNER getOwner() {
        return owner;
    }

    public void setOwner(OWNER owner) {
        this.owner = owner;
        if (owner != null) {
            this.ownerId = owner.getId();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
