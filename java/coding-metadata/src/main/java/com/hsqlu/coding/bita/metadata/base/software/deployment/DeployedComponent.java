package com.hsqlu.coding.bita.metadata.base.software.deployment;

import com.hsqlu.coding.bita.metadata.base.software.Machine;
import com.hsqlu.coding.bita.metadata.model.core.Package;

import java.io.Serializable;

/**
 * Created: 2016/4/21.
 * Author: Qiannan Lu
 */
public class DeployedComponent extends Package<DeployedSoftware> implements Serializable {
    private static final long serialVersionUID = -2586190811383748711L;

    protected DeployedSoftware deployedSoftware;
    protected Machine machine;
    protected Integer port;
    private String isMaster;

    public DeployedComponent() {
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((machine == null) ? 0 : machine.hashCode());
        result = prime * result + ((port == null) ? 0 : port.hashCode());
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
        DeployedComponent other = (DeployedComponent) obj;
        if (machine == null) {
            if (other.machine != null)
                return false;
        } else if (!machine.equals(other.machine))
            return false;
        if (port == null) {
            if (other.port != null)
                return false;
        } else if (!port.equals(other.port))
            return false;
        return true;
    }

    public DeployedComponent(DeployedSoftware deployedSoftware, Machine machine) {
        this.deployedSoftware = deployedSoftware;
        this.machine = machine;
    }

    public DeployedSoftware getDeployedSoftware() {
        return deployedSoftware;
    }

    public void setDeployedSoftware(DeployedSoftware deployedSoftware) {
        this.deployedSoftware = deployedSoftware;
        this.setOwner(deployedSoftware);
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getIsMaster() {
        return isMaster;
    }

    public void setIsMaster(String isMaster) {
        this.isMaster = isMaster;
    }
}
