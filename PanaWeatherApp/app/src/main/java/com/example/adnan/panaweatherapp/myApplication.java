package com.example.adnan.panaweatherapp;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Adnan on 10/27/2015.
 */

/**
 * Created by Adnan on 10/22/2015.
 */
public class myApplication extends Application {
    private static myApplication mInstance;

    //    RequestQueue requestQueue;
    //    TextView date, day, high, temprature, text, country, city, sunrise, sunset;
//    ProgressDialog dialog, dialog1;
    public static Context con;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        con = myApplication.this;
    }

    public static myApplication getInstance() {
        return mInstance;
    }
//    public void todayUpdate() {
//        requestQueue = volleySingleton.getInstance().getRequestQueue();
//
////        if (mCountry.getText().length() == 0 || mCity.getText().length() == 0) {
////            toast("Plz fill the empty spaces");
////        } else
////    {
//        String mCity="lahore";
//        String mCountry="pakistan";
////        dialog1 = ProgressDialog.show(getApplicationContext(), "Fetching Data",
////                "Loading....", true);
//        Log.d("TAG","inside today ");
//        final String url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D\"" + mCity + "%2C%20" + mCountry + "\")&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
//
//        JsonObjectRequest obj = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) throws JSONException {
//                JSONObject obj1 = response.getJSONObject("query")
//                        .getJSONObject("results").getJSONObject("channel")
//                        .getJSONObject("item").getJSONObject("condition");
//
//                double c = ((Integer.parseInt(obj1.getString("temp")) - 32) * 0.555);
//                int d = (int) c;
////                    date.setText(obj1.getString("date"));
////                    temprature.setText(d + "°C");
////                    text.setText(obj1.getString("text"));
////                    city.setText(mCity.getText().toString().toUpperCase());
////                    country.setText(mCountry.getText().toString().toUpperCase());
//
//                JSONObject obj = response.getJSONObject("query")
//                        .getJSONObject("results").getJSONObject("channel").getJSONObject("astronomy");
////                    sunrise.setText("Sunrise :" + obj.getString("sunrise"));
////                    sunset.setText("Sunset :" + obj.getString("sunset"));
//                Global.temperature = d + "°C";
//                Global.city = "Karachi";
//                Global.country ="Pakistan";
//                Global.sunrise=obj.getString("sunrise");
//                Global.sunset=obj.getString("sunset");
//                NewAppWidget.updatee(getApplicationContext());
//
//                dialog1.dismiss();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        requestQueue.add(obj);
//    }
//    public static Context getAppContext() {
//        return mInstance.getApplicationContext();
//
//    }

}
