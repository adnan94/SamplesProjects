package com.example.adnan.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class StartNotStickyInProcess extends Service {
    public StartNotStickyInProcess() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), "Start Not Sticky In Seprate Process OnStartCommand ", Toast.LENGTH_SHORT).show();
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        Toast.makeText(getApplicationContext(), "Start Not Sticky In Seprate Process OnDestroy ", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}
