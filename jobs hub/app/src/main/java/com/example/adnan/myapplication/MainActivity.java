


package com.example.adnan.myapplication;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends ActionBarActivity {
    HashMap<String, Object> data;
    Firebase myFirebaseRef;
    Button updateBtn;
    EditText password;
    EditText contact;
    EditText email;
    HashMap<String, Object> usersid;
    HashMap<String, Object> userspass;
    ArrayList<String> list;
    boolean selfie;
    public static EditText userId;
    String id;
    PendingIntent intent;
    AlarmManager manager;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
    //    Firebase.getDefaultConfig().setPersistenceEnabled(true);
        myFirebaseRef = new Firebase("https://sylanifirebase.firebaseio.com/");

        updateBtn = (Button) findViewById(R.id.updateBtn);
        password = (EditText) findViewById(R.id.password);
        contact = (EditText) findViewById(R.id.contact);
        email = (EditText) findViewById(R.id.email);
        userId = (EditText) findViewById(R.id.userid);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (password.getText().toString().length() == 0 ||
//                        email.getText().toString().length() == 0 ||
//                        contact.getText().toString().length() == 0 ||
//                        userId.getText().toString().length() == 0) {
//                    toast("empty spaces not allowed");
//
//                } else {
                myFirebaseRef.createUser(email.getText().toString(), password.getText().toString(), new Firebase.ValueResultHandler<Map<String, Object>>() {
                    @Override
                    public void onSuccess(Map<String, Object> stringObjectMap) {
                        toast("Sucessful");

                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        switch (firebaseError.getCode()) {
                            case FirebaseError.EMAIL_TAKEN:
                                toast("Email Alreaddy Exists");
                                break;
                            case FirebaseError.NETWORK_ERROR:
                                toast("Network Problem");
                                break;
                        }

                    }
                });


            }
        });

    }

    public void toast(String mess) {
        Toast.makeText(MainActivity.this, mess, Toast.LENGTH_LONG).show();
    }

    public void click() {
//        elsee();


        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        Notification notification = new NotificationCompat.Builder(MainActivity.this)
                .setTicker("Mee .")
                .setSmallIcon(R.drawable.sd)
                .setContentTitle("Mee .")
                .setContentText("sign up completed")
                .setAutoCancel(true)
                .setContentIntent(intent)
                .setVibrate(new long[]{1000, 1000})
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .build();
        notificationManager.notify(i++, notification);

//                    Intent i=new Intent(MainActivity.this,Main2Activity.class);
//                    startActivity(i);

    }

//}


    public void dialog(String message, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

