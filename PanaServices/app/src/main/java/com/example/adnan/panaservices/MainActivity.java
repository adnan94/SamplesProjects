package com.example.adnan.panaservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Start", "");
        Toast.makeText(this,"Start",Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this,MyService.class);
        startService(i);

    }
}
