package com.example.adnan.panaweatherapp;

/**
 * Created by Adnan on 10/27/2015.
 */

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.LruCache;


import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Adnan on 10/22/2015.
 */
public class volleySingleton {


    public static volleySingleton sInstance = null;
    RequestQueue mRequestQueue;



    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    private volleySingleton() {
        mRequestQueue = Volley.newRequestQueue(myApplication.con);
    }

    public static volleySingleton getInstance() {
        if (sInstance == null) {
            sInstance = new volleySingleton();
        }
        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

}


