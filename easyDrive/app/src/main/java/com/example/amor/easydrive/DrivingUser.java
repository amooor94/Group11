package com.example.amor.easydrive;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DrivingUser extends Activity implements TextToSpeech.OnInitListener, TextToSpeech.OnUtteranceCompletedListener{
    // Declare Variables
    TextToSpeech tts;
    ListView listview;
    List<ParseObject> ob;
    ProgressDialog mProgressDialog;
    ListViewAdapter3 adapter;
    public List<String> list = new ArrayList<String>();
    private List<Schedule> schedulelist = null;
    protected Button next;
    protected Button back;
    int rakanre = 0;
    int counter;
    Button inc, dec, pindah;
    TextView result;
    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        // Get the view from listview_main.xml
        setContentView(R.layout.activity_driving_user);
        // Execute RemoteDataTask AsyncTask
        new RemoteDataTask().execute();
        counter = 4;
        inc =(Button)findViewById(R.id.nextbtn);
        dec = (Button)findViewById(R.id.backbtn);
        pindah = (Button)findViewById(R.id.nextbtn);
        result = (TextView)findViewById(R.id.Result);



        next = (Button)findViewById(R.id.nextbtn);
        back = (Button)findViewById(R.id.backbtn);

//listen to when next button is clicked
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rakanre < list.size()-1) {//function that shows the next message if the button is clicked
                    String showMessage = list.get(rakanre + 1).toString();
                    result.setText(" " + showMessage);
                    rakanre++;
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//function that shows the previous message
                if (rakanre != 0) {
                    String showMessage = list.get(rakanre - 1).toString();
                    result.setText(" " + showMessage);
                    rakanre--;
                }
            }
        });
        final Button speak = (Button) findViewById(R.id.speakbtn);
        tts = new TextToSpeech(DrivingUser.this, DrivingUser.this);
        //  final TextView tv = (TextView) findViewById(R.id.Result);
        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String speaktts = result.getText().toString();//reads message out loud if button is pressed
                if(!tts.isSpeaking()) {
                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, speaktts );
                    tts.speak(speaktts, TextToSpeech.QUEUE_ADD, params);
                    speak.setVisibility(Button.GONE);
                } else {
                    tts.stop();
                }
            }
        });

    }
    // RemoteDataTask AsyncTask
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                        "Messages");

                query.orderByDescending("createdAt");
                ob = query.find();
                for (ParseObject schedule : ob) {
                    list.add(schedule.get("Message").toString());
                    Log.e(null, schedule.get("Message").toString() + "\n");
                }

            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

    }

    @Override
    public void onUtteranceCompleted(String utteranceId) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(DrivingUser.this, "Read complete", Toast.LENGTH_SHORT).show();
                Button btn = (Button) findViewById(R.id.speakbtn);
                btn.setVisibility(Button.VISIBLE);
            }
        });
    }
    @Override
    public void onInit(int status) {
        tts.setOnUtteranceCompletedListener(this);
    }
    @Override
    protected void onDestroy() {
        if(tts!=null) {
            tts.stop();
            tts.shutdown();
            tts=null;
        }
        super.onDestroy();
    }
}