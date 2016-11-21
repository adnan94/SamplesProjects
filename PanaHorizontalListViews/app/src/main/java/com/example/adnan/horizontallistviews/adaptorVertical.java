package com.example.adnan.horizontallistviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by adnan on 4/1/2016.
 */
public class adaptorVertical extends BaseAdapter {

    ArrayList<imagee> verticalList;
    Context con;
    LayoutInflater inflater;
/**
 * yaar tum purany task sahi se ker rahy ho nahi or aagy barhy jaa rahy ho...
 * BC yea us time ok chal raha tha ab pta nhai kiya hogaya isko :'(
 * baahra haal
 * isko choro lekin recyler ka click item sahi kero....
 * ok wo krta hun
 * good keep it up
 * */
    public adaptorVertical(ArrayList<imagee> verticalList, Context con) {
        this.verticalList = verticalList;
        this.con = con;
        inflater = LayoutInflater.from(con);
    }

    @Override
    public int getCount() {
        return verticalList.size();
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
        View v = inflater.inflate(R.layout.layout, null);
        ImageView iv = (ImageView) v.findViewById(R.id.imageView);
        iv.setImageResource(verticalList.get(position).getImage());
        return v;
    }
}
