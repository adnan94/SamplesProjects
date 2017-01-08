package com.example.adnan.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Intent i = new Intent(this,MyService.class);
//        startService(i);
//        Intent ii = new Intent(this,OtherService.class);
//        startService(ii);
//        Intent iii = new Intent(this,ThirdService.class);
//        startService(iii);
//    }
//}
public class MainActivity extends AppCompatActivity {

    Button startSticky, startNotSticky, redeliverIntent, intentService,simpleService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        clickEvents();
    }

    public void initialize() {
        startSticky = (Button) findViewById(R.id.startSticky);
        startNotSticky = (Button) findViewById(R.id.startNotStick);
        redeliverIntent = (Button) findViewById(R.id.redeliverIntent);
        intentService = (Button) findViewById(R.id.intentService);
        simpleService = (Button) findViewById(R.id.SimpleService);

    }

    public void clickEvents() {
        startSticky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MyService.class);
                startService(i);

            }
        });
        startNotSticky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startService(

                Intent i = new Intent(MainActivity.this, StartNotSticky.class);
                startService(i);
            }
        });
        redeliverIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startService(new Intent(MainActivity.this, StartRedeliverIntent.class));
                Intent i = new Intent(MainActivity.this, StartRedliverService.class);
                startService(i);

            }
        });
        intentService.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, StartStickyInProcess.class);
//                i.putExtra("Name","Adnan Ahmed Got Data Intent Service");
                startService(i);


            }
        });
        simpleService.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, StartNotStickyInProcess.class);
                startService(i);

            }
        });

    }
}
