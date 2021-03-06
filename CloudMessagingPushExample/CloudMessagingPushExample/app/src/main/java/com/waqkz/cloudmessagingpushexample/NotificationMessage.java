package com.waqkz.cloudmessagingpushexample;

/**
 * Created by Waqas on 10/17/2016.
 */

public class NotificationMessage {

    private String message;
    private String pushId;
    private String UUID;

    public NotificationMessage() {
    }

    public NotificationMessage(String message, String pushId, String UUID) {
        this.message = message;
        this.pushId = pushId;
        this.UUID = UUID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }
}
