package com.example.adnanahmed.geofencing;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingApi;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, ResultCallback<Status> {


    protected ArrayList<Geofence> mGeofenceList;
    protected GoogleApiClient mGoogleApiClient;
    ListView listView;
    ArrayList<pojo> list;
    Adaptor adaptor;
//    = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        mGeofenceList = new ArrayList<Geofence>();
        list = new ArrayList<>();
        adaptor = new Adaptor(this, list);
        buildGoogleApiClient();
        // Get the geofences used. Geofence data is hard coded in this sample.
        populateGeofenceList();
        // Kick off the request to build GoogleApiClient.
        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this,MyService.class));

//                if (!mGoogleApiClient.isConnected()) {
//                    Toast.makeText(MainActivity.this, "Not Connected", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                try {
//                    LocationServices.GeofencingApi.addGeofences(
//                            mGoogleApiClient,
//                            // The GeofenceRequest object.
//                            getGeofencingRequest(),
//                            // A pending intent that that is reused when calling removeGeofences(). This
//                            // pending intent is used to generate an intent when a matched geofence
//                            // transition is observed.
//                            getGeofencePendingIntent()
//                    ).setResultCallback(MainActivity.this); // Result processed in onResult().
//                } catch (SecurityException securityException) {
//                    // Catch exception generated if the app does not use ACCESS_FINE_LOCATION permission.
//                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alert(position);
            }
        });
    }

    public void alert(final int position) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        View v = getLayoutInflater().inflate(R.layout.layout_alert, null);
        final EditText lati = (EditText) v.findViewById(R.id.latitude);
        final EditText longi = (EditText) v.findViewById(R.id.longitude);
        final EditText rad = (EditText) v.findViewById(R.id.radius);
        lati.setText(""+list.get(position).getLatitude());
        longi.setText(""+list.get(position).getLongitude());
        rad.setText(""+list.get(position).getRadiuse());

        alert.setView(v);
        alert.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!mGoogleApiClient.isConnected()) {
                    Toast.makeText(MainActivity.this, "Not Connected", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {

                    double lat = 0;
                    double lon = 0;
                    float radi = 0;
                    lat = Double.parseDouble(lati.getText().toString());
                    lon = Double.parseDouble(longi.getText().toString());
                    radi = Float.parseFloat(rad.getText().toString());
                    String key = list.get(position).getId();
                    if (lat == 0.0 && lon == 0.0 && radi == 0.0) {
                        Toast.makeText(MainActivity.this, "Enter Lat Lon", Toast.LENGTH_SHORT).show();
                    } else {
                        LocationServices.GeofencingApi.addGeofences(
                                mGoogleApiClient,
                                // The GeofenceRequest object.
                                getGeofenceRequest(position, lat, lon, radi, key),
                                // A pending intent that that is reused when calling removeGeofences(). This
                                // pending intent is used to generate an intent when a matched geofence
                                // transition is observed.
                                getGeofencePendingIntent()
                        ).setResultCallback(new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                Toast.makeText(MainActivity.this, "added gence sucessfully", Toast.LENGTH_SHORT).show();

                            }
                        }); // Result processed in onResult().

                    }


                } catch (SecurityException securityException) {
                    // Catch exception generated if the app does not use ACCESS_FINE_LOCATION permission.
                }
            }
        });
        alert.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                LocationServices.GeofencingApi.removeGeofences(mGoogleApiClient, geofencIds)
//                        .setResultCallback(new ResultCallback<Status>() {
//
//                            @Override
//                            public void onResult(Status status) {
//                                if (status.isSuccess()){
//                                 Remove notifiation here
//                            }
                final List<String> removeGeofences = new ArrayList<String>();
                removeGeofences.add(list.get(position).getId());
                LocationServices.GeofencingApi.removeGeofences(mGoogleApiClient, removeGeofences).setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        if (status.isSuccess()) {
                            Toast.makeText(MainActivity.this, "Removed Fence ", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
//                        });
            }
        });
        alert.setTitle("What You Want To Do");

        AlertDialog ad = alert.create();

        alert.show();
    }

    private GeofencingRequest getGeofencingRequest() {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        builder.addGeofences(mGeofenceList);
        return builder.build();

    }

    private GeofencingRequest getGeofenceRequest(int position, double lat, double lon, float rad, String key) {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        Log.d("FFF", key);
        builder.addGeofence(new Geofence.Builder()
                .setRequestId(key)
                .setCircularRegion(lat, lon, rad)
                .setExpirationDuration(Constants.GEOFENCE_EXPIRATION_IN_MILLISECONDS)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER |
                        Geofence.GEOFENCE_TRANSITION_EXIT)
                .build());
        return builder.build();
    }

//    public Geofence get(){

    // }
    public void populateGeofenceList() {

        for (Map.Entry<String, LatLng> entry : Constants.BAY_AREA_LANDMARKS.entrySet()) {
            Log.d("ADNAN", entry.getKey());

            mGeofenceList.add(new Geofence.Builder()
                    // Set the request ID of the geofence. This is a string to identify this
                    // geofence.
                    .setRequestId(entry.getKey())
                    // Set the circular region of this geofence.
                    .setCircularRegion(
                            entry.getValue().latitude,
                            entry.getValue().longitude,
                            Constants.GEOFENCE_RADIUS_IN_METERS
                    )

                    // Set the expiration duration of the geofence. This geofence gets automatically
                    // removed after this period of time.
                    .setExpirationDuration(Constants.GEOFENCE_EXPIRATION_IN_MILLISECONDS)

                    // Set the transition types of interest. Alerts are only generated for these
                    // transition. We track entry and exit transitions in this sample.
                    .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER |
                            Geofence.GEOFENCE_TRANSITION_EXIT)

                    // Create the geofence.
                    .build());

            double latitude = entry.getValue().latitude;
            double longitude = entry.getValue().longitude;
            float radiusInMeters = Constants.GEOFENCE_RADIUS_IN_METERS;

            list.add(new pojo(latitude, longitude, radiusInMeters, entry.getKey()));
            listView.setAdapter(adaptor);
            adaptor.notifyDataSetChanged();

        }
    }

    protected synchronized void buildGoogleApiClient() {
        Log.d("YOOO","build");

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    private PendingIntent getGeofencePendingIntent() {
        Intent intent = new Intent(this, GeofenceTransitionIntentService.class);
        // We use FLAG_UPDATE_CURRENT so that we get the same pending intent back when calling addgeoFences()
        return PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @Override
    public void onConnected(Bundle bundle) {
        LocationRequest locationRequest = LocationRequest.create()
                .setInterval(1000) // every 10 minutes
                .setExpirationDuration(10 * 1000) // After 10 seconds
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d("YOOO","Suspend");

        mGoogleApiClient.connect();

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // Do something with result.getErrorCode());

    }

    @Override
    public void onResult(Status status) {
        if (status.isSuccess()) {
            Toast.makeText(
                    this,
                    "Geofences Added",
                    Toast.LENGTH_SHORT
            ).show();
        } else {
            // Get the status code for the error and log it using a user-friendly message.
            String errorMessage = GeofenceErrorMessages.getErrorString(this,
                    status.getStatusCode());
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!mGoogleApiClient.isConnecting() || !mGoogleApiClient.isConnected()) {
           Log.d("YOOO","onStart");
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnecting() || mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }
}
