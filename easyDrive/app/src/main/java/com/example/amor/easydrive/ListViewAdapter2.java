package com.example.amor.easydrive;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter2 extends BaseAdapter {

	// Declare Variables
	Context context;
	LayoutInflater inflater;
	private List<MessagesUser> messagesUserList = null;
	private ArrayList<MessagesUser> arraylist;

	public ListViewAdapter2(Context context,
                            List<MessagesUser> messagesUserList) {
		this.context = context;
		this.messagesUserList = messagesUserList;
		inflater = LayoutInflater.from(context);
		this.arraylist = new ArrayList<MessagesUser>();
		this.arraylist.addAll(messagesUserList);
	}

	public class ViewHolder {
		TextView message;
		TextView user;
		ImageView flag;
	}

	@Override
	public int getCount() {
		return messagesUserList.size();
	}

	@Override
	public Object getItem(int position) {
		return messagesUserList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup parent) {
		final ViewHolder holder;
		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.listview_item1, null);
			// Locate the TextViews in listview_item.xml
			holder.message = (TextView) view.findViewById(R.id.message);
			holder.user = (TextView) view.findViewById(R.id.user);
			// Locate the ImageView in listview_item.xml

		} else {
			holder = (ViewHolder) view.getTag();
		}


		// Set the results into TextViews
		holder.message.setText(messagesUserList.get(position).getMessage());
		holder.user.setText(messagesUserList.get(position).getUser());

		// Set the results into ImageView
		// Listen for ListView Item Click
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Send single item click data to SingleItemView Class
				Intent intent = new Intent(context, SingleItemView.class);
				// Pass all data message
				intent.putExtra("message",
						(messagesUserList.get(position).getMessage()));
				// Pass all data user
				intent.putExtra("user",
						(messagesUserList.get(position).getUser()));
				context.startActivity(intent);
			}
		});
		return view;
	}

}
