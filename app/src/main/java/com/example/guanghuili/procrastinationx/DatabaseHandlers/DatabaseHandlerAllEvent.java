package com.example.guanghuili.procrastinationx.DatabaseHandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.guanghuili.procrastinationx.Constants;
import com.example.guanghuili.procrastinationx.Events.Event;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHandlerAllEvent extends SQLiteOpenHelper {
    private Context ctx;

    public DatabaseHandlerAllEvent(Context context) {
        super(context, Constants.DB_Name, null, Constants.DB_VERSION);
        this.ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ALLEVENT_TABLE = "CREATE TABLE " + Constants.TABLE_NAME_EVENT + "(" +
                                        Constants.KEY_ID_ALL_EVENT + " INTEGER PRIMARY KEY," +
                                        Constants.KEY_NAME_ALL_EVENT + " TEXT," +
                                        Constants.KEY_TYPE_ALL_EVENT + " TEXT," +
                                        Constants.KEY_DATE_ALL_EVENT + " LONG)";

        db.execSQL(CREATE_ALLEVENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_EVENT);
        onCreate(db);
    }

    //Add an Event
    public void addEvent(Event event){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.KEY_NAME_ALL_EVENT, event.getName());
        values.put(Constants.KEY_TYPE_ALL_EVENT, event.getType());
        values.put(Constants.KEY_DATE_ALL_EVENT, System.currentTimeMillis());

        //Insert values to the row
        db.insert(Constants.TABLE_NAME_EVENT, null, values);

        //debugging
        Log.d("Added to allEvent TBL","Added to allEvent TBL");
    }

    //Get an Event by id
    public Event getEvent(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Constants.TABLE_NAME_EVENT, //table name
                                 new String[]{Constants.KEY_ID_ALL_EVENT, Constants.KEY_NAME_ALL_EVENT, Constants.KEY_TYPE_ALL_EVENT, Constants.KEY_DATE_ALL_EVENT},//entries of the table
                                 Constants.KEY_ID_ALL_EVENT + " =?", //select where the id =
                                 new String[]{String.valueOf(id)}, //the id
                                 null,null,null,null);

        Event event = new Event();

        if (cursor != null){
            cursor.moveToFirst();

            event.setEventId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID_ALL_EVENT))));
            //cursor.getColumnIndex(String) get the index of the specific column in a table
            //cursor.getString(int) get the actually info at that column
            //since id in the table is an int, we gotta convert the string to int

            event.setName(cursor.getString(cursor.getColumnIndex(Constants.KEY_NAME_ALL_EVENT)));
            event.setType(cursor.getString(cursor.getColumnIndex(Constants.KEY_TYPE_ALL_EVENT)));

            //convert time to something readable
            DateFormat dateFormat = DateFormat.getDateInstance();
            String formattedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE_ALL_EVENT))));
            event.setDate(formattedDate);
        }
        return event;

    }


    //get all the events
    public List<Event> getAllEvent(){
        List <Event> eventList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Constants.TABLE_NAME_EVENT, //table name
                        new String[]{Constants.KEY_ID_ALL_EVENT, Constants.KEY_NAME_ALL_EVENT, Constants.KEY_TYPE_ALL_EVENT, Constants.KEY_DATE_ALL_EVENT},//entries of the table
                null,
             null,
                null,null,Constants.KEY_DATE_ALL_EVENT + " DESC");

        if (cursor.moveToFirst()){//1. checks if the query is empty 2. if it's not empty, move to the beginning
            do{
                Event event = new Event();

                event.setEventId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID_ALL_EVENT))));
                event.setName(cursor.getString(cursor.getColumnIndex(Constants.KEY_NAME_ALL_EVENT)));
                event.setType(cursor.getString(cursor.getColumnIndex(Constants.KEY_TYPE_ALL_EVENT)));

                //convert time to something readable
                DateFormat dateFormat = DateFormat.getDateInstance();
                String formattedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE_ALL_EVENT))));
                event.setDate(formattedDate);

                //add the event to the eventList
                eventList.add(event);

            }
            while(cursor.moveToNext());

        }
        return eventList;
    }

    //Update the event by passing a Event and returns where the id it is updated
    public int updateEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_NAME_ALL_EVENT, event.getName());
        values.put(Constants.KEY_TYPE_ALL_EVENT, event.getType());
        values.put(Constants.KEY_DATE_ALL_EVENT, System.currentTimeMillis());

        return db.update(Constants.TABLE_NAME_EVENT,
                         values,
               Constants.KEY_ID_ALL_EVENT + " =?",
                         new String[]{String.valueOf(event.getEventId())});

    }

    //Delete an event
    public void deleteEvent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Constants.TABLE_NAME_EVENT,
        Constants.KEY_ID_ALL_EVENT + " =?",
                  new String[]{String.valueOf(id)});

        db.close();
    }

    //Get the number of events in allEvent TBL
    public int getEventCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT * FROM " + Constants.TABLE_NAME_EVENT;

        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }
}
