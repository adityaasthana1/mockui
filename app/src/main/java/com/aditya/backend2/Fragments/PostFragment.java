package com.aditya.backend2.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aditya.backend2.Adapters.PostAdapter;
import com.aditya.backend2.R;
import com.aditya.backend2.models.Post.PostDataResponse;

public class PostFragment extends Fragment {

    private Context context;
    private PostDataResponse postDataResponse;
    private RecyclerView recyclerView;


    public PostFragment(Context context, PostDataResponse postDataResponse){
        this.context = context;
        this.postDataResponse = postDataResponse;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.postrecyclerview);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        PostAdapter postAdapter = new PostAdapter(context, postDataResponse.getData());
        recyclerView.setAdapter(postAdapter);

    }
}
