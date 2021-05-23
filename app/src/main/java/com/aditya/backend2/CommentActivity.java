package com.aditya.backend2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aditya.backend2.Adapters.CommentAdapter;
import com.aditya.backend2.Controllers.PostComentController;
import com.aditya.backend2.models.Comment.CommentData;
import com.aditya.backend2.models.Comment.CommentResponse;
import com.aditya.backend2.models.Post.Owner;
import com.aditya.backend2.models.Post.PostData;
import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.Date;
import java.util.List;

import static com.aditya.backend2.Extensions.AppConfig.POST_ID_FOR_COMMENT;

public class CommentActivity extends AppCompatActivity implements PostComentController.PostCommentControllerInterface {

    ImageView UserImage;
    TextView UserName, UserPostText, PostDate, PostLikes, PostButton;
    ShimmerFrameLayout shimmerFrameLayout;
    RecyclerView recyclerView;
    EditText PostComment;
    PostComentController postComentController;
    List<CommentData> commentData;
    CommentAdapter commentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        initView();
        onClickListeners();

        postComentController = new PostComentController(CommentActivity.this);
        postComentController.getPostComments(POST_ID_FOR_COMMENT);

        try {
            //String post_id = getIntent().getStringExtra("POST_ID");
            PostData postData = getIntent().getParcelableExtra("POST_DATA");
            Toast.makeText(this, postData.getText(), Toast.LENGTH_SHORT).show();
            if (postData == null){
                Toast.makeText(this, "NULL DATA", Toast.LENGTH_SHORT).show();
            }
            //Glide.with(getApplicationContext()).load(postData.getOwner().getPicture()).into(UserImage);
            UserPostText.setText(postData.getText());
            String post_likes = postData.getLikes() + " likes";
            PostLikes.setText(post_likes);
            PostDate.setText(postData.getPublishDate().toString());
            String fullname = postData.getOwner().getFirstName() + " " + postData.getOwner().getLastName();
            UserName.setText(fullname);
        }catch (Exception e){
            Log.d("ERROR_POST_DATA" , e.getMessage());
            //Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }




    }

    private void onClickListeners() {
        PostButton.setOnClickListener(v -> {
            if (PostComment.getText().toString().isEmpty()){
                return;
            }

            String comment_text = PostComment.getText().toString().trim();
            Owner owner = new Owner("1", "asthana.aditya1@gmail.com", "Mr" , "", "Aditya", "asthana");
            CommentData comment = new CommentData(owner, "2", comment_text, new Date());
            commentData.add( comment);
            commentAdapter.notifyDataSetChanged();
            PostComment.setText("");

            recyclerView.smoothScrollToPosition(commentData.size()-2);

        });
    }

    private void initView() {
        UserImage = findViewById(R.id.comment_post_image);
        UserName = findViewById(R.id.comment_post_user_name);
        UserPostText = findViewById(R.id.comment_post_description);
        PostDate = findViewById(R.id.comment_post_date);
        PostLikes = findViewById(R.id.comment_post_likes);
        PostButton = findViewById(R.id.comment_post_button);
        shimmerFrameLayout = findViewById(R.id.comment_shimmer_layout);
        recyclerView = findViewById(R.id.comments_recyclerview);
        PostComment = findViewById(R.id.write_comment);
        shimmerFrameLayout.startShimmer();
    }

    @Override
    public void onCommentsFetchedSuccessfully(CommentResponse commentResponse) {
        //Toast.makeText(this, "response is here", Toast.LENGTH_SHORT).show();
        commentData = commentResponse.getData();

        commentAdapter = new CommentAdapter(CommentActivity.this, commentData);
        recyclerView.setVisibility(View.VISIBLE);
        shimmerFrameLayout.stopShimmer();
        shimmerFrameLayout.setVisibility(View.GONE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CommentActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(commentAdapter);
        recyclerView.setVisibility(View.VISIBLE);
        shimmerFrameLayout.setVisibility(View.GONE);

    }

    @Override
    public void onCommentsFetchFailed(String e) {
        Toast.makeText(this, e, Toast.LENGTH_SHORT).show();
        Log.d("ERROR_RESPONSE", e);
    }

    private void closeKeyboard() {
        // this will give us the view
        // which is currently focus
        // in this layout
        View view = this.getCurrentFocus();

        // if nothing is currently
        // focus then this will protect
        // the app from crash
        if (view != null) {

            // now assign the system
            // service to InputMethodManager
            InputMethodManager manager
                    = (InputMethodManager)
                    getSystemService(
                            Context.INPUT_METHOD_SERVICE);
            manager
                    .hideSoftInputFromWindow(
                            view.getWindowToken(), 0);
        }
    }
}