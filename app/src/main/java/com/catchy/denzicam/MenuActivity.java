package com.catchy.denzicam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnAnalyze = findViewById(R.id.btnMenuAnalyze);
        Button btnArchive = findViewById(R.id.btnMenuArchive);
        Button btnHelp = findViewById(R.id.btnMenuHelp);

        btnAnalyze.setOnClickListener(view -> {
            Intent intent = new Intent(this, AnalyzeActivity.class);
            startActivity(intent);
        });
        btnArchive.setOnClickListener(view -> {
            Intent intent = new Intent(this, ArchiveActivity.class);
            startActivity(intent);
        });
        btnHelp.setOnClickListener(view -> {
            Intent intent = new Intent(this, HelpActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {}
}
