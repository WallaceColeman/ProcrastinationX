package com.example.guanghuili.procrastinationx.DatabaseHandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.guanghuili.procrastinationx.Constants;
import com.example.guanghuili.procrastinationx.Events.Event;
import com.example.guanghuili.procrastinationx.Events.OneTime;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHandlerOneTime extends SQLiteOpenHelper {
    private Context ctx;

    public DatabaseHandlerOneTime(Context context) {
        super(context, Constants.DB_Name, null, Constants.DB_VERSION);
        this.ctx = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ONETIMETODO_TABLE = "CREATE TABLE " + Constants.TABLE_NAME_ONETIME + "(" +
                Constants.KEY_ID_ONETIME + " INTEGER PRIMARY KEY," +
                Constants.KEY_NAME_ONETIME + " TEXT," +
                Constants.KEY_TODOLIST_ONETIME + " TEXT," +
                Constants.KEY_TYPE_ONETIME + " TEXT," +
                Constants.KEY_DATE_ONETIME + " LONG," +
                Constants.KEY_DUEDATE_ONETIME + " TEXT," +
                Constants.KEY_DUETIME_ONETIME + " TEXT," +
                Constants.KEY_EVENTID_ONETIME + " INTEGER," +
                "FOREIGN KEY(" + Constants.KEY_EVENTID_ONETIME + ") REFERENCES " + Constants.TABLE_NAME_EVENT + "(" + Constants.KEY_ID_ALL_EVENT + "))";

        db.execSQL(CREATE_ONETIMETODO_TABLE);
        Log.d("OneTime Table Created", "OneTimeToDo Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_ONETIME);
        onCreate(db);
    }


    //Add an OneTimeEvent
    public void addOneTimeEvent(OneTime oneTimeEvent) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.KEY_NAME_ONETIME, oneTimeEvent.getName());
        values.put(Constants.KEY_TODOLIST_ONETIME, oneTimeEvent.getOneTimeToDoList());
        values.put(Constants.KEY_TYPE_ONETIME, oneTimeEvent.getType());
        values.put(Constants.KEY_DATE_ONETIME, oneTimeEvent.getDate());
        values.put(Constants.KEY_DUEDATE_ONETIME, oneTimeEvent.getDueDate());
        values.put(Constants.KEY_DUETIME_ONETIME, oneTimeEvent.getDueTime());
        values.put(Constants.KEY_EVENTID_ONETIME, oneTimeEvent.getEventId());

        //Insert values to the row
        db.insert(Constants.TABLE_NAME_ONETIME, null, values);

        //debugging
        Log.d("Add to OneTimeToDo TBL", "Added to OneTimeToDo TBL");
    }


    //Get an OneTimeEvent by id
    public Event getOneTimeEvent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Constants.TABLE_NAME_ONETIME, //table name
                new String[]{Constants.KEY_ID_ONETIME, Constants.KEY_NAME_ONETIME, Constants.KEY_TODOLIST_ONETIME, Constants.KEY_TYPE_ONETIME, Constants.KEY_DATE_ONETIME, Constants.KEY_DUEDATE_ONETIME, Constants.KEY_DUETIME_ONETIME, Constants.KEY_EVENTID_ONETIME},//entries of the table
                Constants.KEY_ID_ONETIME + " =?", //select where the id =
                new String[]{String.valueOf(id)}, //the id
                null, null, null, null);

        OneTime oneTimeEvent = new OneTime();

        if (cursor != null) {
            cursor.moveToFirst();

            oneTimeEvent.setOneTimeToDoListId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID_ONETIME))));
            oneTimeEvent.setName(cursor.getString(cursor.getColumnIndex(Constants.KEY_NAME_ONETIME)));
            oneTimeEvent.setOneTimeToDoList(cursor.getString(cursor.getColumnIndex(Constants.KEY_TODOLIST_ONETIME)));
            oneTimeEvent.setType(cursor.getString(cursor.getColumnIndex(Constants.KEY_TYPE_ONETIME)));

            //convert time to something readable
            DateFormat dateFormat = DateFormat.getDateInstance();
            String formattedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE_ONETIME))));
            oneTimeEvent.setDate(formattedDate);

            oneTimeEvent.setDueDate(cursor.getString(cursor.getColumnIndex(Constants.KEY_DUEDATE_ONETIME)));
            oneTimeEvent.setDueTime(cursor.getString(cursor.getColumnIndex(Constants.KEY_DUETIME_ONETIME)));

            oneTimeEvent.setEventId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_EVENTID_ONETIME))));
        }
        return oneTimeEvent;
    }


    //get all the events
    public List<Event> getAllOneTimeEvent() {
        List<Event> oneTimeEventList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Constants.TABLE_NAME_ONETIME, //table name
                new String[]{Constants.KEY_ID_ONETIME, Constants.KEY_NAME_ONETIME, Constants.KEY_TODOLIST_ONETIME, Constants.KEY_TYPE_ONETIME, Constants.KEY_DATE_ONETIME, Constants.KEY_EVENTID_ONETIME},
                null,
                null,
                null, null, null, null);

        if (cursor.moveToFirst()) {//1. checks if the query is empty 2. if it's not empty, move to the beginning
            do {
                OneTime oneTimeEvent = new OneTime();

                oneTimeEvent.setOneTimeToDoListId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID_ONETIME))));
                oneTimeEvent.setName(cursor.getString(cursor.getColumnIndex(Constants.KEY_NAME_ONETIME)));
                oneTimeEvent.setOneTimeToDoList(cursor.getString(cursor.getColumnIndex(Constants.KEY_TODOLIST_ONETIME)));
                oneTimeEvent.setType(cursor.getString(cursor.getColumnIndex(Constants.KEY_TYPE_ONETIME)));

                //convert time to something readable
                DateFormat dateFormat = DateFormat.getDateInstance();
                String formattedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE_ONETIME))));
                oneTimeEvent.setDate(formattedDate);

                oneTimeEvent.setDueDate(cursor.getString(cursor.getColumnIndex(Constants.KEY_DUEDATE_ONETIME)));
                oneTimeEvent.setDueTime(cursor.getString(cursor.getColumnIndex(Constants.KEY_DUETIME_ONETIME)));

                oneTimeEvent.setEventId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_EVENTID_ONETIME))));

            }
            while (cursor.moveToNext());

        }
        return oneTimeEventList;
    }


    //Update the event by passing a Event and returns where the id it is updated
    public int updateOneTimeEvent(OneTime oneTimeEvent) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Constants.KEY_NAME_ONETIME, oneTimeEvent.getName());
        values.put(Constants.KEY_TODOLIST_ONETIME, oneTimeEvent.getOneTimeToDoList());
        values.put(Constants.KEY_TYPE_ONETIME, oneTimeEvent.getType());
        values.put(Constants.KEY_DATE_ONETIME, System.currentTimeMillis());
        values.put(Constants.KEY_DUEDATE_ONETIME, oneTimeEvent.getDueDate());
        values.put(Constants.KEY_DUETIME_ONETIME, oneTimeEvent.getDueTime());
        values.put(Constants.KEY_EVENTID_ONETIME, oneTimeEvent.getEventId());

        return db.update(Constants.TABLE_NAME_ONETIME,
                values,
                Constants.KEY_ID_ONETIME + " =?",
                new String[]{String.valueOf(oneTimeEvent.getOneTimeToDoListId())});

    }


    //Delete an event
    public void deleteOneTimeEvent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Constants.TABLE_NAME_ONETIME,
                Constants.KEY_ID_ONETIME + " =?",
                new String[]{String.valueOf(id)});

        db.close();
    }


    //Get the number of events in allEvent TBL
    public int getOneTimeEventCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT * FROM " + Constants.TABLE_NAME_ONETIME;

        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }
}