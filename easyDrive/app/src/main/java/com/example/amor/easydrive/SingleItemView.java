package com.example.amor.easydrive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class SingleItemView extends Activity implements TextToSpeech.OnInitListener, TextToSpeech.OnUtteranceCompletedListener {
	// Declare Variables
    TextToSpeech tts;
    String message;
    String user;
    String position;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from singleitemview.xml
		setContentView(R.layout.singleitemview);

        Intent i = getIntent();
        // Get the result of message
        message = i.getStringExtra("message");
        // Get the result of user
        user = i.getStringExtra("user");



        // Locate the TextViews in singleitemview.xml
        TextView txtrank = (TextView) findViewById(R.id.message);
        TextView txtcountry = (TextView) findViewById(R.id.user);


        // Set results to the TextViews
        txtrank.setText(message);
        txtcountry.setText(user);



        // Capture position and set results to the ImageView
		// Passes flag images URL into ImageLoader.class

        tts = new TextToSpeech(SingleItemView.this, SingleItemView.this);
        final Button speak = (Button) findViewById(R.id.button1);
        final TextView tv = (TextView) findViewById(R.id.message);

        speak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String speaktts = tv.getText().toString();

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

    @Override
    public void onUtteranceCompleted(String utteranceId) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(SingleItemView.this, "Read complete", Toast.LENGTH_SHORT).show();
                Button btn = (Button) findViewById(R.id.button1);
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



