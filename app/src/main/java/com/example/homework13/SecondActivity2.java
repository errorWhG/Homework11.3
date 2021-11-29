package com.example.homework13;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.service.media.MediaBrowserService;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class SecondActivity2<Uri> extends AppCompatActivity {

    private static final int CAMERA = 000;
    private final int GALLERY = 1;
    private static final int PICK_IMAGE = 010;
    private TextView textView;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);
        login();
        galleryClick();
        imageClick();

    }

    private void imageClick() {
        textView.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,CAMERA);
        });
    }

    private void galleryClick() {
        imageView.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, GALLERY);

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY && resultCode == RESULT_OK && data != null){
            Glide.with(this).load(data.getData().toString()).circleCrop().into(imageView);
        }
        if (requestCode == CAMERA && resultCode == RESULT_OK && data != null){
            Bundle bundle = data.getExtras();
            Bitmap imageBitmap = (Bitmap) bundle.get("data");
            imageView.setImageBitmap(imageBitmap);
            Glide.with(imageView).load(imageBitmap).circleCrop().into(imageView);
        }
    }

    private void login() {
        imageView = findViewById(R.id.profile_image);
        textView = findViewById(R.id.edit_photo);
    }
}