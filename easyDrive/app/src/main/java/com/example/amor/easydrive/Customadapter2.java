package com.example.amor.easydrive;

import android.content.Context;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class Customadapter2 extends ParseQueryAdapter<ParseObject> {

    public Customadapter2(Context context) {

        // Use the QueryFactory to construct a PQA that will only show
        // Todos marked as high-pri
        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            Integer nrmessage = 1;
            public ParseQuery create() {
                ParseQuery query = new ParseQuery("Messages");
                query.whereEqualTo("Number", nrmessage);
                return query;

            }
        });
    }



}