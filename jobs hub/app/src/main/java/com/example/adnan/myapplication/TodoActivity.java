package com.example.adnan.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

public class TodoActivity extends AppCompatActivity {
    Firebase myFirebaseRef;
    ListView listView;
    adapter adap;
    ArrayList<String> listOfkeys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://sylanifirebase.firebaseio.com/");
        listOfkeys = new ArrayList<String>();
//        characterstics.Classlist.clear();


        listView = (ListView) findViewById(R.id.listView);
        adap = new adapter(this, characterstics.Classlist);
        listView.setAdapter(adap);

        myFirebaseRef.child("toDoData").addChildEventListener(new ChildEventListener() {
// e run krky dekhata hu / log cat dekhna

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                listOfkeys.add(dataSnapshot.getKey().toString());
//                Log.d("keys", "" + listOfkeys.size());
                docs d = dataSnapshot.getValue(docs.class);


                adap.add(new docs(d.getType().toString(), d.getCompany().toString(), d.getSkill().toString(), d.getEducation().toString(), d.getDescription().toString(), d.getSalary().toString(), d.getJob().toString()));
                Log.d("size of list", "" + characterstics.Classlist.size());

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


        ((Button) findViewById(R.id.job)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.alertlayout, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(TodoActivity.this);
//builder.setIcon(R.drawable.rsz_bg_cover);
                builder.setTitle("Jobs Hub !");
//                builder.setMessage("");
                builder.setView(view);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(TodoActivity.this, "Ok button clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(TodoActivity.this, "Cancel button clicked", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_todo, menu);
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
