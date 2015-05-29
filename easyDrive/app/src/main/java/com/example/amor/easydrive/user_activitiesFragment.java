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
public class user_activitiesFragment extends Fragment {
    View rootview;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.lay_user_activities, container, false);






        Button sendmessage = (Button)rootview.findViewById(R.id.sendmessage);
        sendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendmessm = new Intent(getActivity(), User_SendMessage.class);
                startActivity(sendmessm);
            }
        });



        Button Inbox = (Button)rootview.findViewById(R.id.checkmessage);
        Inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takemetoInbox = new Intent(getActivity(), Inbox_user.class);
                startActivity(takemetoInbox);
            }
        });






        Button schedule = (Button)rootview.findViewById(R.id.userschbtn);
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takemetoSchedules = new Intent(getActivity(), ScheduleUser.class);
                startActivity(takemetoSchedules);
            }
        });




        return rootview;





    }



}
