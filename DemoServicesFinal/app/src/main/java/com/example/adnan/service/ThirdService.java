package com.example.adnan.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ThirdService extends Service {
    public ThirdService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
