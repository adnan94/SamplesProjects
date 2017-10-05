package com.example.adnanahmed.restapi;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String urlStringRequest = "http://httpbin.org/html";
    String urlImage = "https://www.androidcentral.com/sites/androidcentral.com/files/postimages/684/podcast_featured_3.jpg";
    String jsonObject = "https://openexchangerates.org/api/latest.json?app_id=39c04d08a46e4f6a80bcce0b38c89ec6";
    TextView textView;
    ImageView imageView;
    ImageLoader imageLoader;
    RequestQueue requestQueue;
    ArrayList<Model> list;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);
        requestQueue = VolleySingleTon.getInstance().getRequestQueue();
        list = new ArrayList<Model>();
        ((Button) findViewById(R.id.stringRequest)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                if (flag) {
//                    for (int i = 0; i < list.size(); i++) {
//                        Log.d("SumbalAdnan", list.get(i).getId() + "\n" + list.get(i).getUserId() + "\n" + list.get(i).getTitle() + "\n" + list.get(i).getBody() + "\n \n \n");
//                    }
//                } else {
//                    jsonArrayRequest();
//                    flag = true;
//                }
                jsonObjectRequest(jsonObject);
            }
        });
    }

    public void jsonObjectRequest(String objectUrl) {
        JsonObjectRequest objRequest = new JsonObjectRequest(Request.Method.GET, objectUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    textView.setText(jsonObject.getString("disclaimer") + "\n" + jsonObject.getString("license") + "\n" + jsonObject.getString("timestamp") + "\n" + jsonObject.getString("rates"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        requestQueue.add(objRequest);


    }

    public void jsonArrayRequest() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("http://jsonplaceholder.typicode.com/posts", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject object = response.getJSONObject(i);
                        list.add(new Model(object.getString("userId"), object.getString("id"), object.getString("title"), object.getString("body")));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    public void imageRequest() {
        imageLoader = VolleySingleTon.sInstance.getImgLoad();
        imageLoader.get(urlImage, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                imageView.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Eroorrrr", "Erods");
            }
        });

    }

    public void stringRequest() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlStringRequest, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                textView.setText(response.toString());

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }
}
