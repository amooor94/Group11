package com.example.amor.easydrive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter3 extends BaseAdapter {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    private List<Schedule> datelist = null;
    private ArrayList<Schedule> arraylist;

    public ListViewAdapter3 (Context context,
                           List<Schedule> schedulelist) {
        this.context = context;
        this.datelist = schedulelist;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<Schedule>();
        this.arraylist.addAll(schedulelist);
    }

    public class ViewHolder {
        TextView date;

    }

    @Override
    public int getCount() {
        return datelist.size();
    }

    @Override
    public Object getItem(int position) {
        return datelist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item3, null);
            // Locate the TextViews in listview_item.xml
            holder.date = (TextView) view.findViewById(R.id.date);

            // Locate the ImageView in listview_item.xml
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.date.setText(datelist.get(position).getDate());

        // Set the results into ImageView
        // Listen for ListView Item Click

        // Listen for ListView Item Click

        return view;
    }

}
