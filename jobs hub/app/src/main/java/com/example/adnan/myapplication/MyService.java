package com.example.adnan.myapplication;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

/**
 * Created by Adnan on 9/2/2015.
 */
public class MyService extends IntentService {


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    int i=0;
    public MyService() {
        super("MyIntentServices");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
