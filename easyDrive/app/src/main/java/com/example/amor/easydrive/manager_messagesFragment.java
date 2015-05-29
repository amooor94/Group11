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
public class manager_messagesFragment extends Fragment{

    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.lay_manager_messages, container, false);

//listen to when send message is clicked
        Button sendmessageMana = (Button)rootview.findViewById(R.id.managersendmess);
        sendmessageMana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendmessm = new Intent(getActivity(), SendMessageManager.class);
                startActivity(sendmessm);
            }
        });
//listen to when inbox is clicked
        Button checkMessage = (Button)rootview.findViewById(R.id.chcMessagebtn);
        checkMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View s) {
                Intent checkintent = new Intent(getActivity(), Inbox_manager.class);
                startActivity(checkintent);
            }
        });


        return rootview;
    }
}
