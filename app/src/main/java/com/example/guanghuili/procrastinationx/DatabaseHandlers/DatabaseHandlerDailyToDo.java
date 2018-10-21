package com.example.guanghuili.procrastinationx.DatabaseHandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.guanghuili.procrastinationx.Constants;
import com.example.guanghuili.procrastinationx.Events.DailyToDo;
import com.example.guanghuili.procrastinationx.Events.Event;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHandlerDailyToDo extends SQLiteOpenHelper {
    private Context ctx;

    public DatabaseHandlerDailyToDo(Context context) {
        super(context, Constants.DB_Name, null, Constants.DB_VERSION);
        this.ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DAILYTODO_TABLE = "CREATE TABLE " + Constants.TABLE_NAME_DAILY + "(" +
                Constants.KEY_ID_DAILY + " INTEGER PRIMARY KEY," +
                Constants.KEY_NAME_DAILY + " TEXT," +
                Constants.KEY_TODOLIST_DAILY + " TEXT," +
                Constants.KEY_TYPE_DAILY + " TEXT," +
                Constants.KEY_DATE_DAILY + " LONG," +
                Constants.KEY_EVENTID_DAILY + " INTEGER," +
                "FOREIGN KEY(" + Constants.KEY_EVENTID_DAILY + ") REFERENCES " + Constants.TABLE_NAME_EVENT + "(" + Constants.KEY_ID_ALL_EVENT + "))";

        db.execSQL(CREATE_DAILYTODO_TABLE);
        Log.d("DailyToDo Table Created", "DailyToDo Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_DAILY);
        onCreate(db);
    }


    //Add an DailyEvent
    public void addDailyEvent(DailyToDo dailyEvent) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.KEY_NAME_DAILY, dailyEvent.getName());
        values.put(Constants.KEY_TODOLIST_DAILY, dailyEvent.getDailyToDoList());
        values.put(Constants.KEY_TYPE_DAILY, dailyEvent.getType());
        values.put(Constants.KEY_DATE_DAILY, dailyEvent.getDate());
        values.put(Constants.KEY_EVENTID_DAILY, dailyEvent.getEventId());

        //Insert values to the row
        db.insert(Constants.TABLE_NAME_DAILY, null, values);

        //debugging
        Log.d("Added to DailyToDo TBL", "Added to DailyToDo TBL");
    }


    //Get an DailyEvent by id
    public Event getDailyEvent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Constants.TABLE_NAME_DAILY, //table name
                new String[]{Constants.KEY_ID_DAILY, Constants.KEY_NAME_DAILY, Constants.KEY_TODOLIST_DAILY, Constants.KEY_TYPE_DAILY, Constants.KEY_DATE_DAILY, Constants.KEY_EVENTID_DAILY},//entries of the table
                Constants.KEY_ID_DAILY + " =?", //select where the id =
                new String[]{String.valueOf(id)}, //the id
                null, null, null, null);

        DailyToDo dailyEvent = new DailyToDo();

        if (cursor != null) {
            cursor.moveToFirst();

            dailyEvent.setDailyToDoListId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID_DAILY))));
            dailyEvent.setName(cursor.getString(cursor.getColumnIndex(Constants.KEY_NAME_DAILY)));
            dailyEvent.setDailyToDoList(cursor.getString(cursor.getColumnIndex(Constants.KEY_TODOLIST_DAILY)));
            dailyEvent.setType(cursor.getString(cursor.getColumnIndex(Constants.KEY_TYPE_DAILY)));

            //convert time to something readable
            DateFormat dateFormat = DateFormat.getDateInstance();
            String formattedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE_DAILY))));
            dailyEvent.setDate(formattedDate);

            dailyEvent.setEventId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_EVENTID_DAILY))));
        }
        return dailyEvent;
    }


    //get all the events
    public List<Event> getAllDailyEvent() {
        List<Event> dailyEventList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Constants.TABLE_NAME_DAILY, //table name
                new String[]{Constants.KEY_ID_DAILY, Constants.KEY_NAME_DAILY, Constants.KEY_TODOLIST_DAILY, Constants.KEY_TYPE_DAILY, Constants.KEY_DATE_DAILY, Constants.KEY_EVENTID_DAILY},
                null,
                null,
                null, null, null, null);

        if (cursor.moveToFirst()) {//1. checks if the query is empty 2. if it's not empty, move to the beginning
            do {
                DailyToDo dailyEvent = new DailyToDo();

                dailyEvent.setDailyToDoListId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID_DAILY))));
                dailyEvent.setName(cursor.getString(cursor.getColumnIndex(Constants.KEY_NAME_DAILY)));
                dailyEvent.setDailyToDoList(cursor.getString(cursor.getColumnIndex(Constants.KEY_TODOLIST_DAILY)));
                dailyEvent.setType(cursor.getString(cursor.getColumnIndex(Constants.KEY_TYPE_DAILY)));

                //convert time to something readable
                DateFormat dateFormat = DateFormat.getDateInstance();
                String formattedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE_DAILY))));
                dailyEvent.setDate(formattedDate);

                dailyEvent.setEventId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_EVENTID_DAILY))));

                //add the event to the eventList
                dailyEventList.add(dailyEvent);

            }
            while (cursor.moveToNext());

        }
        return dailyEventList;
    }


    //Update the event by passing a Event and returns where the id it is updated
    public int updateDailyEvent(DailyToDo dailyEvent) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Constants.KEY_NAME_DAILY, dailyEvent.getName());
        values.put(Constants.KEY_TODOLIST_DAILY, dailyEvent.getDailyToDoList());
        values.put(Constants.KEY_TYPE_DAILY, dailyEvent.getType());
        values.put(Constants.KEY_DATE_DAILY, System.currentTimeMillis());
        values.put(Constants.KEY_EVENTID_DAILY, dailyEvent.getEventId());

        return db.update(Constants.TABLE_NAME_DAILY,
                values,
                Constants.KEY_ID_DAILY + " =?",
                new String[]{String.valueOf(dailyEvent.getDailyToDoListId())});

    }


    //Delete an event
    public void deleteDailyEvent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Constants.TABLE_NAME_DAILY,
                Constants.KEY_ID_DAILY + " =?",
                new String[]{String.valueOf(id)});

        db.close();
    }


    //Get the number of events in allEvent TBL
    public int getDailyEventCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT * FROM " + Constants.TABLE_NAME_DAILY;

        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }
}