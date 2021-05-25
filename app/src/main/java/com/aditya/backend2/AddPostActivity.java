package com.aditya.backend2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aditya.backend2.Fragments.BottomPostOptions;
import com.bumptech.glide.Glide;

public class AddPostActivity extends AppCompatActivity implements BottomPostOptions.BottomPostOptionsInterface {

    ImageView button;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        button = findViewById(R.id.popup_framgne);
        imageView = findViewById(R.id.selectedimage);

        button.setOnClickListener(v -> {
            setupDialogElements();
            BottomPostOptions bottomPostOptions = new BottomPostOptions(AddPostActivity.this);
            bottomPostOptions.show(getSupportFragmentManager(), "BOTTOM_OPTIONS_ADDPOST");
        });
    }

    private void setupDialogElements() {
        //Toast.makeText(this, "We are here", Toast.LENGTH_SHORT).show();
        final Dialog dialog = new Dialog(getApplicationContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_bottom_addpost_option);



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