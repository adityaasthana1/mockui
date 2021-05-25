package com.aditya.backend2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aditya.backend2.Adapters.EndlessPostAdapter;
import com.aditya.backend2.Adapters.EndlessRecyclerViewScrollListener;
import com.aditya.backend2.Adapters.MainAdapter;
import com.aditya.backend2.Adapters.PostAdapter;
import com.aditya.backend2.Controllers.MainActivityController;
import com.aditya.backend2.Controllers.UserPostsController;
import com.aditya.backend2.models.Post.PostData;
import com.aditya.backend2.models.Post.PostDataResponse;
import com.aditya.backend2.models.User.GetUserResponse;
import com.aditya.backend2.models.User.UserData;
import com.aghajari.zoomhelper.ZoomHelper;
import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener;
import com.stfalcon.imageviewer.StfalconImageViewer;
import com.stfalcon.imageviewer.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import static com.aditya.backend2.Extensions.AppConfig.PAGE_LIMIT_MAIN;
import static com.aditya.backend2.Extensions.AppConfig.POST_LIMIT;


public class MainActivity extends AppCompatActivity implements MainActivityController.MainActivityControllerInterface, UserPostsController.UserPostsInterface{

    private RecyclerView recyclerView, postRecycler;
    private Button prev,next;
    private TextView pageno;
    private MainActivityController mainActivityController;
    private Integer pageCount = 0;
    //ProgressDialog builder;
    private NestedScrollView nestedScrollView;
    LinearLayout linearLayout;
    UserPostsController userPostsController;
    ShimmerFrameLayout usershimmer, postshimmer;
    EndlessRecyclerViewScrollListener UserScrollListener, PostScrollListener;
    Boolean isLoading = false;
    List<PostData> postData;
    Parcelable state;
    int page = 1;

    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        onClickListenrs();
        /*
        builder = new ProgressDialog(MainActivity.this);
        builder.setTitle("Please Wait.");
        builder.setMessage("We are fetching data from our servers!");
        builder.show();

         */

        floatingActionButton.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), AddPostActivity.class));
        });

        mainActivityController = new MainActivityController(MainActivity.this);
        mainActivityController.getUsersFromDummyApi(pageCount,PAGE_LIMIT_MAIN);
        userPostsController = new UserPostsController(MainActivity.this);
        userPostsController.getPostsByPageLimit(page, POST_LIMIT);


    }

    private void onClickListenrs() {
        prev.setOnClickListener(v -> {
            pageCount--;
            updateView();
        });

        next.setOnClickListener(v -> {
            pageCount++;
            updateView();
        });


    }

    private void updateView() {
        //builder.show();
        mainActivityController.getUsersFromDummyApi(pageCount,PAGE_LIMIT_MAIN);
        nestedScrollView.fullScroll(NestedScrollView.FOCUS_UP);
    }

    private void initView() {
        recyclerView = findViewById(R.id.recycler_view);
        postRecycler = findViewById(R.id.postrecyclerview);
        prev = findViewById(R.id.backpage);
        next = findViewById(R.id.nextpage);
        pageno = findViewById(R.id.pageno);
        nestedScrollView = findViewById(R.id.nestedscrollview);
        usershimmer = findViewById(R.id.shimmer_users);
        postshimmer = findViewById(R.id.postshimmer);
        usershimmer.startShimmer();
        postshimmer.startShimmer();
        floatingActionButton = findViewById(R.id.main_floating_actionbutton);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onUsersGetSuccess(GetUserResponse response) {
        List<UserData> userDataList = response.getData();
        if (pageCount==0){
            prev.setVisibility(View.INVISIBLE);
            next.setVisibility(View.VISIBLE);
        }else if(pageCount == response.getTotal()){
            next.setVisibility(View.INVISIBLE);
            prev.setVisibility(View.VISIBLE);
        }else{
            next.setVisibility(View.VISIBLE);
            prev.setVisibility(View.VISIBLE);
        }

        MainAdapter adapter = new MainAdapter(MainActivity.this, userDataList);
        pageno.setText(Integer.toString(response.getPage()));
        recyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        state = linearLayoutManager.onSaveInstanceState();
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        usershimmer.stopShimmer();
        usershimmer.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);

        //builder.dismiss();

    }

    @Override
    public void onUserGetFailure(String e) {
        Toast.makeText(this, e, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPostsFetched(PostDataResponse postDataResponse) {       
        //Create or Update PostData for RecyclerView.
        if (postData == null){
            postData = postDataResponse.getData();
            if (postDataResponse.getPage()!=postDataResponse.getTotal())
                postData.add(null);
        }else{
            postData.addAll(postData.size()-1, postDataResponse.getData());
        }
        
        //Setup RecyclerView
        postRecycler.setNestedScrollingEnabled(false);
        postRecycler.setHasFixedSize(true);
        LinearLayoutManager VlinearLayoutManager = new LinearLayoutManager(getApplicationContext());
        postRecycler.setLayoutManager(VlinearLayoutManager);
        EndlessPostAdapter postAdapter = new EndlessPostAdapter(MainActivity.this, postData);
        postRecycler.setAdapter(postAdapter);


        //ManageShimmers
        postshimmer.stopShimmer();
        postshimmer.setVisibility(View.GONE);
        postRecycler.setVisibility(View.VISIBLE);

    }

    @Override
    public void onPostFetchFailed(String e) {
        Toast.makeText(this, e, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return ZoomHelper.Companion.getInstance().dispatchTouchEvent(ev,this) || super.dispatchTouchEvent(ev);
    }


}