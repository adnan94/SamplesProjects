package com.example.adnan.panarecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    ArrayList<Person> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        list.add(new Person("Ayesha ALi", "20", R.drawable.alia));
        list.add(new Person("Ramesha Saher", "20", R.drawable.aliaa));
        list.add(new Person("Natasha Saher", "20", R.drawable.alia));
        list.add(new Person("kinza Iqbal", "20", R.drawable.aliaa));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
//        RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.rv2);
        LinearLayoutManager manager = new LinearLayoutManager(this);
//        LinearLayoutManager manager2 = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(manager);
//        recyclerView2.setLayoutManager(manager2);
        RVadaptor rVadaptor = new RVadaptor(list, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(v.getContext(), "" + position, Toast.LENGTH_SHORT).show();

            }
        });
        recyclerView.setAdapter(rVadaptor);
//        recyclerView2.setAdapter(rVadaptor);


    }


}
