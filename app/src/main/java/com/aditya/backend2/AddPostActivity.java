package com.aditya.backend2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.aditya.backend2.Fragments.BottomPostOptions;
import com.bumptech.glide.Glide;

public class AddPostActivity extends AppCompatActivity implements BottomPostOptions.BottomPostOptionsInterface {
    private static final int PICK_IMAGE_ACTION = 0;
    private static final int PICK_VIDEO_ACTION = 1;

    ImageView button, imageView, selectImage, selectLocation, selectVideo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        initView();
        onClickListeners();


    }

    private void onClickListeners() {
        selectImage.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, PICK_IMAGE_ACTION);
        });

        selectVideo.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("video/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, PICK_VIDEO_ACTION);
        });

        button.setOnClickListener(v -> {
            BottomPostOptions bottomPostOptions = new BottomPostOptions(AddPostActivity.this);
            bottomPostOptions.show(getSupportFragmentManager(), "BOTTOM_OPTIONS_ADDPOST");
        });
    }

    private void initView() {
        button = findViewById(R.id.popup_framgne);
        imageView = findViewById(R.id.selectedimage);
        selectImage = findViewById(R.id.select_image_addpost);
        selectLocation = findViewById(R.id.select_location_addpost);
        selectVideo = findViewById(R.id.select_video_addpost);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_ACTION && null != data){
            //Add Image Uri
            Uri uri = data.getData();
            Glide.with(getApplicationContext()).load(uri).into(imageView);
            imageView.setVisibility(View.VISIBLE);

        }else if (requestCode == PICK_VIDEO_ACTION && null != data){
            Uri uri = data.getData();

        }
    }

    @Override
    public void onImageSelected(Uri uri, Boolean isSelected) {
        imageView.setVisibility(View.VISIBLE);
        Glide.with(getApplicationContext()).load(uri).into(imageView);
    }

    @Override
    public void onPinThePostSelected(String e) {
        Toast.makeText(this, e, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddLinkSelected(String webLink) {
        Toast.makeText(this, webLink, Toast.LENGTH_SHORT).show();
    }
}