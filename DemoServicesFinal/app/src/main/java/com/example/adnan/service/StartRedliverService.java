package com.example.adnan.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class StartRedliverService extends Service {
    public StartRedliverService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), "Start Redeliver OnStartCommand ", Toast.LENGTH_SHORT).show();
        return START_REDELIVER_INTENT;

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        Toast.makeText(getApplicationContext(), "Start Redeliver OnDestroy ", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}
