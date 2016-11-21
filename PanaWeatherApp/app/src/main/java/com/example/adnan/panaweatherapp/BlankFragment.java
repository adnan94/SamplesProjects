package com.example.adnan.panaweatherapp;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends android.support.v4.app.Fragment {

    ListView listView;

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_blank, container, false);
        listView = (ListView)v.findViewById(R.id.listView);
        adaptor adaptor1=new adaptor(getActivity());
        listView.setAdapter(adaptor1);
        return v;
    }


}
