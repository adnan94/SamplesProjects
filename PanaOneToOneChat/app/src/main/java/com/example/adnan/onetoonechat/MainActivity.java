package com.example.adnan.onetoonechat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Firebase fire;
    EditText email, name, password;
    String uid, userId;
    String time;
    String amPm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        fire = new Firebase("https://onetoonechat.firebaseio.com/");

        Calendar cal = Calendar.getInstance();

        int second = cal.get(Calendar.SECOND);
        int minute = cal.get(Calendar.MINUTE);
        //12 hour format
        int hourofday = cal.get(Calendar.HOUR_OF_DAY);
        int hour = cal.get(Calendar.HOUR);
        //24 hour format

        if (hourofday > 12) {
            amPm = "Pm";
        } else {
            amPm = "Am";
        }
        time = hour + ":" + minute + " " + amPm;

        email = (EditText) findViewById(R.id.EmailEditText);
        password = (EditText) findViewById(R.id.PasswordEditText);
        ((ImageButton) findViewById(R.id.userSize)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fire.child("names").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int i = 0;
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            i++;
                        }
                        toast(i + " Users So Far !");
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
            }
        });
        ((Button) findViewById(R.id.buttonLogin)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().length() == 0 || password.getText().length() == 0) {
                    toast("empty spaces are not allowed");
                } else {
                    fire.authWithPassword(email.getText().toString(), password.getText().toString(), new Firebase.AuthResultHandler() {
                        @Override
                        public void onAuthenticated(AuthData authData) {
                            toast("sucessfull");

                            uid = authData.getUid();
//                            toast(uid);

                            Intent i = new Intent(MainActivity.this, afterSignin.class);
//                            i.putExtra("userName", nameD);
                            i.putExtra("userId", uid);
                            startActivity(i);
////                            rukna ek min

                        }

                        @Override
                        public void onAuthenticationError(FirebaseError firebaseError) {
                            switch (firebaseError.getCode()) {
                                case FirebaseError.INVALID_EMAIL:
                                    toast("Invalid Email");
                                    break;
                                case FirebaseError.INVALID_PASSWORD:
                                    toast("Invalid Password");
                                    break;
                                case FirebaseError.NETWORK_ERROR:
                                    toast("Network Problem");
                                    break;
                                case FirebaseError.AUTHENTICATION_PROVIDER_DISABLED:
                                    toast("Auth provider disabled");
                                    break;
                                case FirebaseError.USER_DOES_NOT_EXIST:
                                    toast("User Not Exists");
                                    break;
                            }
                        }
                    });
                }
            }

        });


        TextView textView = (TextView) findViewById(R.id.textViewSignUp);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.alert_sign_up, null);
                View view2 = inflater.inflate(R.layout.custom_title, null);

                email = (EditText) view.findViewById(R.id.SignUpEmail);
                password = (EditText) view.findViewById(R.id.signUpPass);
                name = (EditText) view.findViewById(R.id.SignUpName);
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setIcon(R.drawable.user);
                builder.setCustomTitle(view2);
                builder.setView(view);
                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("Register", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        fire.createUser(email.getText().toString(), password.getText().toString(), new Firebase.ValueResultHandler<Map<String, Object>>() {
                            @Override
                            public void onSuccess(Map<String, Object> stringObjectMap) {
//                                Log.d("sucess", "sucess");
                                userId = stringObjectMap.get("uid").toString();
                                fire.child("SignUpData").child(userId).child("userName").setValue(name.getText().toString());
                                fire.child("Pushnames").child(userId).push().setValue(name.getText().toString());
                                fire.child("names").child(userId).setValue(name.getText().toString());

                            }

                            @Override
                            public void onError(FirebaseError firebaseError) {
                                Log.d("error", "error");
                                switch (firebaseError.getCode()) {
                                    case FirebaseError.EMAIL_TAKEN:
                                        Log.d("", "Email Alreaddy Exists");
                                        break;
                                    case FirebaseError.NETWORK_ERROR:
                                        Log.d("", "Network Problem");
                                        break;
                                }

                            }
                        });
                    }
                });
                builder.show();


            }
        });


    }

    public void toast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
