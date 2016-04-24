package com.hsqlu.coding.bita.metadata.base.software.deployment;

import com.google.common.collect.Sets;
import com.hsqlu.coding.bita.metadata.base.software.Software;
import com.hsqlu.coding.bita.metadata.model.core.Package;

import java.io.Serializable;
import java.util.Set;

/**
 * Created: 2016/4/21.
 * Author: Qiannan Lu
 */
public class DeployedSoftware<COMPONENT extends DeployedComponent> extends Package<Software> implements Serializable {
    private static final long serialVersionUID = 1809936172230260857L;

    private Software software;
    private Set<COMPONENT> deployedComponents = Sets.newHashSet();
    private Set<COMPONENT> masterComponents = Sets.newHashSet();

    public DeployedSoftware() {
    }

    public void addCluster(COMPONENT deployedComponent) {
        deployedComponent.setDeployedSoftware(this);
        deployedComponents.add(deployedComponent);
    }

    public COMPONENT getMaster() {
        for (COMPONENT masterComponent : masterComponents) {
            return masterComponent;
        }
        return null;
    }

    public Software getSoftware() {
        return software;
    }

    public void setSoftware(Software software) {
        this.software = software;
        this.setOwner(software);
    }

    public Set<COMPONENT> getDeployedComponents() {
        return deployedComponents;
    }

    public void setDeployedComponents(Set<COMPONENT> deployedComponents) {
        this.deployedComponents = deployedComponents;
    }

    public Set<COMPONENT> getMasterComponents() {
        return masterComponents;
    }

    public void setMasterComponents(Set<COMPONENT> masterComponents) {
        this.masterComponents = masterComponents;
    }

    @Override
    public String toString() {
        return "DeployedSoftware [name=" + this.getName() + ", depId=" + this.getId() + "]";
    }
}
