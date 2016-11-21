package com.example.adnan.panaclockapp;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.widget.Toast;

/**
 * Created by adnan on 4/13/2016.
 */
public class alarm {
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void noti(Context con){
        Toast.makeText(con, "Start", Toast.LENGTH_SHORT).show();
        Notification n  = new Notification.Builder(con)
                .setContentTitle("Alarm")
                .setContentText("Alarm Is About To Ring ")
                .setAutoCancel(true)
                .build();


        NotificationManager notificationManager = (NotificationManager) con.getSystemService(con.NOTIFICATION_SERVICE);

        notificationManager.notify(0, n);

    }
}
