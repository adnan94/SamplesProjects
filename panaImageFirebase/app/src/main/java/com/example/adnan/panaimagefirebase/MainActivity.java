package com.example.adnan.panaimagefirebase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    Firebase fire;
    ImageView imageView, imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView2 = (ImageView) findViewById(R.id.imageViewsenD);

        fire = new Firebase("https://panafirebasepractice.firebaseio.com/");
        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.chicken);//your image
                Bitmap resizedbitmap = Bitmap.createScaledBitmap(bmp, 100, 100, true);
                imageView2.setImageResource(R.drawable.chick);
                ByteArrayOutputStream bYtE = new ByteArrayOutputStream();
                resizedbitmap.compress(Bitmap.CompressFormat.PNG, 100, bYtE);
                resizedbitmap.recycle();
                byte[] byteArray = bYtE.toByteArray();
                String imageFile = Base64.encodeToString(byteArray, Base64.DEFAULT);
                Log.d("", imageFile);
                fire.child("image").push().setValue(imageFile, new Firebase.CompletionListener() {
                    @Override
                    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                        Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                    }
                });


                ((Button) findViewById(R.id.getImg)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fire.child("image").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                    byte[] imageAsBytes = Base64.decode(dataSnapshot1.getValue().toString(), Base64.DEFAULT);
                                    Bitmap bmp = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
                                    Bitmap resizedbitmap = Bitmap.createScaledBitmap(bmp, 100, 100, true);

                                    imageView.setImageBitmap(resizedbitmap);

                                }

                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });
                    }
                });
//                fire.child("image").push().setValue("jhj");

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
