package com.example.adnan.panamaps;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    public GoogleMap mMap;
    LocationManager manager;
    String provider;
    double lati;
    double longi;
    boolean isGPsEnabled = false;
    boolean inNetworkEnabled = false;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    Location mLastLocation;
    LocationListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
//        mMap.setMyLocationEnabled(true);
        mapFragment.getMapAsync(this);
//                mMap.getUiSettings().setZoomControlsEnabled(true);

        manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        listener = new myLocationListener();
//        refresh();
//        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        if (displayGpsStatus()) {
            refresh();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Your Device's GPS is Disable")
                    .setCancelable(false)
                    .setTitle("** Gps Status **")
                    .setPositiveButton("Gps On",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // finish the current activity
                                    // AlertBoxAdvance.this.finish();Intent myIntent = new Intent(
                                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                    startActivity(intent);

                                    dialog.cancel();
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // cancel the dialog box
                                    dialog.cancel();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
        }
//refresh();

//        }
    }

    public void refresh() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return;
            }
        }
        Log.d("hello", "dfgfd");
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, listener);
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, listener);

    }

    private Boolean displayGpsStatus() {
        boolean gpsStatus = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (gpsStatus) {
            toast("gps is on");
            return true;
        } else {
            toast("gps is off");
            return false;
        }
    }

    public void toast(String message) {
        Toast.makeText(MapsActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setOnMapLongClickListener(this);

        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setBuildingsEnabled(true);
        Log.d("onMapReaddy", "readdy");
//        24.8600° N,
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(0, 0);
//        ° N, ° E
        mMap.addMarker(new MarkerOptions().position(sydney).title("My Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


    @Override
    protected void onResume() {
        super.onResume();
//        toast("onResume");
        refresh();

    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        toast("clicked");

        CircleOptions circleOptions = new CircleOptions()
                .center(new LatLng(latLng.latitude, latLng.longitude))

                .radius(100)
                .fillColor(0x40ff0000)
                .strokeColor(Color.TRANSPARENT)
                .strokeWidth(2);

// Get back the mutable Circle
        Circle circle = mMap.addCircle(circleOptions);
        Geocoder geo = new Geocoder(getBaseContext(), Locale.getDefault());
        List<Address> list;
        try {
            list = geo.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (list.size() > 0) {
//                mMap.addMarker(new MarkerOptions().position(latLng).title(list.get(0).getFeatureName())).showInfoWindow();

                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(list.get(0).getFeatureName())
                        .snippet(list.get(0).getFeatureName())
                        );
                marker.showInfoWindow();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private class myLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            Log.d("lati", "" + location.getLatitude());
            // Add a marker in Sydney and move the camera
            mMap.clear();

            LatLng sydney1 = new LatLng(location.getLatitude(), location.getLongitude());
//        ° N, ° E
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney1));

//            CameraPosition init=new CameraPosition.Builder().target(new LatLng(location.getLatitude(), location.getLongitude().
            mMap.addMarker(new MarkerOptions().position(sydney1).title("My Location"));
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

}