package com.example.amor.easydrive;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import java.util.List;
public class Inbox_manager extends ListActivity {
    protected List<ParseObject> mStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_inbox_manager);
        ParseUser currentUser = ParseUser.getCurrentUser();

        if (currentUser != null) {
            //show user the homepage
            ParseQuery<ParseObject>query = new ParseQuery<ParseObject>("Messages");
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> status, ParseException e) {
                    if(e == null){
                        //success
                        mStatus = status;
                        StatusAdapter adapter = new StatusAdapter(getListView().getContext(),mStatus);
                        setListAdapter(adapter);
                    }else {
                        //there was a problem. Alert user
                    }
                }
            });
        } else {
            // show the signup or login screen
            Intent takeusertologin = new Intent(this, LoginActivity.class);
            startActivity(takeusertologin);
        }
    }
}
