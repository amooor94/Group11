package com.example.amor.easydrive;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.parse.ParseObject;
import org.w3c.dom.Text;
import java.util.List;
/**
 * Created by Wei on 2015-04-19.
 */
public class StatusAdapter extends ArrayAdapter<ParseObject>{
    protected Context mContext;
    protected List<ParseObject> mStatus;
    public StatusAdapter(Context context, List<ParseObject> status){
        super(context, R.layout.randomlayout, status);
        mContext = context;
        mStatus = status;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.randomlayout, null);
            holder = new ViewHolder();
            holder.usernameHomepage = (TextView) convertView
                    .findViewById(R.id.usernameHP);
            holder.statusHomepage = (TextView) convertView
                    .findViewById(R.id.statusHP);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        ParseObject statusObject = mStatus.get(position);
        String username = statusObject.getString("user");
        holder.usernameHomepage.setText(username);
        String status = statusObject.getString("Message");
        holder.statusHomepage.setText(status);
        return convertView;
    }
    public static class ViewHolder{
        TextView usernameHomepage;
        TextView statusHomepage;
    }
}