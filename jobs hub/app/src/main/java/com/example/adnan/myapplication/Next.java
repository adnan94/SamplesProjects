package com.example.adnan.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Next extends AppCompatActivity {
    EditText type, company, education, skill, salary, description, job;
    Firebase myFirebaseRef;

    ArrayList<String> listOfKeys;
    ArrayList<String> listOfData;
    ArrayList<String> dataList;

    ArrayList<docs> list;
    HashMap<String, String> andType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
//isko abi nahi dekhna yea baad ki activity hyy//

        Firebase.setAndroidContext(this);
//        Firebase.getDefaultConfig()
        myFirebaseRef = new Firebase("https://sylanifirebase.firebaseio.com/");
//
//        listOfKeys = new ArrayList<String>();
//        listOfData = new ArrayList<String>();


        ((Button) findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(Next.this, "Clicked", Toast.LENGTH_SHORT).show();

                myFirebaseRef.child("toDoData").push().setValue(new docs("ssss","tttt","uuuu","vvv","ww","xxx","yyy"));


                job.setText("");
                type.setText("");
                company.setText("");
                education.setText("");
                skill.setText("");
                salary.setText("");
                description.setText("");

                Intent i2 = new Intent(Next.this, TodoActivity.class);
                startActivity(i2);

//                if (type.getText().toString().length() == 0 || company.getText().toString().length() == 0 ||
//                        education.getText().toString().length() == 0 || skill.getText().toString().length() == 0
//                        || salary.getText().toString().length() == 0 || description.getText().toString().length() == 0 ||
//                        job.getText().toString().length() == 0) {
//
//                    Toast.makeText(Next.this, "Fields Must Be Filled", Toast.LENGTH_SHORT).show();
//                } else {
//                }
            }
        });


    }
//ok file knsi ha wahan navigate karo // konsi file ? o//bject jaa raha hy docs ka
    public void click() {


//            }


//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_next, menu);
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
