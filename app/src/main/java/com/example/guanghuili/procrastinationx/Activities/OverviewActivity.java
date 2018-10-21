package com.example.guanghuili.procrastinationx.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.guanghuili.procrastinationx.DatabaseHandlers.DatabaseHandlerAllEvent;
import com.example.guanghuili.procrastinationx.Events.Event;
import com.example.guanghuili.procrastinationx.R;
import com.example.guanghuili.procrastinationx.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class OverviewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Event> eventListDB; //get the list from database
    private List<Event> eventList; //add each event separately to eventList to make sure it is adding the right thing, might be unnecessary
    private DatabaseHandlerAllEvent db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO need to implement this
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                        */
            }
        });

        db = new DatabaseHandlerAllEvent(this);
        recyclerView = (RecyclerView) findViewById(R.id.rvOverviewID_overview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        eventListDB = new ArrayList<>();
        eventList = new ArrayList<>();

        //get events from database
        eventListDB = db.getAllEvent();

        for (Event e : eventListDB){
            Event event = new Event();
            event.setEventId(e.getEventId());
            event.setName(e.getName());
            event.setType(e.getType());
            event.setDate(e.getDate());

            eventList.add(event);
        }

        recyclerViewAdapter = new RecyclerViewAdapter(this, eventList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
    }

}
