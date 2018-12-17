package com.itrainasia.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LocationListener {

    LocationManager locationManager;
    TextView txtLat, txtLng, txtAltitute;
    String provider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtLat = findViewById(R.id.latitude);
        txtLng = findViewById(R.id.longitude);
        txtAltitute = findViewById(R.id.altitute);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) &&
                    ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.ACCESS_COARSE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        0);
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},0);

            }
        } else {
            Location location = locationManager.getLastKnownLocation(provider);
            if(location != null){
                Log.v("Location","Provider " + provider + " is selected");
                onLocationChanged(location);
            }
            else {
                txtLat.setText("Not Available");
                txtLng.setText("Not Available");
                txtAltitute.setText("Not Available");
            }
        }
    }
    @Override
    protected void onResume(){
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},0);
            return;
        }
        locationManager.requestLocationUpdates(provider,1,1,this);
    }

    @Override
    protected void onPause() {
        locationManager.removeUpdates(this);
        super.onPause();
    }



    @Override
    public void onLocationChanged(Location location) {
        float lng = (float) location.getLongitude();
        float lat = (float) location.getLatitude();
        float alt = (float) location.getAltitude();
        txtLat.setText("Latitute:" + lat);
        txtLng.setText("Longitute:" + lng);
        txtAltitute.setText("Altitute:" + alt);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("on status changed", "Error");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("on provider enable", "Error");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("on provider disabled", "Error");


    }
}
