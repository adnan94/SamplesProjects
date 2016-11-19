package com.aziz.apptwo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

/**
 * Created by panacloud on 10/5/16.
 */
public class reciever extends BroadcastReceiver {
    Intent i;
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent != null){
            // You can also include some extra data.
            Toast.makeText(context,intent.getStringExtra("High"),Toast.LENGTH_SHORT).show();

             i= new Intent("intent");
            i.putExtra("mess",intent.getStringExtra("High"));
            LocalBroadcastManager.getInstance(context).sendBroadcast(i);
        }
    }
}
