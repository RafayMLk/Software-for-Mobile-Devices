package com.example.saboorhussain.project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UploadImage extends AppCompatActivity {

    private int Gallery_intent = 2;
    de.hdodenhof.circleimageview.CircleImageView ProfileImage;
    StorageReference imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_photo);

          ProfileImage = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.Profile_Image);

    }




}
