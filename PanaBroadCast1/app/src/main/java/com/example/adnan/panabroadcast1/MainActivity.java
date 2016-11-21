package com.example.adnan.panabroadcast1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        IntentFilter filter = new IntentFilter("com1");
//        MyReceiver resiver = new MyReceiver();
//        registerReceiver(resiver, filter);

        ((Button) findViewById(R.id.btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.package.address");
//                startActivity(launchIntent);
//                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
//                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                Intent launchIntent = new Intent();
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                launchIntent.setAction("com").putExtra("ad", "adnan ahmed");
                sendBroadcast(launchIntent);
                text = (TextView) findViewById(R.id.Button);

            }
        });


    }




}
