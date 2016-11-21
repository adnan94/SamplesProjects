package com.example.adnan.webview;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Browser extends ActionBarActivity {
EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
edit=(EditText)findViewById(R.id.editText1);

        ((Button)findViewById(R.id.go)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.url ="https://"+edit.getText().toString();

                Intent i = new Intent(Browser.this, MainActivity.class);
                startActivity(i);
            }
        });



    }

}
