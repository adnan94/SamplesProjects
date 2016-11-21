package com.example.adnan.panafirebasepractice;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.cloudinary.Cloudinary;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.view.GestureDetector;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    Firebase fire;
    ArrayList<String> students = new ArrayList<String>();
    ListView list;
    EditText name;
    ArrayAdapter<String> adapter;
    GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        detector = new GestureDetector(this, this);
        detector.setOnDoubleTapListener(this);
//        name = (EditText) findViewById(R.id.editText);
//

//        list = (ListView) findViewById(R.id.listView);
//        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, students);
//
        fire = new Firebase("https://panafirebasepractice.firebaseio.com/");
//
        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(i, 0);


//
//                SharedPreferences pref = getSharedPreferences("adnan",0);
//                SharedPreferences.Editor editor = pref.edit();
//                editor.putString("Name", name.getText().toString());
//
//                editor.commit();

//                String getName = pref.getString("Name", "");
//                Toast.makeText(MainActivity.this, "NAME :" + getName, Toast.LENGTH_LONG).show();

//                students.add(getName);
//                adapter.notifyDataSetChanged();
//                SharedPreferences pref = getSharedPreferences("adnan", Context.MODE_PRIVATE);


//                fire.child("adi").push().setValue(new data("adn"));
//                Firebase user = fire.child("user");
//                user.push().setValue("amazing !");
//                Firebase user2 = user.push();
//                user2.push().setValue("adnan ahmed");
//                String y = user2.getKey().toString();
//                String yy = user2.getPath().toString();
//                String yyy = user2.getRef().toString();
//                Toast.makeText(MainActivity.this, "key :" + y, Toast.LENGTH_LONG).show();
//                Toast.makeText(MainActivity.this, "path :" + yy, Toast.LENGTH_LONG).show();
//                Toast.makeText(MainActivity.this, "ref :" + yyy, Toast.LENGTH_LONG).show();

            }
        });

//        fire.child("adi").addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                data d = dataSnapshot.getValue(data.class);
//                Log.d("data retrive", d.getText().toString());
//                students.add(d.getText().toString());
//                 Toast.makeText(MainActivity.this, "size :" + students.size(), Toast.LENGTH_SHORT).show();
//                adapter.notifyDataSetChanged();
//            }

//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//        Toast.makeText(MainActivity.this, "size :" + students.size(), Toast.LENGTH_SHORT).show();
//
//        list.setAdapter(adapter);
//
//        students.add("Adnan");
//        students.add("Zeeshan");
//        students.add("Aliza");


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

    @Override
    public boolean onDown(MotionEvent e) {
     Toast.makeText(MainActivity.this,"down",Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Toast.makeText(MainActivity.this,"show",Toast.LENGTH_SHORT).show();
//        return true;

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Toast.makeText(MainActivity.this,"single tap up",Toast.LENGTH_SHORT).show();
        return true;

//        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Toast.makeText(MainActivity.this,"onScroll",Toast.LENGTH_SHORT).show();
        return true;

//        return false;


    }

    @Override
    public void onLongPress(MotionEvent e) {
        Toast.makeText(MainActivity.this,"long press",Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Toast.makeText(MainActivity.this,"fling",Toast.LENGTH_SHORT).show();
        return true;

//        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Toast.makeText(MainActivity.this,"on single tap confirmed",Toast.LENGTH_SHORT).show();
        return true;

//        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Toast.makeText(MainActivity.this,"double tap",Toast.LENGTH_SHORT).show();
        return true;

//        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        Toast.makeText(MainActivity.this,"double tap event",Toast.LENGTH_SHORT).show();
        return true;

//        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
