package com.example.adnanahmed.geofencing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Adnan Ahmed on 1/11/2017.
 */

public class Reciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context,MyService.class));

    }
}
