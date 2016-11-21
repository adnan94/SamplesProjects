package com.example.adnan.panafacebookauthentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity {
    private TextView info;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    AccessToken accessToken;
    AccessTokenTracker accessTokenTracker;
    LoginManager loginManager;
    Profile pro;
    ProgressDialog progress;
    ProfileTracker tracker;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_main);

       accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            }
        };
        info = (TextView) findViewById(R.id.info);

        checkAuth();

    }

    public void checkAuth() {
        accessToken = AccessToken.getCurrentAccessToken();
//        accessToken.getLastRefresh();
        if (accessToken != null) {
//            progress.dismiss();
            Intent i = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(i);
        } else {
            getMeLogIn();
        }
    }


    public void getMeLogIn() {
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                accessToken = loginResult.getAccessToken();
                if (Profile.getCurrentProfile() == null) {
                    tracker = new ProfileTracker() {
                        @Override
                        protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                            tracker.stopTracking();
                            Toast.makeText(MainActivity.this, "Else is Running" + currentProfile.getFirstName(), Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(MainActivity.this, Main2Activity.class);
                            startActivity(i);
                        }
                    };
                    tracker.startTracking();
                } else {
                    Profile profile = Profile.getCurrentProfile();
                    Toast.makeText(MainActivity.this, "Else is Running" + profile.getFirstName(), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onCancel() {
                info.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException e) {
                info.setText("Login attempt failed.");
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (callbackManager.onActivityResult(requestCode, resultCode, data)) {
            return;
        }
    }



    @Override
    protected void onStart() {
        super.onStart();
        accessTokenTracker.startTracking();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
    }

}
