package com.example.guanghuili.procrastinationx.Events;

public class Event {
    private int EventId;
    private String name;
    private String type;
    private String date;



    public Event(){

    }


    public Event(int EventId, String name, String type, String date){
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
