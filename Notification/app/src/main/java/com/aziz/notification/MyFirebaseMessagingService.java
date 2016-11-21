package com.aziz.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    LocalBroadcastManager broadcaster;
    public MyFirebaseMessagingService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("HELLO","sadsadsadsad");
        broadcaster = LocalBroadcastManager.getInstance(this);

    }

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d("HELLO","sadsadsadsad");
        Log.d("HELLO", "From: " + remoteMessage.getFrom());
        Log.d("HELLO", "Message data payload: " + remoteMessage.getData().toString());
        Log.d("HELLO", "Message Notification Body: " + remoteMessage.getNotification().getBody());

        super.onMessageReceived(remoteMessage);
        Log.d("HELLO","sadsadsadsad");
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData().toString());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        sendNotification(remoteMessage.getNotification().getBody(), remoteMessage.getFrom());
        sendResult(remoteMessage.getNotification().getBody(), remoteMessage.getFrom());

    }
/**
     * Called when message is received.
     *
//     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */

public void sendResult(String message_param1, String message_param2) {
    Log.d("INSIDE", "Message Notification Body: ");

    Intent intent = new Intent("ADNAN");
    if (message_param1 != null)
        intent.putExtra("PARAM", message_param1);
    if (message_param2 != null)
        intent.putExtra("PARAM2", message_param2);
    broadcaster.sendBroadcast(intent);
}
    // [START receive_message]
private void sendNotification(String messageBody,String from) {
    Intent intent = new Intent(this, MainActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
            PendingIntent.FLAG_ONE_SHOT);

    Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(from)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent);

    NotificationManager notificationManager =
            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

    Random random = new Random();
    int randomNumber = random.nextInt(9999 - 1000) + 1000;
    notificationManager.notify(randomNumber, notificationBuilder.build());
}

}
