package com.aziz.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {
    BroadcastReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toast.makeText(MainActivity.this, FirebaseInstanceId.getInstance().getToken(), Toast.LENGTH_SHORT).show();
        Log.d("GGG",""+FirebaseInstanceId.getInstance().getToken());
//        final FirebaseDatabase database = FirebaseDatabase.getInstance();
//        database.getReference().child("adni").push().setValue("adann");
        Intent i =new Intent(MainActivity.this,MyFirebaseMessagingService.class);
        startService(i);
        receiver =new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String param1 = intent.getStringExtra("PARAM");
                String param2 = intent.getStringExtra("PARAM2");
                // Notification data will receive here.
                if (param2 != null)
                 Toast.makeText(MainActivity.this,param1+" :"+"Param 2"+param2,Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver((receiver),
                new IntentFilter("ADNAN"));
    }
}
