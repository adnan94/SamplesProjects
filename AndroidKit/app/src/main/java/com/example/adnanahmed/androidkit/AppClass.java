package com.example.adnanahmed.androidkit;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.internal.AccountKitController;

/**
 * Created by AdnanAhmed on 9/25/2017.
 */

public class AppClass extends Application {
    @Override
    public void onCreate() {
//        AccountKitController.initialize(this, null);
        super.onCreate();
    }
}
