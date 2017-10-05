package com.example.adnanahmed.addmob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    AdView addView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addView = (AdView) findViewById(R.id.adView);
        button = (Button) findViewById(R.id.button);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("90F00FFA293E935757252BA7AE0A0B09")
                .build();
        addView.loadAd(adRequest);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });

    }
    @Override
    public void onPause() {
        if (addView != null) {
            addView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (addView != null) {
            addView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (addView != null) {
            addView.destroy();
        }
        super.onDestroy();
    }
//mAdView.setAdListener(new AdListener() {
//        @Override
//        public void onAdLoaded() {
//            Toast.makeText(getApplicationContext(), "Ad is loaded!", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onAdClosed() {
//            Toast.makeText(getApplicationContext(), "Ad is closed!", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onAdFailedToLoad(int errorCode) {
//            Toast.makeText(getApplicationContext(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onAdLeftApplication() {
//            Toast.makeText(getApplicationContext(), "Ad left application!", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onAdOpened() {
//            Toast.makeText(getApplicationContext(), "Ad is opened!", Toast.LENGTH_SHORT).show();
//        }
//    });
}
