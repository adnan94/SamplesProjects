package com.example.adnanahmed.restapi;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by AdnanAhmed on 9/17/2017.
 */

public class VolleySingleTon {


    public static VolleySingleTon sInstance = null;
    RequestQueue mRequestQueue;
    private ImageLoader imgLoad;

    public ImageLoader getImgLoad() {
        return imgLoad;
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    private VolleySingleTon() {
        mRequestQueue = Volley.newRequestQueue(MyApplicatin.getAppContext());
        imgLoad = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private LruCache<String, Bitmap> lru = new LruCache<String, Bitmap>((int) (Runtime.getRuntime().maxMemory() / 1024 / 8));

            @Override
            public Bitmap getBitmap(String s) {
                return lru.get(s);
            }

            @Override
            public void putBitmap(String s, Bitmap bitmap) {
                lru.put(s, bitmap);
            }
        });
    }

    public static VolleySingleTon getInstance() {
        if (sInstance == null) {
            sInstance = new VolleySingleTon();
        }
        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

}
