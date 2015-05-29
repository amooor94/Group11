package com.example.amor.easydrive;

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

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class User_SendMessage extends ActionBarActivity {

    protected EditText UsertxtFieldmsg;
    protected Button UserSendMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__send_message);
        //initialize
        UsertxtFieldmsg = (EditText)findViewById(R.id.txtFieldmsg1);
        UserSendMessage = (Button)findViewById(R.id.sMessage1);


        //Listen to when status button is clicked
        UserSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get the current user
                ParseUser currentUser = ParseUser.getCurrentUser();
                String currentUserUsername = currentUser.getUsername();


                //get the status user has enteretd, conver it to a string
                String newStatus = UsertxtFieldmsg.getText().toString();

                //Save the status in parse
                ParseObject statusObject = new ParseObject("Messages");
                statusObject.put("Message",newStatus);
                statusObject.put("user", currentUserUsername);
                statusObject.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){
                            //succesfully stored the new status in parse
                            Toast.makeText(User_SendMessage.this, "Message Sent!", Toast.LENGTH_LONG).show();

                            //take user home
                            Intent takeUserHome =new Intent(User_SendMessage.this, UserActivityPage.class);
                            startActivity(takeUserHome);


                        }else{
                            //there was a problem storn new status, advice user
                            AlertDialog.Builder builder = new AlertDialog.Builder(User_SendMessage.this);
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
        getMenuInflater().inflate(R.menu.menu_send_message_manager, menu);
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