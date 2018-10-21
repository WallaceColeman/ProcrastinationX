package com.example.guanghuili.procrastinationx;

public class Event {
    private String name;
    private String type;
    private String date;
    private int EventId;


    public Event(){

    }


    public Event(String name, String type, String date, int EventId){
        this.name = name;
        this.type = type;
        this.date = date;
        this.EventId = EventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getEventId() {
        return EventId;
    }

    public void setEventId(int eventId) {
        EventId = eventId;
    }



}
