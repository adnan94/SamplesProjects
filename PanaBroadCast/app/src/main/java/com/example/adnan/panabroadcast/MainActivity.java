package com.example.adnan.panabroadcast;

import android.app.ActivityManager;
import android.app.ApplicationErrorReport;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.SyncStateContract;
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
        IntentFilter filter = new IntentFilter("com");
        MyReceiver resiver = new MyReceiver();
        registerReceiver(resiver, filter);


        text = (TextView) findViewById(R.id.Button);

        ((Button) findViewById(R.id.btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//                for (ApplicationErrorReport.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
//                    if (serviceClass.getName().equals(service.service.getClassName())) {
//                        return true;
//                    }


                Intent intent = new Intent();
//                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setAction("com1").putExtra("ad1", "zeeshan hanif");
                sendBroadcast(intent);
                text = (TextView) findViewById(R.id.Button);

            }
        });

    }


    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String data = intent.getStringExtra("ad");
                Toast.makeText(context, "Intent Detected. :" + data, Toast.LENGTH_LONG).show();
                text.setText(data);
            }
        }
    }

}
