package com.example.adnanahmed.geofencing;

/**
 * Created by Adnan Ahmed on 1/9/2017.
 */

public class pojo {

    double latitude, longitude;
    float radiuse;
    String id;

    public String getId() {
        return id;
    }

    public pojo(double latitude, double longitude, float radius, String id) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.radiuse = radius;
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public float getRadiuse() {
        return radiuse;
    }
}
