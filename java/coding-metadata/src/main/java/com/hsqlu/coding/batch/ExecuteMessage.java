package com.hsqlu.coding.batch;

/**
 * Created: 2016/5/6.
 * Author: Qiannan Lu
 */
public class ExecuteMessage {
    private OrganizationState messageType;

    private int organizationLevel;

    private String message;

    private String sender;

    public ExecuteMessage(OrganizationState messageType, String sender, int organizationLevel, String message) {
        this.messageType = messageType;
        this.sender = sender;
        this.organizationLevel = organizationLevel;
        this.message = message;
    }

    public int getOrganizationLevel() {
        return organizationLevel;
    }

    public String getMessage() {
        return message;
    }

    public static ExecuteMessage errorMessage(String sender, int organizationLevel, String message) {
        return new ExecuteMessage(OrganizationState.ERROR, sender, organizationLevel, message);
    }

    public static ExecuteMessage stoppingMessage(String sender, int organizationLevel, String message) {
        return new ExecuteMessage(OrganizationState.STOPPING, sender, organizationLevel, message);
    }

    public static ExecuteMessage finishMessage(String sender, int organizationLevel) {
        return new ExecuteMessage(OrganizationState.FINISHED, sender, organizationLevel, "");
    }

    public boolean isLeaderMessage(int organizationLevel) {
        return this.organizationLevel < organizationLevel;
    }

    public OrganizationState getMessageType() {
        return messageType;
    }

    public void setMessageType(OrganizationState messageType) {
        this.messageType = messageType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }


    @Override
    public String toString() {
        return "消息 [sender=" + sender + ", messageType=" + messageType + ", message=" + message + "]";
    }
}
