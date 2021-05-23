package com.aditya.backend2.Controllers;

import com.aditya.backend2.models.Comment.CommentResponse;
import com.aditya.backend2.models.Connectivity.DummyApi;
import com.aditya.backend2.models.Connectivity.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.aditya.backend2.Extensions.AppConfig.APP_ID;

public class PostComentController {

    DummyApi api;
    PostCommentControllerInterface postCommentControllerInterface;

    public PostComentController(PostCommentControllerInterface postCommentControllerInterface){
        this.postCommentControllerInterface = postCommentControllerInterface;
    }

    public void getPostComments(String post_id){
        api = RetrofitClient.getClient().create(DummyApi.class);
        api.getCommentsOfPost(APP_ID,post_id).enqueue(new Callback<CommentResponse>() {
            @Override
            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                if (!response.isSuccessful()){
                    String status = response.message() + " " + response.code();
                    postCommentControllerInterface.onCommentsFetchFailed(status);
                    return;
                }
                postCommentControllerInterface.onCommentsFetchedSuccessfully(response.body());

            }

            @Override
            public void onFailure(Call<CommentResponse> call, Throwable t) {
                postCommentControllerInterface.onCommentsFetchFailed(t.getMessage());
            }
        });
    }

    public interface PostCommentControllerInterface{
        public void onCommentsFetchedSuccessfully(CommentResponse commentResponse);
        public void onCommentsFetchFailed(String e);
    }
}
