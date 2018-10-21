package com.example.guanghuili.procrastinationx;

public class OneTime extends Event {
    private String toDoList;
    private int toDoListId;
    private String dueDate;
    private String dueTime;

    public OneTime(String name, String type, String date, int EventId, String toDoList, int toDoListId, String dueDate, String dueTime) {
        super(name, type, date, EventId);
        this.toDoList = toDoList;
        this.toDoListId = toDoListId;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
    }

    public String getToDoList() {
        return toDoList;
    }

    public void setToDoList(String toDoList) {
        this.toDoList = toDoList;
    }

    public int getToDoListId() {
        return toDoListId;
    }

    public void setToDoListId(int toDoListId) {
        this.toDoListId = toDoListId;
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
