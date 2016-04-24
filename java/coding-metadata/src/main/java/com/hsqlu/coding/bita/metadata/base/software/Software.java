package com.hsqlu.coding.bita.metadata.base.software;

import com.google.common.collect.Sets;
import com.hsqlu.coding.bita.metadata.base.software.deployment.DeployedSoftware;
import com.hsqlu.coding.bita.metadata.base.typemapping.TypeSystem;
import com.hsqlu.coding.bita.metadata.model.core.Package;
import com.hsqlu.coding.plugin.IPlugin;

import java.io.File;
import java.io.Serializable;
import java.util.Set;

/**
 * Created: 2016/4/21.
 * Author: Qiannan Lu
 */
public class Software extends Package implements Serializable, IPlugin {

    private static final long serialVersionUID = -6679085793971394175L;

    private String softwareVersion;
    private String supplier;
    protected Set<DeployedSoftware> deployedSoftwareSet = Sets.newHashSet();
    protected TypeSystem typeSystem;

    @Override
    public String getPluginDir() {
        return this.getCode() + File.separator + this.getSoftwareVersion();
    }

    @Override
    public String getPluginVersion() {
        return this.getSoftwareVersion();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((softwareVersion == null) ? 0 : softwareVersion.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Software other = (Software) obj;
        if (softwareVersion == null) {
            if (other.softwareVersion != null)
                return false;
        } else if (!softwareVersion.equals(other.softwareVersion))
            return false;
        return true;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Set<DeployedSoftware> getDeployedSoftwareSet() {
        return deployedSoftwareSet;
    }

    public void setDeployedSoftwareSet(Set<DeployedSoftware> deployedSoftwareSet) {
        this.deployedSoftwareSet = deployedSoftwareSet;
    }

    public TypeSystem getTypeSystem() {
        return typeSystem;
    }

    public void setTypeSystem(TypeSystem typeSystem) {
        this.typeSystem = typeSystem;
    }
}
