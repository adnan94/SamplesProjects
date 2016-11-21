package com.example.adnan.panaservices;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       try {

           for(int i=0;i<10;i++){
               Thread.sleep(500);

               Log.d("Counter", "" + i);
//           Toast.makeText(getApplicationContext(), "On Start Command"+i, Toast.LENGTH_SHORT).show();
           }

       } catch (InterruptedException e) {
           e.printStackTrace();
       }


        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
