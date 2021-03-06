package com.example.amor.easydrive;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

public class InboxManagerAdapter extends ParseQueryAdapter<ParseObject> {

    public InboxManagerAdapter(Context context) {
        // Use the QueryFactory to construct a PQA that will only show
        // Todos marked as high-pri
        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery create() {
                ParseQuery query = new ParseQuery("Messages");
                query.whereDoesNotExist("manager");
                return query;
            }
        });
    }


    // Customize the layout by overriding getItemView


}