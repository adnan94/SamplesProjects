package com.example.adnanahmed.geofencing;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

/**
 * Created by Adnan Ahmed on 1/16/2017.
 */

public class AppClass extends MultiDexApplication {
    @Override
    public void onCreate() {
        MultiDex.install(getApplicationContext());
        super.onCreate();
    }
}
