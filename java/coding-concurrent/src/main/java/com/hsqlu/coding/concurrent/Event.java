package com.hsqlu.coding.concurrent;

import java.util.Date;

/**
 * Created: 2016/4/26.
 * Author: Qiannan Lu
 */
public class Event {
    private Date date;
    private String event;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
