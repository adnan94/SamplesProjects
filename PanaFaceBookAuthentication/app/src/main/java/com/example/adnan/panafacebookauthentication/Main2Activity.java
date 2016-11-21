package com.example.adnan.panafacebookauthentication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

public class Main2Activity extends AppCompatActivity {
    TextView t, t2, t3, t4;
    ImageView iv;
    Profile pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        casts();
        profile();
        ((Button)findViewById(R.id.logout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                finish();
            }
        });
    }

    public void profile() {
        pro = Profile.getCurrentProfile();

        if (pro != null) {
            t.setText(pro.getFirstName() + " " + pro.getLastName());
            t2.setText(pro.getLinkUri().toString());
            t3.setText(pro.getId());
            Picasso.with(this).load(pro.getProfilePictureUri(100, 100))
                    .placeholder(R.drawable.art)
                    .error(R.drawable.art)
                    .into(iv);

        } else {
//            Intent i = new Intent();
//            t.setText(i.getStringExtra("name"));
//            Picasso.with(this).load(i.getStringExtra("picLink"))
//                    .placeholder(R.drawable.art)
//                    .error(R.drawable.art)
//                    .into(iv);
            Toast.makeText(this, "Profile is null !", Toast.LENGTH_SHORT).show();
        }
    }

    public void casts() {
        iv = (ImageView) findViewById(R.id.imageView);
        t = (TextView) findViewById(R.id.textView);
        t2 = (TextView) findViewById(R.id.textView2);
        t3 = (TextView) findViewById(R.id.textView3);
//        t4 = (TextView) findViewById(R.id.textView4);

    }

}
