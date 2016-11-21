package com.example.adnan.myapplication;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.provider.Settings;
import android.support.v7.app.NotificationCompat;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     * <p>
     * /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     * @see IntentService
     */
    // TODO: Customize helper method
    int i = 0;

    public MyIntentService() {

        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        Notification notification = new NotificationCompat.Builder(this)
                .setTicker("Mee .")
                .setSmallIcon(R.drawable.sd)
                .setContentTitle("Mee .")
                .setContentText("sign up completed")
                .setContentIntent(PendingIntent.getActivity(this, 0,
                        new Intent(getApplicationContext(), signIn.class), 0))
                .setAutoCancel(true)
                .setVibrate(new long[]{500, 500})
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .build();
        notificationManager.notify(i++, notification);

    }
}
//    }
//}
