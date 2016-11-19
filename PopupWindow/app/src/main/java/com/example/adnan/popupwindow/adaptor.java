package com.example.adnan.popupwindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by adnan on 10/13/2016.
 */

public class adaptor extends BaseAdapter {
    Context con;
    List<String> list;
    LayoutInflater inflater;

    public adaptor(Context con, List<String> list) {
        this.con = con;
        this.list = list;
     inflater=LayoutInflater.from(con);
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
       View v= inflater.inflate(R.layout.view,parent,false);
        TextView text=(TextView)v.findViewById(R.id.textView);
        text.setText(list.get(position));
        return v;
    }
}
