package com.example.adnanahmed.swipyrefreshlayout;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.item, R.id.textView, list);
        listView.setAdapter(adapter);
        addList();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                addList();
            }
        });
    }

    public void addList() {
        list.add(list.size()+"Sumbal");
        list.add(list.size()+"Nimra");
        list.add(list.size()+"Maryam");
        list.add(list.size()+"Ayesha");
        list.add(list.size()+"Natasha");
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }
}
