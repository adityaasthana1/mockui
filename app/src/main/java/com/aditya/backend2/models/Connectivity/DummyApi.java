package com.aditya.backend2.models.Connectivity;

import com.aditya.backend2.models.Post.PostDataResponse;
import com.aditya.backend2.models.User.GetUserResponse;
import com.aditya.backend2.models.User.UserDataComplete;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface DummyApi {

    @GET("user")
    Call<GetUserResponse> getUsersByPage(
            @Header("app-id") String app_id,
            @Query("page") int page,
            @Query("limit") int limit
    );

    @GET("user/{userid}")
    Call<UserDataComplete> getUserDataComplete(
            @Path("userid") String userid
    );

    @GET("post")
    Call<PostDataResponse> getPosts(
            @Header("app-id") String app_id,
            @Query("limit") int post_limit
    );

    @GET("post")
    Call<PostDataResponse> getPostsbyPageLimit(
            @Header("app-id") String app_id,
            @Query("page") int page,
            @Query("limit") int limit
    );



}
