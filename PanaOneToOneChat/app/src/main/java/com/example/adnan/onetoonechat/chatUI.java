package com.example.adnan.onetoonechat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class chatUI extends AppCompatActivity {
    String id;
    String partnerId;
    Firebase fire;
    String nameD;
    String user;
    ListView listview;
    EditText editText;
    String time;
    String amPm;
    Adaptor adap;
    ImageButton btnSend;
    int position1;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_ui);
        Firebase.setAndroidContext(this);
        fire = new Firebase("https://onetoonechat.firebaseio.com/");

        editText = (EditText) findViewById(R.id.editTextMessage);
        editText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    btnSend.setImageResource(R.drawable.ic_chat_send);
                } else {
                    btnSend.setImageResource(R.drawable.ic_chat_send_active);
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
//                TextView myOutputBox = (TextView) findViewById(R.id.myOutputBox);
//                myOutputBox.setText(s);
//                btnSend.setImageResource(R.drawable.ic_chat_send_active);

            }
        });
        btnSend = (ImageButton) findViewById(R.id.send);
        listview = (ListView) findViewById(R.id.ListView);

        Intent i = getIntent();
        id = i.getStringExtra("id");
        partnerId = i.getStringExtra("pid");
        user = i.getStringExtra("use");

        fire.child("Pushnames").child(partnerId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {
//                    Toast.makeText(chatUI.this, nameD, Toast.LENGTH_SHORT).show();
                    nameD = d.getValue().toString();
                    getSupportActionBar().setTitle(nameD);

                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

//        Log.d("nameD+++", nameD);
        adap = new Adaptor(chatUI.this, user);
        listview.setAdapter(adap);
        registerForContextMenu(listview);

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


        fire.child("conversation").child(id).child("message").child(partnerId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                Log.d("data+++", dataSnapshot.getValue().toString());
                messages mess = dataSnapshot.getValue(messages.class);
                String name = mess.getName().toString();
                String mtimee = mess.getTime().toString();
                String message = mess.getMess().toString();

                adap.obj(new messages(message, mtimee, name));
//                adap.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                adap.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                toast("Pid :"+partnerId);
//                toast("id :"+id);
//                toast("message"+editText.getText().toString());
//                toast("time :"+time);
//                toast("user :"+user);

                fire.child("conversation").child(id).child("message").child(partnerId).push().setValue(new messages(editText.getText().toString(), time, user));
                fire.child("conversation").child(partnerId).child("message").child(id).push().setValue(new messages(editText.getText().toString(), time, user));

                editText.setText("");

                adap.notifyDataSetChanged();
                listview.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
                listview.setStackFromBottom(true);
            }
        });

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                position1 = position;

                return false;
            }
        });


//        Toast.makeText(chatUI.this, id, Toast.LENGTH_SHORT).show();
    }

    //
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() != R.id.ListView) {
            return;
        } else {
            String option[] = {"Delete", "Return"};
            menu.setHeaderIcon(android.R.drawable.ic_menu_delete);
            menu.setHeaderTitle("What Do you Want");
            for (String ff : option) {
                menu.add(ff);
            }
            super.onCreateContextMenu(menu, v, menuInfo);
        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getTitle().equals("Delete")) {
            fire.child("conversation").child(id).child("message").child(partnerId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int i = 0;
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        if (i == position1) {
                            Firebase c = dataSnapshot1.getRef();
                            c.removeValue();
                            adap.list.remove(position1);
                            adap.notifyDataSetChanged();
                        }
                        i++;
                    }
//                    Toast.makeText(chatUI.this, "Deleted sucesfully", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat_ui, menu);
        return true;
    }

    public void toast(String message) {
        Toast.makeText(chatUI.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int Id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (Id == R.id.action_settings) {
            return true;
        }
        if (Id == android.R.id.home) {
//            startActivity(new Intent(chatUI.this, afterSignin.class));
//
            finish();
        }
        if (Id == R.id.clearAll) {
            for (int i = 0; i < adap.list.size(); i++) {
                fire.child("conversation").child(id).child("message").child(partnerId).removeValue();

            }
            toast("cleared");
            adap.list.clear();
            adap.notifyDataSetChanged();

        }

        if (Id == R.id.BackGround) {
            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relMain);

            int img[] = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j, R.drawable.k, R.drawable.l, R.drawable.m};
            if (i == img.length) {
                i = 0;
            } else {
                relativeLayout.setBackgroundResource(img[i]);
                i++;

            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
