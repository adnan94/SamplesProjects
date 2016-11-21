package com.example.adnan.panaclockapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    PendingIntent pendingIntent, pendingIntent2;
    public static int minute, hour, day, month, year;
    TimePicker timePicker;
    Communicator com;
    AlarmManager alarmManager, alarmManager2;
    EditText min;
    public static Activity acti;
    int request = 1;

//    EditText hourTxt, minuteTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        acti = MainActivity.this;

        timePicker = (TimePicker) findViewById(R.id.timePicker);
        min = (EditText) findViewById(R.id.minutes);
//        timePicker.is24HourView();


        ((Button) findViewById(R.id.set)).setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

                minute = timePicker.getCurrentMinute();
                hour = timePicker.getCurrentHour();

                Log.d("ServiceHour", "" + hour);
                Toast.makeText(getApplicationContext(), "Alarm Set To " + hour + ":" + minute, Toast.LENGTH_SHORT).show();

                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);

                setNotification(calendar.getTimeInMillis());

            }
        });

    }

    public void setNotification(long time) {

        int notifyTime = Integer.parseInt(min.getText().toString());
        Toast.makeText(getApplicationContext(), "notification " + notifyTime + "  long time=" + time, Toast.LENGTH_SHORT).show();
        pendingIntent2 = PendingIntent.getService(getApplicationContext(), request++, new Intent(getApplicationContext(), Service.class), 0);
        alarmManager2 = (AlarmManager) getSystemService(ALARM_SERVICE);
        int m = 60 * 1000;
        alarmManager2.set(AlarmManager.RTC_WAKEUP, time - (m * notifyTime), pendingIntent2);
        AppClass.time = time;

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
