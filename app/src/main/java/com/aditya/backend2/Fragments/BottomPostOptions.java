package com.aditya.backend2.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aditya.backend2.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomPostOptions extends BottomSheetDialogFragment {

    BottomPostOptionsInterface bottomPostOptionsInterface;
    public static final int PICK_IMAGE_REQUEST = 1;

    public BottomPostOptions(BottomPostOptionsInterface bottomPostOptionsInterface){
        this.bottomPostOptionsInterface = bottomPostOptionsInterface;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom_addpost_option, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout AddImageLayout = view.findViewById(R.id.add_image_layout);
        LinearLayout PinPostLayout = view.findViewById(R.id.pin_post_layout);
        LinearLayout AddLocationLayout = view.findViewById(R.id.add_location_layout);
        LinearLayout AddLinkLayout = view.findViewById(R.id.add_link_layout);
        LinearLayout AddVideoLayout = view.findViewById(R.id.add_video_layout);

        AddImageLayout.setOnClickListener(v -> {
            //Toast.makeText(getContext(), "Image Add", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent,PICK_IMAGE_REQUEST);
        });

        PinPostLayout.setOnClickListener(v -> {
           // Toast.makeText(getContext(), "Pin the post", Toast.LENGTH_SHORT).show();
            bottomPostOptionsInterface.onPinThePostSelected("Pin this post!");
        });

        AddLocationLayout.setOnClickListener(v -> {
          //  Toast.makeText(getContext(), "Add Location", Toast.LENGTH_SHORT).show();
        });

        AddLinkLayout.setOnClickListener(v -> {
            bottomPostOptionsInterface.onAddLinkSelected("Add Link Selected!");
        });

        AddVideoLayout.setOnClickListener(v -> {
           // Toast.makeText(getContext(), "Add Video", Toast.LENGTH_SHORT).show();
        });
    }

    public interface BottomPostOptionsInterface{
        public void onImageSelected(Uri uri, Boolean isSelected);
        public void onPinThePostSelected(String e);
        public void onAddLinkSelected(String webLink);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && data==null){
            Uri uri = data.getData();
            bottomPostOptionsInterface.onImageSelected(uri,true);
            Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag("BOTTOM_OPTIONS_ADDPOST");
            getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }

    }
}
