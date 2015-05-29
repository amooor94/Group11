package com.example.amor.easydrive;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Amor on 2015-03-10.
 */
public class manager_settingsFragment extends Fragment implements Button.OnClickListener {


    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.lay_manager_settings, container, false);


//listen to when button is pressed
        Button rButton = (Button)rootview.findViewById(R.id.adddriverBtn);
        rButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);            }
        });

//listen to when button is pressed

        Button dButton = (Button)rootview.findViewById(R.id.checkdriverbtn);
        dButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ManagerUser.class);
                startActivity(intent);            }
        });

//listen to when button is pressed

        Button sButton = (Button)rootview.findViewById(R.id.createschedulebtn);
        sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateSchedule.class);
                startActivity(intent);            }
        });





        return rootview;




    }

    public void onClick(View v){
        //Nothing here yet
    }
}
