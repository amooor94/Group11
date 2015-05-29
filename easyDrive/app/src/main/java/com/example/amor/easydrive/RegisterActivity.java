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
import com.parse.SignUpCallback;


public class RegisterActivity extends Activity {

    protected EditText mUsername;
    protected EditText mfName;
    protected EditText mEmail;
    protected EditText mPassword;
    protected Button mRegisterButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //initialize
        mUsername = (EditText)findViewById(R.id.usernameRegisterEditText);
        mfName = (EditText)findViewById(R.id.fNameRegisterEditText);
        mEmail = (EditText)findViewById(R.id.EmailRegisterEditText);
        mPassword = (EditText)findViewById(R.id.PasswordRegisterEditText);
        mRegisterButton = (Button)findViewById(R.id.registerButton);

        //listen to register button click
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the username, password and email and convert them to string
                String username = mUsername.getText().toString().trim();
                String fName = mfName.getText().toString();
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();



                //store users in parse
                ParseUser user = new ParseUser();
                user.setUsername(username);
                user.setEmail(email);
                user.put("fName", ParseObject.createWithoutData("Full Name", fName));
                user.setPassword(password);


                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e==null){
                            //user signed up successfully
                            Toast.makeText(RegisterActivity.this, "Driver succesfully added!", Toast.LENGTH_LONG).show();

                            //take user to hompage
                            Intent UserHome = new Intent(RegisterActivity.this, ManagerActivityPage.class);
                            startActivity(UserHome);


                        }else{
                            //there was an error sigup the user
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
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
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
