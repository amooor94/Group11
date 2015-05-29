package com.example.amor.easydrive;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseUser;

public class app extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);//connects the app to parse, which is our database
        Parse.initialize(this, "XYKm55v5tV3BfgPPoGtIgSi52muFTRf0kqvnrw66", "oeTlB9ftDYyQ5NdNsYdpkh2E7YVDv03u4qsvDxx2");


    }

}