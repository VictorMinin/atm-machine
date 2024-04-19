package com.solvd.atm.models;

public class EventType {

    private int eventTypeId;
    private String eventName;

    public EventType() {
    }

    public EventType(String eventName) {
        this.eventName = eventName;
    }

    public int getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(int eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    @Override
    public String toString() {
        return "Event Name: " + eventName;
    }
}
