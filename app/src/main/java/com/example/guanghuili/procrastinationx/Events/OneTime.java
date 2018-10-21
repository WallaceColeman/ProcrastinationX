package com.example.guanghuili.procrastinationx;

public class OneTime extends Event {
    private String oneTimeToDoList;
    private int oneTimeToDoListId;
    private String dueDate;
    private String dueTime;

    public OneTime(){

    }
    public OneTime(int oneTimeToDoListId, String name, String oneTimeToDoList, String type, String date, String dueDate, String dueTime, int EventId) {
        super(EventId, name, type, date);
        this.oneTimeToDoList = oneTimeToDoList;
        this.oneTimeToDoListId = oneTimeToDoListId;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
    }

    public String getOneTimeToDoList() {
        return oneTimeToDoList;
    }

    public void setOneTimeToDoList(String oneTimeToDoList) {
        this.oneTimeToDoList = oneTimeToDoList;
    }

    public int getOneTimeToDoListId() {
        return oneTimeToDoListId;
    }

    public void setOneTimeToDoListId (int OneTimeToDoListId) {
        this.oneTimeToDoListId = OneTimeToDoListId;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDueTime() {
        return dueTime;
    }

    public void setDueTime(String dueTime) {
        this.dueTime = dueTime;
    }
}
