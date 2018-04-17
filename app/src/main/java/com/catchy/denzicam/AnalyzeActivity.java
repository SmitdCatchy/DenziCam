package com.catchy.denzicam;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AnalyzeActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Button btnPhoto;
    private ImageView background;

    private Uri imageURI;
    private Bitmap original;
    private Bitmap[] layers;
    private String pictureImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze);

        background = (ImageView) findViewById(R.id.imageAnalyzeBackground);

        btnPhoto = (Button) findViewById(R.id.buttonAnalyzeTakePhoto);
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

    }

    String mCurrentPhotoPath;

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
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
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
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath);
            background.setImageBitmap(bitmap);
        }
    }

    /*private void takePictureIntent() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Intent chooser = new Intent(Intent.ACTION_CHOOSER);
        chooser.putExtra(Intent.EXTRA_INTENT, galleryIntent);
        chooser.putExtra(Intent.EXTRA_TITLE, "Chhose");
        Intent[] intentArray = {cameraIntent};
        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
        startActivityForResult(chooser, REQUEST_IMAGE_CAPTURE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("Analyze","onActivityResult");
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (data.getData() != null) {
                // this case will occur in case of picking image from the Gallery
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                    setpicture(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // this case will occur when taking a picture with a camera
                Bitmap bitmap = null;

                Cursor cursor = this.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[]{MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_ADDED,
                                MediaStore.Images.ImageColumns.ORIENTATION}, MediaStore.Images.Media.DATE_ADDED,
                        null, "date_added DESC");
                if (cursor != null && cursor.moveToFirst()) {
                    Uri uri = Uri.parse(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)));
                    String photoPath = uri.toString();
                    cursor.close();
                    if (photoPath != null) {
                        bitmap = BitmapFactory.decodeFile(photoPath);
                    }
                }

                if (bitmap == null) {
                    bitmap = (Bitmap) data.getExtras().get("data");
                }
                setpicture(bitmap);
            }
        }
    }*/

    private void setpicture(Bitmap bitmap){
//        Toast.makeText(this, "height: " + bitmap.getHeight() + "px",Toast.LENGTH_SHORT).show();
        background.setImageBitmap(bitmap);
    }
}
