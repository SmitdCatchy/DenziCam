package com.catchy.denzicam;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AnalyzeActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Button btnPhoto;
    private ImageView background;
    private TextView textColor;

    private ArrayList<Bitmap> layers = new ArrayList<>();
    private String pictureImagePath;

    private int pickedR;
    private int pickedG;
    private int pickedB;
    private Button btnDone;
    private Button btnMark;
    private Button btnUndo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze);

        background = (ImageView) findViewById(R.id.imageAnalyzeBackground);
        textColor = (TextView) findViewById(R.id.textAnalyzeColor);

        btnPhoto = (Button) findViewById(R.id.buttonAnalyzeTakePhoto);
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
        btnDone = (Button) findViewById(R.id.buttonAnalyzeDone);
        btnMark = (Button) findViewById(R.id.buttonAnalyzeMark);
        btnUndo = (Button) findViewById(R.id.buttonAnalyzeUndo);
//        mainText = (TextView) findViewById(R.id.txtMain);

        btnDone.setVisibility(View.INVISIBLE);
        btnMark.setVisibility(View.INVISIBLE);
        btnUndo.setVisibility(View.INVISIBLE);

    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        pictureImagePath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        Intent chooser = new Intent(Intent.ACTION_CHOOSER);
        chooser.putExtra(Intent.EXTRA_INTENT, galleryIntent);
        chooser.putExtra(Intent.EXTRA_TITLE, "Chhose");


        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                Intent[] intentArray = {cameraIntent};
                chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
                startActivityForResult(chooser, REQUEST_IMAGE_CAPTURE);
//                startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            Bitmap bitmap = null;

            if (data == null || data.getData() == null) {
                bitmap = BitmapFactory.decodeFile(pictureImagePath);

//                ExifInterface ei = null;
//                try {
//                    ei = new ExifInterface(pictureImagePath);
//                    int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
//                            ExifInterface.ORIENTATION_UNDEFINED);
//
//                    switch(orientation) {
//
//                        case ExifInterface.ORIENTATION_ROTATE_90:
//                            bitmap = rotateBitmap(bitmap, 90);
//                            break;
//
//                        case ExifInterface.ORIENTATION_ROTATE_180:
//                            bitmap = rotateBitmap(bitmap, 180);
//                            break;
//
//                        case ExifInterface.ORIENTATION_ROTATE_270:
//                            bitmap = rotateBitmap(bitmap, 270);
//                            break;
//
//                        case ExifInterface.ORIENTATION_NORMAL:
//                        default:
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

            } else {
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            layers.add(bitmap);
            background.setImageBitmap(bitmap);

            initiateMarking();


        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initiateMarking() {
        btnPhoto.setVisibility(View.INVISIBLE);
        btnDone.setVisibility(View.VISIBLE);
        btnMark.setVisibility(View.VISIBLE);
        btnUndo.setVisibility(View.VISIBLE);

        background.setDrawingCacheEnabled(true);
        background.buildDrawingCache(true);
        background.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent){
                if( motionEvent.getAction() == MotionEvent.ACTION_DOWN
                    || motionEvent.getAction() == MotionEvent.ACTION_MOVE
                ){

                    Bitmap bitmap = background.getDrawingCache();
                    int pixel = bitmap.getPixel((int)motionEvent.getX(),(int)motionEvent.getY());

                    pickedR = Color.red(pixel);
                    pickedG = Color.green(pixel);
                    pickedB = Color.blue(pixel);

                    textColor.setBackgroundColor(Color.rgb(pickedR,pickedG,pickedB));
                    String toWrite = "RGB("+pickedR+";"+pickedG+";"+pickedB+")";
                    if(pickedR < 128 && pickedG < 128 && pickedB < 128){
                        textColor.setTextColor(Color.rgb(255,255,255));
                    }
                    else textColor.setTextColor(Color.rgb(0,0,0));
                    textColor.setText(toWrite);

//                    Toast.makeText(AnalyzeActivity.this, toWrite, Toast.LENGTH_SHORT).show();

                }
                return true;
            }
        });
        btnMark.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent){

                textColor.setText("Calculating...");


                Bitmap bitmap = Bitmap.createBitmap(layers.get(layers.size() - 1));
                Bitmap marked = Bitmap.createBitmap( bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
//                marked.eraseColor(Color.rgb(255, 0, 0));

//                if(marked.getWidth() > 1280 || marked.getHeight() > 720){
//
//                    marked = Bitmap.createScaledBitmap(marked, 1280,720, false);
//
//                }

                int threshold = 20;

                int bitmapWidth = bitmap.getWidth();
                int bitmapHeight = bitmap.getHeight();
                int count = 0;
                int pixel = 0;

                for(int i = 0; i < bitmapWidth; i++) {
                    for (int j = 0; j < bitmapHeight; j++) {

                        pixel = bitmap.getPixel(i,j);
                        if( Color.red(pixel)   <= pickedR + threshold ) {
                            if( Color.red(pixel)   >= pickedR - threshold ) {
                                if( Color.green(pixel) <= pickedG + threshold ) {
                                    if( Color.green(pixel) >= pickedG - threshold ) {
                                        if( Color.blue(pixel)  <= pickedB + threshold ) {
                                            if( Color.blue(pixel)  >= pickedB - threshold ) {
                                                marked.setPixel(i, j, Color.rgb(255, 0, 0));
                                                count++;
                                            }else marked.setPixel(i, j, Color.rgb(Color.red(pixel), Color.green(pixel), Color.blue(pixel)));
                                        }else marked.setPixel(i, j, Color.rgb(Color.red(pixel), Color.green(pixel), Color.blue(pixel)));
                                    }else marked.setPixel(i, j, Color.rgb(Color.red(pixel), Color.green(pixel), Color.blue(pixel)));
                                }else marked.setPixel(i, j, Color.rgb(Color.red(pixel), Color.green(pixel), Color.blue(pixel)));
                            }else marked.setPixel(i, j, Color.rgb(Color.red(pixel), Color.green(pixel), Color.blue(pixel)));
                        }else marked.setPixel(i, j, Color.rgb(Color.red(pixel), Color.green(pixel), Color.blue(pixel)));
                    }
                }
//                for(int i = 0; i < 5; i++) {
//                    for (int j = 0; j < 200; j++) {
//                        marked.setPixel(i, j, Color.rgb(255, 0, 0));
//                        count++;
//                    }
//                }


                layers.add(marked);
                background.setImageBitmap(marked);

                double percent = count / ((bitmapWidth*bitmapHeight)/100) ;

                String text = count + "/" + ((bitmapWidth*bitmapHeight)/100) + "=" + percent+"%";

                textColor.setText(text);

                return true;
            }
        });
        btnUndo.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent){

                if(layers.size() - 1 < 1) return true;

                layers.remove(layers.size() - 1);
                background.setImageBitmap(layers.get(layers.size() - 1));

                return true;
            }
        });
    }

    private Bitmap rotateBitmap(Bitmap original, float degrees) {

        Matrix matrix = new Matrix();
        matrix.preRotate(degrees);

        Bitmap rotatedBitmap = Bitmap.createBitmap(original, 0, 0, original.getWidth(), original.getHeight(), matrix, true);

        return rotatedBitmap;
    }
}