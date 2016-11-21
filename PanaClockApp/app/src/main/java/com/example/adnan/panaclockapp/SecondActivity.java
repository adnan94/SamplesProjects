package com.example.adnan.panaclockapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {
    Ringtone r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        r.play();
        Vibrator vibrator = (Vibrator) getApplication().getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(500);
        ((Button) findViewById(R.id.stop)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r.stop();

                finish();
            }
        });
    }
}
