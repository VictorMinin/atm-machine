package com.solvd.atm.models;

import java.sql.Timestamp;

public class Event {

    private int eventId;
    private EventType eventType;
    private Timestamp date;

    public Event() {
    }

    public Event(EventType eventType, Timestamp date) {
        this.eventType = eventType;
        this.date = date;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return " Event Type: " + eventType +
                "\n Date: " + date;
    }
}
