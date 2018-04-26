package com.catchy.denzicam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button buttonMenuAnalyze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        buttonMenuAnalyze = findViewById(R.id.btnMenuAnalyze);
        buttonMenuAnalyze.setOnClickListener(view -> goToAnalyze());
    }

    public void goToAnalyze(){
        Intent intent = new Intent(this, AnalyzeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {}
}
