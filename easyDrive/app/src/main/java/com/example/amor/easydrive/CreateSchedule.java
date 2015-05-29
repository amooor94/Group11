package com.example.amor.easydrive;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class CreateSchedule extends Activity {

    protected EditText mDrivername;
    protected EditText mDatebox;
    protected EditText mDestination;
    protected Button mSchedulebtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_schedule);


        //initialize
        mDrivername = (EditText)findViewById(R.id.DriverName);
        mDatebox = (EditText)findViewById(R.id.Datebox);
        mDestination = (EditText)findViewById(R.id.Destinationbox);
        mSchedulebtn = (Button)findViewById(R.id.createschbtn);

        //listen to when button is clicked
        mSchedulebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Drivername = mDrivername.getText().toString().trim();
                String Date = mDatebox.getText().toString();
                String Destination = mDestination.getText().toString();

                ParseObject statusObject = new ParseObject("Schedules");
                statusObject.put("Driver", Drivername);
                statusObject.put("Date", Date);
                statusObject.put("Destination", Destination);
                statusObject.saveInBackground();

                statusObject.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e==null){
                            //user signed up successfully
                            Toast.makeText(CreateSchedule.this, "Schedule created!", Toast.LENGTH_LONG).show();

                            Intent UserHome = new Intent(CreateSchedule.this, ManagerActivityPage.class);
                            startActivity(UserHome);


                        }else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(CreateSchedule.this);
                            builder.setMessage(e.getMessage());
                            builder.setTitle("Sorry");
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();


                        }
                    }
                });

            }
        });

    }}