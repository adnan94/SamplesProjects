package com.example.adnanahmed.geofencing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Adnan Ahmed on 1/9/2017.
 */

public class Adaptor extends BaseAdapter{

    LayoutInflater layoutInflater;
    Context context;
    ArrayList<pojo> list;

    public Adaptor(Context context, ArrayList<pojo> list) {
        this.context = context;
        this.list = list;
        layoutInflater=LayoutInflater.from(context);
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

        View view=layoutInflater.inflate(R.layout.item,null);
        TextView lat=(TextView)view.findViewById(R.id.latitude);
        TextView id=(TextView)view.findViewById(R.id.id);
        TextView lon=(TextView)view.findViewById(R.id.longitude);
        TextView radius=(TextView)view.findViewById(R.id.radius);

        lat.setText(""+list.get(position).getLatitude());
        lon.setText(""+list.get(position).getLongitude());
        radius.setText(""+list.get(position).getRadiuse());
        id.setText(list.get(position).getId());

        return view;
    }
}
