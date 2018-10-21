package com.example.guanghuili.procrastinationx.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.guanghuili.procrastinationx.DatabaseHandlers.DatabaseHandlerAllEvent;
import com.example.guanghuili.procrastinationx.Events.Event;
import com.example.guanghuili.procrastinationx.R;

public class MainActivity extends AppCompatActivity {
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    private EditText etName_newEvent;
    private RadioGroup rgEvents_newEvent;
    private RadioButton rbDailyToDo_newEvent;
    private RadioButton rbOneTime_newEvent;
    private RadioButton rbLongTerm_newEvent;
    private Button btnCancel_newEvent;
    private Button btnSave_newEvent;

    private DatabaseHandlerAllEvent db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHandlerAllEvent(this);
        //bypassActivity();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createEventDialog();
            }
        });
    }

    //auto generated
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    //auto generated
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createEventDialog() {
        //********Get widgets**********
        dialogBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.new_event,null);

        etName_newEvent = (EditText)view.findViewById(R.id.etNameID_newEvent);

        rgEvents_newEvent = (RadioGroup) view.findViewById(R.id.rgEventsID_newEvent);
        rbDailyToDo_newEvent = (RadioButton) view.findViewById(R.id.rbDailyToDoID_newEvent);
        rbOneTime_newEvent = (RadioButton) view.findViewById(R.id.rbOneTimeID_newEvent);
        rbLongTerm_newEvent = (RadioButton) view.findViewById(R.id.rbLongTermID_newEvent);

        btnCancel_newEvent = (Button) view.findViewById(R.id.btnCancelID_newEvent);
        btnSave_newEvent = (Button) view.findViewById(R.id.btnSaveID_newEvent);


        dialogBuilder.setView(view);
        dialog = dialogBuilder.create();
        dialog.show();

        /*setOnClick Method doesn't work
          created btnCancel_newEventOnClick() for when cancel is clicked;
          created btnSave_newEventOnclick() for when save is clicked
         */

    }

    public void btnCancel_newEventOnClick(View view){
        dialog.dismiss();
    }


    public void btnSave_newEventOnClick(View view)
    {
        Log.d("EditText",etName_newEvent.getText().toString());
        Log.d("RadioButtonID",String.valueOf(rgEvents_newEvent.getCheckedRadioButtonId()));
        if(!etName_newEvent.getText().toString().isEmpty() && rgEvents_newEvent.getCheckedRadioButtonId() != -1){
            saveNewEventToDB(view);
        }

    }

    public void saveNewEventToDB(View view){
        Event event = new Event();
        String newEventName = etName_newEvent.getText().toString();


        RadioButton rbSelected = (RadioButton) rgEvents_newEvent.findViewById(rgEvents_newEvent.getCheckedRadioButtonId());
        String newEventType = rbSelected.getText().toString();

        event.setName(newEventName);
        event.setType(newEventType);

        //save to db
        db.addEvent(event);

        Snackbar.make(view,"Event Saved!",Snackbar.LENGTH_LONG).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                startActivity(new Intent(MainActivity.this,OverviewActivity.class));
            }
        },1200);

    }



    public void bypassActivity() {
        if(db.getEventCount() > 0){
            Intent intent = new Intent(MainActivity.this, OverviewActivity.class);
            startActivity(intent);
            finish();
        }
    }


}
