package com.aditya.backend2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.aditya.backend2.Controllers.UserPostsController;
import com.aditya.backend2.Fragments.LoadingFragment;
import com.aditya.backend2.Fragments.PostFragment;
import com.aditya.backend2.models.Post.PostDataResponse;

public class UserPostActivity extends AppCompatActivity implements UserPostsController.UserPostsInterface {

    FrameLayout frameLayout;
    UserPostsController userPostsController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_post);

        frameLayout = findViewById(R.id.postlayout);
        userPostsController = new UserPostsController(UserPostActivity.this);
        getSupportFragmentManager().beginTransaction().replace(R.id.postlayout, new LoadingFragment()).commitAllowingStateLoss();
        userPostsController.GetPosts();

    }

    @Override
    public void onPostsFetched(PostDataResponse postDataResponse) {
        getSupportFragmentManager().beginTransaction().replace(R.id.postlayout, new PostFragment(UserPostActivity.this, postDataResponse)).commitAllowingStateLoss();
    }

    @Override
    public void onPostFetchFailed(String e) {
        Toast.makeText(this, e, Toast.LENGTH_SHORT).show();
    }
}