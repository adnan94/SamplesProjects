package com.example.adnan.onetoonechat;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class afterSignin extends AppCompatActivity {
    ListView list;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    Firebase fire;
    String uid;
    String user;
    String partnerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_signin);
        Firebase.setAndroidContext(this);
        fire = new Firebase("https://onetoonechat.firebaseio.com/");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        uid = i.getStringExtra("userId");
        list = (ListView) findViewById(R.id.listView);


        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(afterSignin.this, R.layout.layout_users, R.id.textUsers, arrayList);
        fire.child("SignUpData").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    getSupportActionBar().setTitle(d.getValue().toString());
                    user = d.getValue().toString();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        fire.child("SignUpData").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                lists.listOfUserIds.add(dataSnapshot.getKey().toString());

                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    arrayList.add(d.getValue().toString());
                    adapter.notifyDataSetChanged();
//                    toast(d.getValue().toString());
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (arrayList.get(position).toString().equals(user)) {
                    toast("Its You !");
                } else {

                    partnerId = lists.listOfUserIds.get(position).toString();
                    Intent i = new Intent(afterSignin.this, chatUI.class);
//                fire.child("conversation").child()
                    i.putExtra("id", uid);
                    i.putExtra("pid", partnerId);
                    i.putExtra("use", user);
                    startActivity(i);


                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_after_signin, menu);
        return true;
    }

    public void toast(String message) {
        Toast.makeText(afterSignin.this, message, Toast.LENGTH_SHORT).show();
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
        if (id == R.id.next) {
            fire.unauth();
            toast("Logout");
            finish();
            Intent i = new Intent(afterSignin.this, MainActivity.class);
            startActivity(i);

        }
        if (id == android.R.id.home) {
            fire.unauth();
            toast("Logout");
            finish();
            startActivity(new Intent(afterSignin.this, MainActivity.class));

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
