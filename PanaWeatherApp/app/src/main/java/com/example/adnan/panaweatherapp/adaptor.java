package com.example.adnan.panaweatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Adnan on 10/30/2015.
 */
public class adaptor extends BaseAdapter {
    Context con;
    ArrayList<signature> list;
    LayoutInflater inflator;

    public adaptor(Context con) {
        this.con = con;
        inflator = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        list = Global.signature;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflator.inflate(R.layout.weather_weekly, null);

        TextView date = (TextView) view.findViewById(R.id.textView8);
        TextView day = (TextView) view.findViewById(R.id.textView9);
        TextView high = (TextView) view.findViewById(R.id.textView10);
        TextView low = (TextView) view.findViewById(R.id.textView11);
        TextView text = (TextView) view.findViewById(R.id.textView12);

        date.setText("Date :"+list.get(position).getDate());
        day.setText("Day :"+list.get(position).getDay());
        high.setText("High :"+list.get(position).getHigh());
        low.setText("Low :"+list.get(position).getLow());
        text.setText("Text :"+list.get(position).getText());


        return view;
    }
}
