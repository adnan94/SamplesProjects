package com.example.adnan.panatwitterauth;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.LruCache;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    //    ArrayList<Twitter> tweets;
    Long id;
    ArrayList<String> tweets;
    String userName;
    TextView textName, textStatus;
    ListView list;
    ArrayAdapter<String> adap;
    RequestQueue mRequestQueue;
    ImageLoader imageLoader;
    String ingUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textName = (TextView) findViewById(R.id.namesText);
        ((Button) findViewById(R.id.Logout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Twitter.getSessionManager().clearActiveSession();

                finish();
            }
        });
//        textStatus = (TextView) findViewById(R.id.statusText);
        list = (ListView) findViewById(R.id.listView);
        tweets = new ArrayList<>();

        userName = getIntent().getStringExtra("name");
        id = getIntent().getLongExtra("id", 0);
//        toast("UserName :" + userName);
        textName.setText(userName);
        getStatus();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    public void getStatus() {
        mRequestQueue = Volley.newRequestQueue(MainActivity.this);
        imageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
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
        TwitterCore.getInstance().getApiClient().getStatusesService()
                .userTimeline(id,
                        userName,
                        10,
                        //the number of tweets we want to fetch,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        new com.twitter.sdk.android.core.Callback<List<Tweet>>() {
                            @Override
                            public void success(Result<List<Tweet>> result) {

                                for (Tweet t : result.data) {
                                    tweets.add(t.text);
                                    String email = t.user.email;
//                                    toast(email);
                                    ingUrl = t.user.profileImageUrlHttps;
//                                    String sd=t.user.
//                                    toast(t.text);
                                    adap = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, tweets);

//                                    android.util.Log.d("twittercommunity", "tweet is " + t.text);
                                    list.setAdapter(adap);
                                    adap.notifyDataSetChanged();
                                }
                                imageLoader.get(ingUrl, new ImageLoader.ImageListener() {
                                    @Override
                                    public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {

                                        ImageView iv = (ImageView) findViewById(R.id.imageView);
                                        iv.setImageBitmap(imageContainer.getBitmap());
                                    }

                                    @Override
                                    public void onErrorResponse(VolleyError volleyError) {

                                    }
                                });
                            }

                            @Override
                            public void failure(TwitterException e) {

                            }
                        });
    }


    public void toast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();

    }

}
