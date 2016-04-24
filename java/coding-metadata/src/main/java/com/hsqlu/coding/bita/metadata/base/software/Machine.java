package com.hsqlu.coding.bita.metadata.base.software;

import com.hsqlu.coding.bita.metadata.model.core.ModelElement;

import java.io.Serializable;
import java.text.MessageFormat;

/**
 * Created: 2016/4/21.
 * Author: Qiannan Lu
 */
public class Machine extends ModelElement implements Serializable {
    private static final long serialVersionUID = -7106599601831325541L;

    private String machineId;
    private String ipAddress;
    private String macAddress;

    public Machine(String ipAddress, String macAddress) {
        this.ipAddress = ipAddress;
        this.macAddress = macAddress;
    }

    public Machine() {
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ipAddress == null) ? 0 : ipAddress.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Machine other = (Machine) obj;
        if (ipAddress == null) {
            if (other.ipAddress != null)
                return false;
        } else if (!ipAddress.equals(other.ipAddress))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return MessageFormat.format("machineId={0}, ipAddress={1}, macAddress={2}", machineId, ipAddress, macAddress);
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
}
