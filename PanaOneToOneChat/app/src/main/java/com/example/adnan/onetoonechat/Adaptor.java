package com.example.adnan.onetoonechat;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Adnan on 10/6/2015.
 */
public class Adaptor extends BaseAdapter {
    Context con;
    ArrayList<messages> list = new ArrayList<messages>();
    LayoutInflater inflater;
    String userName;
    TextView name, time, message;

    public Adaptor(Context con, String userName) {


        this.con = con;
        this.userName = userName;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.chat_ui, null);
        View v2 = inflater.inflate(R.layout.chat_ui_right, null);
//Log.d("username++++++++++++",userName);
        if (userName.equals(list.get(position).getName().toString())) {
            name = (TextView) v.findViewById(R.id.nameUI);
            time = (TextView) v.findViewById(R.id.timeUI);
            message = (TextView) v.findViewById(R.id.messageUI);
            name.setText(list.get(position).getName());
            time.setText(list.get(position).getTime());
            message.setText(list.get(position).getMess());
            return v;

        } else {
            name = (TextView) v2.findViewById(R.id.nameUIv);
            time = (TextView) v2.findViewById(R.id.timeUIv);
            message = (TextView) v2.findViewById(R.id.messageUIv);
            name.setText(list.get(position).getName());
            time.setText(list.get(position).getTime());
            message.setText(list.get(position).getMess());
            return v2;
//
        }
//
//  return null;

//
    }

    public void obj(messages o) {
        list.add(o);
        notifyDataSetChanged();
    }
}
