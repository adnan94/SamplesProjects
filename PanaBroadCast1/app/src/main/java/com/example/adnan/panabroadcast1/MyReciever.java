package com.example.adnan.panabroadcast1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Adnan on 11/10/2015.
 */
public class MyReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String data = intent.getStringExtra("ad1");
        Toast.makeText(context, "Intent Detected. :" + data, Toast.LENGTH_LONG).show();
    }
}
