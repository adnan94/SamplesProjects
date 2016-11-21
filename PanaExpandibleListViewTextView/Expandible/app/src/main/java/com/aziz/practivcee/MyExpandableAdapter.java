package com.aziz.practivcee;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by panacloud on 8/30/16.
 */
public class MyExpandableAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;
    LayoutInflater inflater;
    Activity activity;

    public MyExpandableAdapter(List<String> _listDataHeader, HashMap<String, List<String>> _listDataChild) {
        this._listDataHeader = _listDataHeader;
        this._listDataChild = _listDataChild;
    }

    public void setInflater(LayoutInflater inflater, Activity activity) {
        this.inflater = inflater;
        this.activity = activity;
    }
    @Override
    public int getGroupCount() {
        return _listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return _listDataChild.size();
    }

    @Override
    public Object getGroup(int i) {
        return this._listDataHeader.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return this._listDataChild.get(this._listDataHeader.get(i))
                .get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String headerTitle = (String) getGroup(i);
        View v=inflater.inflate(R.layout.row,viewGroup,false);
        ((CheckedTextView) v).setText(headerTitle);
        ((CheckedTextView) v).setChecked(b);

        return v;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        final String childText = (String) getChild(i, i1);

        View v1=inflater.inflate(R.layout.layout_expand,viewGroup,false);
        TextView textView = (TextView) v1.findViewById(R.id.textView1);
        textView.setText(childText);
        return v1;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
