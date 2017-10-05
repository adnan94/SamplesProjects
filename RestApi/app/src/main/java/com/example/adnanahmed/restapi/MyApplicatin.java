package com.example.adnanahmed.restapi;

import android.app.Application;
import android.content.Context;

/**
 * Created by AdnanAhmed on 9/17/2017.
 */

public class MyApplicatin extends Application {
    private static MyApplicatin mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static MyApplicatin getInstance() {
        return mInstance;
    }

    public static Context getAppContext() {
        return mInstance.getApplicationContext();

    }
}