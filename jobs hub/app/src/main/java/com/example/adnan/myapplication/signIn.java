package com.example.adnan.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


public class signIn extends AppCompatActivity {
    Firebase myFirebaseRef;

    String get;
    EditText id, pass;
    boolean passOk = false;
    boolean idOk = false;
    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Firebase.setAndroidContext(this);

        myFirebaseRef = new Firebase("https://sylanifirebase.firebaseio.com/");

        id = (EditText) findViewById(R.id.signInid);
        pass = (EditText) findViewById(R.id.signInPass);

        signIn = (Button) findViewById(R.id.button);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id.getText().length() == 0 || pass.getText().length() == 0) {
                    toast("empty spaces are not allowed");
                } else {
toast(id.getText().toString()+"pass ---"+pass.getText().toString());
                    myFirebaseRef.authWithPassword(id.getText().toString(), pass.getText().toString(), new Firebase.AuthResultHandler() {
                        @Override
                        public void onAuthenticated(AuthData authData) {
                            toast(authData.getProvider().toString());
                        Intent i=new Intent(signIn.this,TodoActivity.class);
                            startActivity(i);
//                            rukna ek min
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
    }


    public void toast(String mess) {
        Toast.makeText(signIn.this, mess, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_in, menu);
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
}
