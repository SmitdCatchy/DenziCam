package com.catchy.denzicam;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class ResultActivity extends AppCompatActivity {

    private float percentage;
    private ArrayList<Tree> trees;
    private FusedLocationProviderClient client;

    private TextView txtAverage;
    private TextView txtPercentage;
    private TextView txtOxygen;
    private TextView txtCarbon;
    private TextView txtGPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        percentage = ChartActivity.usedPercentage;
        trees = new ArrayList<>(ChartActivity.trees);
        client = LocationServices.getFusedLocationProviderClient(this);

        txtAverage = findViewById(R.id.txtResultAverage);
        txtPercentage = findViewById(R.id.txtResultPercentage);
        txtOxygen = findViewById(R.id.txtResultOxygen);
        txtCarbon = findViewById(R.id.txtResultCarbon);
        txtGPS = findViewById(R.id.txtResultGPS);

        analyze();


        requestPermissions();
        getLocation();
    }

    private void analyze(){
        int s = 0;
        int c = 0;

        if (trees.size() < 1){
            s = 45;
            c = 1;
        }
        else {
            for(Tree t : trees){
                s += t.getAge();
                c++;
            }
        }

        int average = Math.round((s) / c);
        float oxygen = Math.round(Tree.getOxygen(average) * percentage) / 100.0f;
        float carbon = Math.round(Tree.getCarbon(average) * percentage) / 100.0f;

        String toDisplay = getString(R.string.result_average) + " " + average + ".";
        txtAverage.setText(toDisplay);
        toDisplay = getString(R.string.result_percentage)+ " " + percentage + "%.";
        txtPercentage.setText(toDisplay);
        toDisplay = getString(R.string.result_oxygen) + " " + oxygen + "kg.";
        txtOxygen.setText(toDisplay);
        toDisplay = getString(R.string.result_carbon) + " " + carbon + "kg.";
        txtCarbon.setText(toDisplay);

    }

    private void getLocation() {
        if( ActivityCompat.checkSelfPermission( ResultActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) return;
        client.getLastLocation().addOnSuccessListener(ResultActivity.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    String toDisplay = "Szélesség: " + location.getLatitude() + ",\nHosszúság: " + location.getLongitude() + ".";
                    txtGPS.setText(location.toString());
                }
            }
        });
    }

    private void requestPermissions(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }
}
