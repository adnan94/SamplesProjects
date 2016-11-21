package com.example.adnan.panamaps;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.audiofx.BassBoost;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    LocationManager locationMangaer;
    EditText editLocation;
    ProgressBar pb;
    Button btnGetLocation;
    LocationListener listener;
    String provider;
    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setRequestedOrientation(ActivityInfo
                .SCREEN_ORIENTATION_PORTRAIT);
        FloatingActionButton f = (FloatingActionButton) findViewById(R.id.fab);
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });
        casts();
        locationMangaer = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        btnGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener = new myLocationListener();
                pb.setVisibility(View.VISIBLE);
                boolean flag = displayGpsStatus();
                editLocation.setText("Please!! move your device to" +
                        " see the changes in coordinates." + "\nWait..");
                refresh();

            }
//            else {
//                    alertbox("Gps", "Plz activate android GPS first !");
//                }
//            }
        });

    }

    protected void alertbox(String title, String mymessage) {
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

    private Boolean displayGpsStatus() {
        boolean gpsStatus = locationMangaer.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (gpsStatus) {
            toast("gps is on");
            return true;
        } else {
            toast("gps is off");
            return false;
        }
    }

    public void toast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
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

        locationMangaer.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 1, listener);

        locationMangaer.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 1, listener);


    }

    public void casts() {
        pb = (ProgressBar) findViewById(R.id.progressBar1);
        pb.setVisibility(View.INVISIBLE);

        editLocation = (EditText) findViewById(R.id.editTextLocation);

        btnGetLocation = (Button) findViewById(R.id.btnLocation);
    }


    private class myLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
//            toast("inside");

            Log.d("longi :", "" + location.getLongitude());
//            editLocation.setText("");
//            pb.setVisibility(View.INVISIBLE);
//                                editLocation.setText("Latitude :" + location.getLatitude() + "\n Longitude :" + location.getLongitude());

            String city = "";
            Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
            List<Address> addresses;

            try {
                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                if (addresses.size() > 0) {
                    city = addresses.get(0).getLocality();
                    String country = addresses.get(0).getCountryName();
//                    addresses.clear();

//                    Log.d("tag", addresses.get(0).getCountryName());
//                    Log.d("tag", addresses.get(0).getSubLocality());
                    String s = "Address Line: "
                            + addresses.get(0).getAddressLine(0) + "\n"
                            + addresses.get(0).getFeatureName() + "\n"
                            + "Locality: "
                            + addresses.get(0).getLocality() + "\n"
                            + addresses.get(0).getPremises() + "\n"
                            + "Admin Area: "
                            + addresses.get(0).getAdminArea() + "\n"
                            + "Country code: "
                            + addresses.get(0).getCountryCode() + "\n"
                            + "Country name: "
                            + addresses.get(0).getCountryName() + "\n"
                            + "Phone: " + addresses.get(0).getPhone()
                            + "\n" + "Postbox: "
                            + addresses.get(0).getPostalCode() + "\n"
                            + "SubLocality: "
                            + addresses.get(0).getSubLocality() + "\n"
                            + "SubAdminArea: "
                            + addresses.get(0).getSubAdminArea() + "\n"
                            + "SubThoroughfare: "
                            + addresses.get(0).getSubThoroughfare()
                            + "\n" + "Thoroughfare: "
                            + addresses.get(0).getThoroughfare()
                            + "\n" + "Latitude: " + location.getLatitude()
                            + "\n" + "Longitude:" + location.getLongitude();
                    pb.setVisibility(View.INVISIBLE);
                    editLocation.setText(s);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    @Override
    protected void onPause() {
        super.onPause();
//locationMangaer.removeUpdates(this);
    }
}
