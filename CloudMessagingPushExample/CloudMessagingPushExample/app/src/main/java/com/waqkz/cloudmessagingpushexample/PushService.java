package com.waqkz.cloudmessagingpushexample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

/**
 * Created by Waqas on 10/16/2016.
 */

public class PushService extends Service {

    private FirebaseUser currentUser;

    @Override
    public void onCreate() {
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        Log.v("USER", "" + currentUser.getUid());

        FirebaseDatabase.getInstance().getReference().child("Notifications").child(currentUser.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                currentUser = FirebaseAuth.getInstance().getCurrentUser();
                NotificationMessage notificationMessage = dataSnapshot.getValue(NotificationMessage.class);

                notif(notificationMessage.getMessage());


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                Log.v("TAG", "" + dataSnapshot.getValue());
//
//                for (DataSnapshot data: dataSnapshot.getChildren()){
//
//                    NotificationMessage notificationMessage = data.getValue(NotificationMessage.class);
//
//                    notif(notificationMessage.getMessage());
//
//                    FirebaseDatabase.getInstance().getReference().child("Notifications").child(currentUser.getUid()).child(notificationMessage.getPushId()).removeValue();
//                }
//
//                Log.v("STICK", "" + dataSnapshot.getValue());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//

        super.onCreate();


    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void notif(String message) {


        Intent intent = new Intent(this, PushActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder notification = new Notification.Builder(this)
                .setTicker("Office Work")
                .setContentTitle("Push Notification")
                .setContentText(message)
                .setTicker("Message")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setVibrate(new long[]{500, 500})
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setPriority(Notification.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Random random = new Random();
        int randomNumber = random.nextInt(9999 - 1000) + 1000;
        notificationManager.notify(randomNumber, notification.build());

        FirebaseDatabase.getInstance().getReference().child("Notifications").child(currentUser.getUid()).removeValue();

    }
}
