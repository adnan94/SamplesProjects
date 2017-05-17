package com.example.ali.demoalarmmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        intent.putExtra("extra","Fuuast");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(),3456, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + (10 * 1000), pendingIntent);
//        Toast.makeText(this, "Alarm set in " + 4 + " seconds",Toast.LENGTH_LONG).show();
    }
}
