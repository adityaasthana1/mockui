package com.aditya.backend2.Controllers;

import com.aditya.backend2.models.Connectivity.DummyApi;
import com.aditya.backend2.models.Connectivity.RetrofitClient;
import com.aditya.backend2.models.Post.PostDataResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.aditya.backend2.Extensions.AppConfig.APP_ID;
import static com.aditya.backend2.Extensions.AppConfig.POST_LIMIT;

public class UserPostsController {

    DummyApi api;
    UserPostsInterface userPostsInterface;

    public UserPostsController(UserPostsInterface userPostsInterface){
        this.userPostsInterface = userPostsInterface;
    }

    public void GetPosts(){
        api = RetrofitClient.getClient().create(DummyApi.class);
        api.getPosts(APP_ID, POST_LIMIT).enqueue(new Callback<PostDataResponse>() {
            @Override
            public void onResponse(Call<PostDataResponse> call, Response<PostDataResponse> response) {
                if (!response.isSuccessful()){
                    userPostsInterface.onPostFetchFailed("Something went wrong!");
                    return;
                }

                PostDataResponse postDataResponse = response.body();
                userPostsInterface.onPostsFetched(postDataResponse);
            }

            @Override
            public void onFailure(Call<PostDataResponse> call, Throwable t) {
                userPostsInterface.onPostFetchFailed(t.getMessage());
            }
        });

    }

    public void getPostsByPageLimit(int page, int limit){
        api = RetrofitClient.getClient().create(DummyApi.class);
        api.getPostsbyPageLimit(APP_ID, page, limit).enqueue(new Callback<PostDataResponse>() {
            @Override
            public void onResponse(Call<PostDataResponse> call, Response<PostDataResponse> response) {
                if (!response.isSuccessful()){
                    userPostsInterface.onPostFetchFailed("Something went wrong!");
                    return;
                }
                PostDataResponse postDataResponse = response.body();
                userPostsInterface.onPostsFetched(postDataResponse);
            }

            @Override
            public void onFailure(Call<PostDataResponse> call, Throwable t) {
                userPostsInterface.onPostFetchFailed(t.getMessage());
            }
        });
    }

    public interface UserPostsInterface{
        void onPostsFetched(PostDataResponse postDataResponse);
        void onPostFetchFailed(String e);
    }
}
