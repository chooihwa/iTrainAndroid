package com.itrainasia.location;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.util.List;

public class geocodingactivity extends AppCompatActivity {

    EditText address, lat, lng:
    Button geocoding

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geocodingactivity);
    }

    void searchAddress(){
        String addr = address.getText().toString();
        Geocoder geocoder = new Geocoder(this);
        try{
            List<Address> addressList=geocoder.getFromLocationName(locationName,1);


    }
