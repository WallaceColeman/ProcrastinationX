package com.example.guanghuili.procrastinationx;

public class Constants {
    public static final int DB_VERSION = 1;
    public static final String DB_Name = "EventsDB";

    //table name and rows for allEventsTBL
    public static final String TABLE_NAME_EVENT = "AllEventTBL";
    public static final String KEY_ID_ALL_EVENT = "id";
    public static final String KEY_NAME_ALL_EVENT = "eventName";
    public static final String KEY_TYPE_ALL_EVENT = "type";
    public static final String KEY_DATE_ALL_EVENT = "dateCreated";

    //table name and rows for dailyToDoTBL
    public static final String TABLE_NAME_DAILY = "dailyToDoTBL";
    public static final String KEY_ID_DAILY = "id";
    public static final String KEY_NAME_DAILY = "eventName";
    public static final String KEY_TODOLIST_DAILY = "toDoList";
    public static final String KEY_TYPE_DAILY = "type";
    public static final String KEY_DATE_DAILY = "dateCreated";
    public static final String KEY_EVENTID_DAILY = "eventID";

    //table name and rows for oneTimeTBL
    public static final String TABLE_NAME_ONETIME = "oneTimeTBL";
    public static final String KEY_ID_ONETIME = "id";
    public static final String KEY_NAME_ONETIME = "eventName";
    public static final String KEY_TYPE_ONETIME = "type";
    public static final String KEY_TODOLIST_ONETIME = "toDoList";
    public static final String KEY_EVENTID_ONETIME = "eventID";
    public static final String KEY_DATE_ONETIME = "dateCreated";
    public static final String KEY_DUEDATE_ONETIME = "dueDate";
    public static final String KEY_DUETIME_ONETIME = "dueTime";

}
