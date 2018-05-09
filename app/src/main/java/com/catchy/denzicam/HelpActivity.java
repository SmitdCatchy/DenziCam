package com.catchy.denzicam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class HelpActivity extends AppCompatActivity {
    PDFView pdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        pdf = findViewById(R.id.pdfHelp);

        pdf.fromAsset("help.pdf").load();
    }
}
