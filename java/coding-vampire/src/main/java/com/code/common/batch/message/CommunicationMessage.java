package com.code.common.batch.message;

public class CommunicationMessage {
    private MessageType messageType;

    private int organizationLevel;
    private String message;
    private String sender;

    public static CommunicationMessage errorMessage(String sender, int organizationLevel, String message) {
        return new CommunicationMessage(MessageType.ERROR, sender, organizationLevel, message);
    }

    public static CommunicationMessage finishMessage(String sender, int organizationLevel) {
        return new CommunicationMessage(MessageType.FINISH, sender, organizationLevel, "");
    }

    private CommunicationMessage(MessageType messageType, String sender, int organizationLevel, String message) {
        this.message = message;
        this.messageType = messageType;
        this.sender = sender;
        this.organizationLevel = organizationLevel;
    }

    public boolean isLeaderMessage(int organizationLevel) {
        return this.organizationLevel < organizationLevel;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public int getOrganizationLevel() {
        return organizationLevel;
    }

    public String getMessage() {
        return message;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
}
