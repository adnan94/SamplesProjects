package com.example.adnan.panaclockapp;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by adnan on 4/11/2016.
 */
public class Service extends android.app.Service {
    public static int minute;
    public static int hour;
    int request = 1;
    int notifyID=123;
    public static Context context;
    public static AlarmManager alarmManager;
    public static PendingIntent pendingIntent;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        if (intent != null) {
            Log.d("ServiceHour", "");

        }
        Toast.makeText(getApplicationContext(), "StartBind", Toast.LENGTH_SHORT).show();

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), "StartCmd", Toast.LENGTH_SHORT).show();


        return super.onStartCommand(intent, flags, startId);

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCreate() {
        Toast.makeText(getApplicationContext(), "Start11", Toast.LENGTH_SHORT).show();
        context = getApplicationContext();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        Notification notification = new Notification.Builder(getApplicationContext())
                .setTicker("Office Work")
                .setContentTitle("Friend Request")
                .setContentText(" want to be your friend ")
                .setTicker("Request")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setVibrate(new long[]{500, 500})
                .setSmallIcon(R.drawable.ddddddd)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .build();
        notificationManager.notify(notifyID++, notification);
//        AlarmManager alarmManager2 = (AlarmManager) getSystemService(ALARM_SERVICE);
//        PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), 0, new Intent(getApplicationContext(), Service.class), 0);
//        alarmManager2.cancel(pendingIntent);
//        MainActivity.acti.finish();
        pendingIntent = PendingIntent.getActivity(getApplicationContext(), request++, new Intent(getApplicationContext(), SecondActivity.class), 0);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, AppClass.time, pendingIntent);


        super.onCreate();


    }


}
