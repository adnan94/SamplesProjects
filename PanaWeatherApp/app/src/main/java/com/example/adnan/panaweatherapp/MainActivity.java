package com.example.adnan.panaweatherapp;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity {
    EditText mCity, mCountry;
    Button mCheck;
    RequestQueue requestQueue;
    Button btn;
    TextView date, day, high, temprature, text, country, city, sunrise, sunset;
    ProgressDialog dialog, dialog1;
    ArrayList<signature> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        startService(new Intent(this, MyService.class));
        casts();
        list = new ArrayList<signature>();
        requestQueue = volleySingleton.getInstance().getRequestQueue();
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewAppWidget.updatee(MainActivity.this);

            }
        });
        ((Button) findViewById(R.id.Mdialog)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                weekLyView();
            }
        });

        mCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                todayUpdate();
            }
        });

    }

    public void weekLyView() {

        if (mCountry.getText().length() == 0 || mCity.getText().length() == 0) {
            toast("Plz fill the empty spaces");
        } else {
            final String url1 = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D\"" + mCity.getText().toString() + "%2C%20" + mCountry.getText().toString() + "\")&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

            dialog = ProgressDialog.show(MainActivity.this, "Fetching Data",
                    "Loading....", true);

//        final String url1 = "";

            JsonObjectRequest obj = new JsonObjectRequest(Request.Method.GET, url1, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    JSONObject obj;
                    try {

                        obj = response.getJSONObject("query")
                                .getJSONObject("results").getJSONObject("channel")
                                .getJSONObject("item");

                        JSONArray jsonArray = obj.getJSONArray("forecast");
                        Log.d("", jsonArray.toString());
                        list.clear();

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject object = jsonArray.getJSONObject(i);
                            String date = object.getString("date");
                            String day = object.getString("day");
                            String low = object.getString("low");
                            String high = object.getString("high");
                            String text = object.getString("text");
                            Global.signature.add(new signature(date, day, high, low, text));
                            dialog.dismiss();


                        }

                        BlankFragment fragInfo = new BlankFragment();
                        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
                        trans.add(R.id.main, fragInfo);
                        trans.addToBackStack("a");
                        trans.commit();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            requestQueue.add(obj);

        }
    }

    public void casts() {
        mCity = (EditText) findViewById(R.id.Mcity);
        mCountry = (EditText) findViewById(R.id.Mcountry);
        mCheck = (Button) findViewById(R.id.Mcheck);

        temprature = (TextView) findViewById(R.id.textView);
        country = (TextView) findViewById(R.id.textView2);
        city = (TextView) findViewById(R.id.textView3);
        text = (TextView) findViewById(R.id.textView4);

        date = (TextView) findViewById(R.id.textView5);
        sunrise = (TextView) findViewById(R.id.textView6);
        sunset = (TextView) findViewById(R.id.textView7);

    }

    public void toast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    public void todayUpdate() {

        if (mCountry.getText().length() == 0 || mCity.getText().length() == 0) {
            toast("Plz fill the empty spaces");
        } else {
            dialog1 = ProgressDialog.show(MainActivity.this, "Fetching Data",
                    "Loading....", true);
//String mmCity="karachi";
//            String mmCountry="pakistan";

            final String url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D\"" + mCity.getText().toString() + "%2C%20" + mCountry.getText().toString() + "\")&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

            JsonObjectRequest obj = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) throws JSONException {
                    JSONObject obj1 = response.getJSONObject("query")
                            .getJSONObject("results").getJSONObject("channel")
                            .getJSONObject("item").getJSONObject("condition");

                    double c = ((Integer.parseInt(obj1.getString("temp")) - 32) * 0.555);
                    int d = (int) c;
                    date.setText(obj1.getString("date"));
                    temprature.setText(d + "°C");
                    text.setText(obj1.getString("text"));
                    city.setText(mCity.getText().toString().toUpperCase());
                    country.setText(mCountry.getText().toString().toUpperCase());
                    JSONObject obj = response.getJSONObject("query")
                            .getJSONObject("results").getJSONObject("channel").getJSONObject("astronomy");

                    sunrise.setText("Sunrise :" + obj.getString("sunrise"));
                    sunset.setText("Sunset :" + obj.getString("sunset"));

                    Global.temperature = d + "°C";
                    Global.city = mCity.getText().toString().toUpperCase();
                    Global.country = mCountry.getText().toString().toUpperCase();
                    Global.sunrise = obj.getString("sunrise");
                    Global.sunset = obj.getString("sunset");
                    btn.setVisibility(View.VISIBLE);
                    dialog1.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            requestQueue.add(obj);
        }
    }
}
