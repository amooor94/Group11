package com.example.amor.easydrive;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

public class CustomAdapter extends ParseQueryAdapter<ParseObject> {

    public CustomAdapter(Context context) {
        // Use the QueryFactory to construct a PQA that will only show
        // Users marked as wrk are currently in Schedule
        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery create() {
                ParseQuery<ParseUser> query = ParseUser.getQuery();
                query.whereEqualTo("wrk", true);
                return query;
            }
        });
    }

    // Customize the layout by overriding getItemView


}