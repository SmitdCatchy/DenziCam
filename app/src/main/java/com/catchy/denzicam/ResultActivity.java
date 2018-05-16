package com.catchy.denzicam;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.Date;

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
    private EditText txtName;

    private int average;
    private float oxygen;
    private float carbon;
    private double latitude;
    private double longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        txtAverage = findViewById(R.id.txtResultAverage);
        txtPercentage = findViewById(R.id.txtResultPercentage);
        txtOxygen = findViewById(R.id.txtResultOxygen);
        txtCarbon = findViewById(R.id.txtResultCarbon);
        txtGPS = findViewById(R.id.txtResultGPS);
        txtName = findViewById(R.id.txtResultName);
        Button btnGPS = findViewById(R.id.btnResultGPS);
        Button btnSave = findViewById(R.id.btnResultSave);
        Button btnDismiss = findViewById(R.id.btnResultDismiss);

        percentage = ChartActivity.usedPercentage;
        trees = new ArrayList<>(ChartActivity.trees);
        client = LocationServices.getFusedLocationProviderClient(this);

        analyze();
        requestPermissions();

        btnGPS.setOnClickListener(view -> getLocation());
        btnSave.setOnClickListener(view -> {
            Toast.makeText(this, "Eredmények elmentve", Toast.LENGTH_SHORT).show();
            IO.add( this, new Result( txtName.getText().toString(), average, percentage, oxygen, carbon, latitude, longitude, new Date()).toString());

            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        });
        btnDismiss.setOnClickListener(view -> {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        });
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

        average = Math.round((s) / c);

        float turfOxigen = (100 - percentage) * 15;
        float turfCarbon = (100 - percentage) * 20;

        oxygen = Math.round(Tree.getOxygen(average) * percentage + turfOxigen) / 100.0f;
        carbon = Math.round(Tree.getCarbon(average) * percentage + turfCarbon) / 100.0f;

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
        client.getLastLocation().addOnSuccessListener(ResultActivity.this, location -> {
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                String toDisplay = "Szélesség: " + latitude + ",\nHosszúság: " + longitude + ".";
                txtGPS.setText(toDisplay);
            }
        });
    }

    private void requestPermissions(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }
}
