package com.example.adnan.panatwitterauth;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

import com.twitter.sdk.android.core.*;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.internal.TwitterCollection;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.TweetEntities;
import com.twitter.sdk.android.core.services.StatusesService;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScrollingActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "0MEiDuPsrnGx9i1DaLDKRa0Vl";
    private static final String TWITTER_SECRET = "g6BevLjp07wMJgRWEswG1C3M8Pmz1StL7ISyrmi71cuS0EHRQw";
    private TwitterLoginButton loginButton;
    ArrayList<Tweet> tweets;
    TextView text;
    TwitterApiClient twitterApiClient;
    TwitterSession session;
    Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://panatodo.firebaseio.com/");
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric fab = Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.content_scrolling);
        session = TwitterCore.getInstance().getSessionManager().getActiveSession();
        if (session != null) {
            session.getUserId();
            Intent i = new Intent(ScrollingActivity.this, MainActivity.class);
            i.putExtra("name", session.getUserName());
            i.putExtra("id", session.getUserId());
            startActivity(i);
        } else {
            Session();
        }

    }

    public void Session() {
//        text = (TextView) findViewById(R.id.text);

        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);

        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {

                // The TwitterSession is also available through:
                // Twitter.getInstance().core.getSessionManager().getActiveSession()
                TwitterSession session = result.data;
                // TODO: Remove toast and use the TwitterSession's userID
                // with your app's user model
                String userName = session.getUserName();
                Long pass = session.getUserId();
//                String token=session.getAuthToken();
//                result.data.
//                text.setText("UserName :" + userName + "\nUserID :" + pass);
                final Map<String, String> options = new HashMap<String, String>();
                options.put("oauth_token", session.getAuthToken().token);
                options.put("oauth_token_secret", session.getAuthToken().secret);
                options.put("user_id", "" + session.getUserId());
                firebase.authWithOAuthToken("twitter", options, new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        toast("FireBase Sucessful Auth !");
                        firebase.child("user").push().setValue(options);
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        toast("FireBase Failure !");
                    Log.e("******",firebaseError.toString());
                    }
                });
                Intent i = new Intent(ScrollingActivity.this, MainActivity.class);
                i.putExtra("name", userName);
                i.putExtra("id", pass);
                startActivity(i);


            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
                toast("Login with Twitter failure");
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

    public void toast(String message) {
        Toast.makeText(ScrollingActivity.this, message, Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class LoginHandler extends Callback<TwitterSession> {
        @Override
        public void success(Result<TwitterSession> twitterSessionResult) {
            toast("handler sucess");
        }

        @Override
        public void failure(TwitterException e) {
            toast("handler failure");
        }
    }

}
