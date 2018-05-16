package com.catchy.denzicam;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AnalyzeActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Button btnPhoto;
    private Button btnDone;
    private Button btnMask;
    private Button btnUndo;
    private ImageView background;
    private TextView txtColor;

    private int pickedR;
    private int pickedG;
    private int pickedB;

    private String pictureImagePath;

    private int imageWidth;
    private int imageHeight;

    private ArrayList<int[]> layers = new ArrayList<>();

    public static int[] layer;

    private SeekBar skbTolerance;
    private int tolerance = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze);

        background = findViewById(R.id.imgAnalyzeBackground);
        txtColor = findViewById(R.id.txtAnalyzeColor);
        btnPhoto = findViewById(R.id.btnAnalyzeTakePhoto);
        btnPhoto.setOnClickListener(view -> dispatchTakePictureIntent());
        btnDone = findViewById(R.id.btnAnalyzeDone);
        btnMask = findViewById(R.id.btnAnalyzeMask);
        btnUndo = findViewById(R.id.btnAnalyzeUndo);
        skbTolerance = findViewById(R.id.skbAnalyze);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) skbTolerance.setMin(10);
        skbTolerance.setMax(100);
        skbTolerance.setProgress(tolerance);

        btnDone.setVisibility(View.INVISIBLE);
        btnMask.setVisibility(View.INVISIBLE);
        btnUndo.setVisibility(View.INVISIBLE);
        skbTolerance.setVisibility(View.INVISIBLE);

    }

    private File createImageFile() {
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = new File(storageDir, "temp.jpg");
        pictureImagePath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        Intent chooser = new Intent(Intent.ACTION_CHOOSER);
        chooser.putExtra(Intent.EXTRA_INTENT, galleryIntent);
        chooser.putExtra(Intent.EXTRA_TITLE, "Válasszon az alábbiak közül");


        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile;
            photoFile = createImageFile();
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                Intent[] intentArray = {cameraIntent};
                chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
                startActivityForResult(chooser, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    @SuppressLint("ShowToast")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap bitmap = null;

            if (data == null || data.getData() == null) {
                bitmap = BitmapFactory.decodeFile(pictureImagePath);
                if (bitmap.getWidth() > bitmap.getHeight()) {
                    Matrix matrix = new Matrix();
                    matrix.setRotate(90);
                    bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                }

            } else {
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                    if (bitmap.getWidth() > bitmap.getHeight()) {
                        Matrix matrix = new Matrix();
                        matrix.setRotate(90);
                        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bitmap != null && bitmap.getWidth() > 720) {
                float scale = 720.0f / (float) bitmap.getWidth();
                int height = (int) (scale * bitmap.getHeight());

                bitmap = Bitmap.createScaledBitmap(bitmap, 720, height, true);
            }
            if (bitmap != null && bitmap.getHeight() > 1280) {
                float scale = 1280.0f / bitmap.getHeight();
                int width = (int) (scale * bitmap.getWidth());

                bitmap = Bitmap.createScaledBitmap(bitmap, width, 1280, true);
            }
            if(bitmap != null ) {
                imageWidth = bitmap.getWidth();
                imageHeight = bitmap.getHeight();


                bitmap = Bitmap.createScaledBitmap(bitmap, imageWidth, imageHeight, true);

                int[] layer = new int[imageWidth * imageHeight];
                bitmap.getPixels(layer, 0, imageWidth, 0, 0, imageWidth, imageHeight);

                layers.add(layer);
                background.setImageBitmap(bitmap);

                initiateMasking();
            } else Toast.makeText(this, "Hiba merült fel a kép betöltése során.", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(this, "Hiba merült fel a kép betöltése során.", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initiateMasking() {
        btnPhoto.setVisibility(View.INVISIBLE);
        btnDone.setVisibility(View.VISIBLE);
        btnMask.setVisibility(View.VISIBLE);
        btnUndo.setVisibility(View.VISIBLE);
        skbTolerance.setVisibility(View.VISIBLE);
        background.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN
                    || motionEvent.getAction() == MotionEvent.ACTION_MOVE
                    ) {
                txtColor.setVisibility(View.VISIBLE);
                background.setDrawingCacheEnabled(true);
                background.buildDrawingCache(true);
                Bitmap bitmap = background.getDrawingCache();
                int pixel = bitmap.getPixel((int) motionEvent.getX(), (int) motionEvent.getY());

                pickedR = Color.red(pixel);
                pickedG = Color.green(pixel);
                pickedB = Color.blue(pixel);

                String mask = "Maszk";

                txtColor.setBackgroundColor(Color.rgb(pickedR, pickedG, pickedB));
                String toWrite = "RGB(" + pickedR + ";" + pickedG + ";" + pickedB + ")";
                if (pickedR < 128 && pickedG < 128 && pickedB < 128) {
                    txtColor.setTextColor(Color.rgb(255, 255, 255));
                } else txtColor.setTextColor(Color.rgb(0, 0, 0));
                if (pickedR == 255 && pickedG == 0 && pickedB == 0) txtColor.setText(mask);
                else txtColor.setText(toWrite);

            } else {
                txtColor.setVisibility(View.INVISIBLE);
            }
            background.setDrawingCacheEnabled(false);
            return true;
        });
        skbTolerance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tolerance = progress;
                String tol = "Tolerancia: " + tolerance;
                txtColor.setText(tol);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                txtColor.setVisibility(View.VISIBLE);
                txtColor.setBackgroundColor(Color.rgb(255, 255, 255));
                txtColor.setTextColor(Color.rgb(0, 0, 0));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                txtColor.setVisibility(View.INVISIBLE);
            }
        });
        btnMask.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                if (layers.size() >= 10) {
                    Toast.makeText(this, "Tíznél több maszkolási réteget nem lehet alkalmazni.", Toast.LENGTH_SHORT).show();
                } else {
                    if (
                            pickedR == 255 &&
                                    pickedG == 0 &&
                                    pickedB == 0
                            ) {
                        Toast.makeText(this, "Nem maszkolható felület.", Toast.LENGTH_SHORT).show();
                    } else {
                        Bitmap bitmap = Bitmap.createBitmap(layers.get(layers.size() - 1), imageWidth, imageHeight, Bitmap.Config.ARGB_8888);
                        bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
                        Bitmap masked;

                        int bitmapWidth = bitmap.getWidth();
                        int bitmapHeight = bitmap.getHeight();

                        int t = tolerance;

                        int[] old = new int[bitmapWidth * bitmapHeight];
                        int[] news = new int[bitmapWidth * bitmapHeight];

                        bitmap.getPixels(old, 0, bitmapWidth, 0, 0, bitmapWidth, bitmapHeight);

                        for (int i = 0; i < old.length; i++) {
                            if (Color.red(old[i]) <= pickedR + t) {
                                if (Color.red(old[i]) >= pickedR - t) {
                                    if (Color.green(old[i]) <= pickedG + t) {
                                        if (Color.green(old[i]) >= pickedG - t) {
                                            if (Color.blue(old[i]) <= pickedB + t) {
                                                if (Color.blue(old[i]) >= pickedB - t) {
                                                    news[i] = Color.rgb(255, 0, 0);
                                                } else
                                                    news[i] = Color.rgb(Color.red(old[i]), Color.green(old[i]), Color.blue(old[i]));
                                            } else
                                                news[i] = Color.rgb(Color.red(old[i]), Color.green(old[i]), Color.blue(old[i]));
                                        } else
                                            news[i] = Color.rgb(Color.red(old[i]), Color.green(old[i]), Color.blue(old[i]));
                                    } else
                                        news[i] = Color.rgb(Color.red(old[i]), Color.green(old[i]), Color.blue(old[i]));
                                } else
                                    news[i] = Color.rgb(Color.red(old[i]), Color.green(old[i]), Color.blue(old[i]));
                            } else
                                news[i] = Color.rgb(Color.red(old[i]), Color.green(old[i]), Color.blue(old[i]));
                        }

                        masked = Bitmap.createBitmap(news, bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);

                        int[] layer = new int[imageWidth * imageHeight];
                        masked.getPixels(layer, 0, imageWidth, 0, 0, imageWidth, imageHeight);
                        layers.add(layer);

                        background.setImageBitmap(masked);
                        background.setDrawingCacheEnabled(true);
                        background.buildDrawingCache(true);
                        background.setDrawingCacheEnabled(false);
                    }
                }
            }
            return true;
        });
        btnUndo.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                if (layers.size() - 1 < 1) return true;

                layers.remove(layers.size() - 1);
                background.setImageBitmap(Bitmap.createBitmap(layers.get(layers.size() - 1), imageWidth, imageHeight, Bitmap.Config.ARGB_8888));
                background.setDrawingCacheEnabled(true);
                background.buildDrawingCache(true);
                background.setDrawingCacheEnabled(false);
            }

            return true;
        });
        btnDone.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN
                    || motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                int c = 0;

                for (int i : layers.get(layers.size() - 1)) {
                    if (Color.red(i) == 255 &&
                            Color.green(i) == 0 &&
                            Color.blue(i) == 0
                            ) c++;
                }

                int a = imageHeight * imageWidth;

                float p = c * 100.0f / a;
                p = (Math.round(p * 100.0f) / 100.0f);
                Intent goToChart = new Intent(this, ChartActivity.class);
                goToChart.putExtra("PERCENTAGE", p);
                goToChart.putExtra("WIDTH", imageWidth);
                goToChart.putExtra("HEIGHT", imageHeight);

                layer = layers.get(layers.size() - 1);
                goToChart.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                startActivity(goToChart);
            }

            return true;
        });
    }
}