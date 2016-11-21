package com.example.adnan.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adnan on 8/29/2015.
 */
public class adapter extends BaseAdapter {
    LayoutInflater inflater;
    Context con;
    ArrayList<docs> d ;

    public adapter(Context con,ArrayList<docs> list)
    {
        this.con = con;
        this.d=list;
        inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return d.size();
    }

    @Override
    public docs getItem(int position) {
        return d.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(R.layout.layout, null);
        TextView type = (TextView) v.findViewById(R.id.type);
        TextView company = (TextView) v.findViewById(R.id.company);
        TextView education = (TextView) v.findViewById(R.id.education);
        TextView skills = (TextView) v.findViewById(R.id.skill);
        TextView salary = (TextView) v.findViewById(R.id.salary);
        TextView description = (TextView) v.findViewById(R.id.description);
        TextView job = (TextView) v.findViewById(R.id.job);


        type.setText("Type :" + d.get(position).getType());
        skills.setText("Skills :" + d.get(position).getSkill());
        education.setText("Education :" + d.get(position).getEducation());
        company.setText("Company :" + d.get(position).getCompany());
        description.setText("Description :" + d.get(position).getDescription());
        salary.setText("Salary :" + d.get(position).getSalary());
        job.setText("Job :" + d.get(position).getJob());

        return v;
    }

    public void add(docs o){
        d.add(o);
        notifyDataSetChanged();
    }
}