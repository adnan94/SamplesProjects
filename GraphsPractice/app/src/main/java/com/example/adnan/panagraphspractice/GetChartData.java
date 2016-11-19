package com.example.adnan.panagraphspractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class GetChartData extends AppCompatActivity {
    EditText text, text2, text3, text4, text5, text6, text7, text8, text9, text10;
    EditText mtext, mtext2, mtext3, mtext4, mtext5, mtext6, mtext7, mtext8, mtext9, mtext10;

    Comm com;
    ArrayList<String> listString;
    ArrayList<Integer> listInteger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_chart_data);
        listString = new ArrayList<>();
        listInteger = new ArrayList<>();

        text = (EditText) findViewById(R.id.editText);
        text2 = (EditText) findViewById(R.id.editText2);
        text3 = (EditText) findViewById(R.id.editText3);
        text4 = (EditText) findViewById(R.id.editText4);
        text5 = (EditText) findViewById(R.id.editText5);
        text6 = (EditText) findViewById(R.id.editText6);
        text7 = (EditText) findViewById(R.id.editText7);
        text8 = (EditText) findViewById(R.id.editText8);
        text9 = (EditText) findViewById(R.id.editText9);
        text10 = (EditText) findViewById(R.id.editText10);

        mtext = (EditText) findViewById(R.id.value);
        mtext2 = (EditText) findViewById(R.id.value2);
        mtext3 = (EditText) findViewById(R.id.value3);
        mtext4 = (EditText) findViewById(R.id.value4);
        mtext5 = (EditText) findViewById(R.id.value5);
        mtext6 = (EditText) findViewById(R.id.value6);
        mtext7 = (EditText) findViewById(R.id.value7);
        mtext8 = (EditText) findViewById(R.id.value8);
        mtext9 = (EditText) findViewById(R.id.value9);
        mtext10 = (EditText) findViewById(R.id.value10);

        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listString.add(text.getText().toString());
                listString.add(text2.getText().toString());
                listString.add(text3.getText().toString());
                listString.add(text4.getText().toString());
                listString.add(text5.getText().toString());
                listString.add(text6.getText().toString());
                listString.add(text7.getText().toString());
                listString.add(text8.getText().toString());
                listString.add(text9.getText().toString());
                listString.add(text10.getText().toString());
//int value=
                listInteger.add(Integer.parseInt(mtext.getText().toString()));
                listInteger.add(Integer.parseInt(mtext2.getText().toString()));
                listInteger.add(Integer.parseInt(mtext3.getText().toString()));
                listInteger.add(Integer.parseInt(mtext4.getText().toString()));
                listInteger.add(Integer.parseInt(mtext5.getText().toString()));
                listInteger.add(Integer.parseInt(mtext6.getText().toString()));
                listInteger.add(Integer.parseInt(mtext7.getText().toString()));
                listInteger.add(Integer.parseInt(mtext8.getText().toString()));
                listInteger.add(Integer.parseInt(mtext9.getText().toString()));
                listInteger.add(Integer.parseInt(mtext10.getText().toString()));
                                Utils.getLists(listString, listInteger);
                Intent i = new Intent(GetChartData.this, Main2Activity.class);
                startActivity(i);
            }
        });
    }
}
