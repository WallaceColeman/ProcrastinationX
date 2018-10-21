package com.example.guanghuili.procrastinationx;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    private void createEventDialog() {
        //********Get widgets**********
        etName_newEvent = (EditText)findViewById(R.id.etNameID_newEvent);

        rgEvents_newEvent = (RadioGroup) findViewById(R.id.rgEventsID_newEvent);
        rbDailyToDo_newEvent = (RadioButton) findViewById(R.id.rbDailyToDoID_newEvent);
        rbOneTime_newEvent = (RadioButton) findViewById(R.id.rbOneTimeID_newEvent);
        rbLongTerm_newEvent = (RadioButton) findViewById(R.id.rbLongTermID_newEvent);

        btnCancel_newEvent = (Button) findViewById(R.id.btnCancelID_newEvent);

        dialogBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.new_event,null);

        dialogBuilder.setView(view);
        dialog = dialogBuilder.create();
        dialog.show();

        /*setOnClick Method doesn't work
          created btnCancel_newEventOnClick() for when cancel is clicked;
          created btnSave_newEventOnclick() for when save is clicked
         */


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

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

    public void btnCancel_newEventOnClick(View view){
        dialog.dismiss();
    }

    public void btnSave_newEventOnClick(View view){
        dialog.dismiss();
    }


}
