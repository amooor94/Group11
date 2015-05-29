package com.example.amor.easydrive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.List;

/**
 * Created by Amor on 2015-04-09.
 */
public class PrintadapterUSER extends ArrayAdapter<ParseObject>{
    protected Context uContext;
    protected List<ParseObject> uStatus;

    public PrintadapterUSER(Context context, List<ParseObject> status){
        super(context, R.layout.homepagecustomlayout, status);
        uContext = context;
        uStatus = status;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(uContext).inflate(
                    R.layout.homepagecustomlayout, null);
            holder = new ViewHolder();

            holder.usernameHomepage = (TextView) convertView
                    .findViewById(R.id.usernameHP);

            holder.statusHomepage = (TextView) convertView
                    .findViewById(R.id.statusHP);

            holder.dateHomepage = (TextView) convertView
                    .findViewById(R.id.dateHP);

            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        ParseObject statusObject = uStatus.get(position);

        String username = statusObject.getString("user");
        holder.usernameHomepage.setText(username);

        String status = statusObject.getString("Message");
        holder.statusHomepage.setText(status);

        String date = statusObject.getString("objectID");
        holder.dateHomepage.setText(date);

        return convertView;



    }



    public static class ViewHolder{
        TextView usernameHomepage;
        TextView statusHomepage;
        TextView dateHomepage;
    }



}
