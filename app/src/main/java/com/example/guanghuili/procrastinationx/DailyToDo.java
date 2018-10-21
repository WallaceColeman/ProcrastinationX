package com.example.guanghuili.procrastinationx;

public class DailyToDo extends Event {
    private String toDoList;
    private int toDoListId;

    public DailyToDo(){
    }

    public DailyToDo(String name, String type, String date, int EventId, String toDoList, int toDoListId) {
        super(name, type, date, EventId);
        this.toDoList = toDoList;
        this.toDoListId = toDoListId;
    }

    public String getToDoList() {
        return toDoList;
    }

    public void setToDoList(String toDoList) {
        this.toDoList = toDoList;
    }

    public int getToDoListid() {
        return toDoListId;
    }

    public void setToDoListid(int toDoListid) {
        this.toDoListId = toDoListid;
    }


}
