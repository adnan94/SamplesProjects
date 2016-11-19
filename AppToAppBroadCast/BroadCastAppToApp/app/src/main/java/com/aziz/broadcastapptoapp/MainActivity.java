package com.aziz.broadcastapptoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (EditText) findViewById(R.id.editText);

        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (text.getText() != null) {
                    Intent intent = new Intent();
                    intent.setAction("com.example.Broadcast");
                    intent.putExtra("High", text.getText().toString());
                    sendBroadcast(intent);//        Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "Emty text", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
