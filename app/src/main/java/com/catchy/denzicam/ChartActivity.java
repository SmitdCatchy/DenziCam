package com.catchy.denzicam;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class ChartActivity extends AppCompatActivity {

    private float percentage;
    public static float usedPercentage;
    private TextView txtPercentage;
    private TextView txtMasked;
    private TextView txtTrees;
    private Spinner spinnerName;
    private EditText txtPerimeter;

    public static ArrayList<Tree> trees;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);


        ImageView imgHeader = findViewById(R.id.imgChartHeader);
        txtPercentage = findViewById(R.id.txtChartPercentage);
        txtMasked = findViewById(R.id.txtMasked);
        txtPerimeter = findViewById(R.id.txtEditPerimeter);
        txtTrees = findViewById(R.id.txtChartTrees);
        spinnerName = findViewById(R.id.spinnerTrees);
        Switch switchMask = findViewById(R.id.switchMask);
        Button btnAdd = findViewById(R.id.btnChartAdd);
        Button btnRemove = findViewById(R.id.btnChartRemove);
        Button btnResults = findViewById(R.id.btnChartResults);

        percentage = getIntent().getFloatExtra("PERCENTAGE", 0.0f);

        usedPercentage = percentage;
        Bitmap headerBitmap = Bitmap.createBitmap(
                AnalyzeActivity.layer,
                getIntent().getIntExtra("WIDTH", 0),
                getIntent().getIntExtra("HEIGHT", 0),
                Bitmap.Config.ARGB_8888);

        imgHeader.setImageBitmap(headerBitmap);
        String init = usedPercentage+ "%";
        txtPercentage.setText(init);
        switchMask.setChecked(true);
        trees = new ArrayList<>();

        txtTrees.setMovementMethod( new ScrollingMovementMethod());

        switchMask.setOnCheckedChangeListener((view, isChecked) -> {
            if(isChecked){
                usedPercentage = percentage;
                String masked = usedPercentage + "%";
                txtPercentage.setText(masked);
                txtMasked.setText(R.string.chart_masked);
            }else{
                usedPercentage =  Math.round((100.0f - percentage) * 100) / 100.0f;
                String unmasked =  usedPercentage + "%";
                txtPercentage.setText(unmasked);
                txtMasked.setText(R.string.chart_unmasked);
            }
        });

        btnAdd.setOnClickListener(view -> addTree());
        btnRemove.setOnClickListener(view -> removeTree());
        btnResults.setOnClickListener(view -> calcResult());

    }

    private void addTree() {
        int p;

        try {
            p = Integer.parseInt(txtPerimeter.getText().toString().replaceAll("[^\\d.]", ""));
        }catch (NumberFormatException e){
            p = 0;
        }
        trees.add(new Tree(spinnerName.getSelectedItem().toString(), p));
        printTrees();
    }

    private void removeTree() {
        if( trees.size() >= 1) {
            trees.remove(trees.size() - 1);
            printTrees();
        }
    }

    private void printTrees() {
        StringBuilder toPrint = new StringBuilder();
        for(Tree t : trees){
            toPrint.append(t.getName()).append(", ").append(t.getAge()).append(" éves.\n");
        }
        txtTrees.setText(toPrint.toString());

    }

    private void calcResult() {
        Intent goToResult = new Intent(this, ResultActivity.class);
        goToResult.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(goToResult);
    }

}
