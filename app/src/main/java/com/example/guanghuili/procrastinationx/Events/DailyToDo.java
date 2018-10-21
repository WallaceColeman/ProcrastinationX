package com.example.guanghuili.procrastinationx.Events;

public class DailyToDo extends Event {
    private String dailyToDoList;
    private int dailyToDoListId;

    //TODO need to be changed to a proper format of time
    private final String DUETIME = "23:59";

    public DailyToDo(){
    }

    public DailyToDo(int dailyToDoListId, String name, String dailyToDoList, String type, String date, int EventId) {
        super(EventId, name, type, date);
        this.dailyToDoList = dailyToDoList;
        this.dailyToDoListId = dailyToDoListId;
    }

    public String getDailyToDoList() {
        return dailyToDoList;
    }

    public void setDailyToDoList(String dailyToDoList) {
        this.dailyToDoList = dailyToDoList;
    }

    public int getDailyToDoListId() {
        return dailyToDoListId;
    }

    public void setDailyToDoListId(int dailyToDoListId) {
        this.dailyToDoListId = dailyToDoListId;
    }

    public String getDUETIME() {
        return DUETIME;
    }


}