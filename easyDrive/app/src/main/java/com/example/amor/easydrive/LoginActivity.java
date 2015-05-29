package com.example.amor.easydrive;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.swedspot.automotiveapi.AutomotiveSignal;
import android.swedspot.automotiveapi.AutomotiveSignalId;
import android.swedspot.scs.data.SCSFloat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.swedspot.automotiveapi.AutomotiveFactory;
import com.swedspot.automotiveapi.AutomotiveListener;
import com.swedspot.vil.distraction.DriverDistractionLevel;
import com.swedspot.vil.distraction.DriverDistractionListener;
import com.swedspot.vil.policy.AutomotiveCertificate;


public class LoginActivity extends Activity {




    protected EditText mUsernname;
    protected EditText mPassword;
    protected Button mLoginBtn;
    public int speedchc = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        // Enable Local Datastore.
        setContentView(R.layout.activity_login);

        final TextView ds = (TextView)findViewById(R.id.displaySpeed);

        new AsyncTask() {
            @Override
            protected Object doInBackground(Object... objects) {
                // Access to Automotive API
                AutomotiveFactory.createAutomotiveManagerInstance(
                        new AutomotiveCertificate(new byte[0]),
                        new AutomotiveListener() { // Listener that observes the Signals
                            @Override
                            public void receive(final AutomotiveSignal automotiveSignal) {
                                ds.post(new Runnable() { // Post the result back to the View/UI thread
                                    public void run() {
                                        ds.setText(String.format("Speed: %.1f km/h", ((SCSFloat) automotiveSignal.getData()).getFloatValue()));
                                        speedchc = Math.round(((SCSFloat) automotiveSignal.getData()).getFloatValue());
                                    }
                                });
                            }

                            @Override
                            public void timeout(int i) {
                            }

                            @Override
                            public void notAllowed(int i) {
                            }
                        },
                        new DriverDistractionListener() {       // Observe driver distraction level
                            @Override
                            public void levelChanged(final DriverDistractionLevel driverDistractionLevel) {
                                ds.post(new Runnable() { // Post the result back to the View/UI thread
                                    public void run() {
                                    }
                                });
                            }
                        }
                ).register(AutomotiveSignalId.FMS_WHEEL_BASED_SPEED); // Register for the speed signal
                return null;
            }
        }.execute(); // And go!
        //initialize
        mUsernname = (EditText)findViewById(R.id.usernameLoginText);
        mPassword = (EditText)findViewById(R.id.passwordLoginText);
        mLoginBtn = (Button)findViewById(R.id.LoginBtn);


        //Listen to when Login button is clicked
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the user inputs and convert to string
                final String username = mUsernname.getText().toString().trim();
                String password = mPassword.getText().toString().trim();


                //Login the user using parse
                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {

                        int drive = 10;

                        if(e == null){

                            if (username.equals("manager")) {
                                Toast.makeText(LoginActivity.this, "Logged in as Manager!", Toast.LENGTH_LONG).show();
                                Intent takeUserHome = new Intent(LoginActivity.this, ManagerActivityPage.class);
                                startActivity(takeUserHome);

                            }

                            else if (speedchc > 0) {
                                Toast.makeText(LoginActivity.this, "Driving!", Toast.LENGTH_LONG).show();
                                Intent userDrive = new Intent(LoginActivity.this, DrivingUser.class);
                                startActivity(userDrive);
                            }

                            else {
                                Toast.makeText(LoginActivity.this, "Logged in as Driverr!", Toast.LENGTH_LONG).show();
                                Intent takeUserHome = new Intent(LoginActivity.this, UserActivityPage.class);
                                startActivity(takeUserHome);

                            }



                        }else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
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


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
}
