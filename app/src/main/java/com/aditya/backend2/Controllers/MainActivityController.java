package com.aditya.backend2.Controllers;

import com.aditya.backend2.models.Connectivity.DummyApi;
import com.aditya.backend2.models.Connectivity.RetrofitClient;
import com.aditya.backend2.models.User.GetUserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.aditya.backend2.Extensions.AppConfig.APP_ID;


public class MainActivityController {

    MainActivityControllerInterface mainActivityControllerInterface;
    DummyApi dummyApi;

    public MainActivityController(MainActivityControllerInterface mainActivityControllerInterface){
        this.mainActivityControllerInterface = mainActivityControllerInterface;
    }

    public void getUsersFromDummyApi(int page, int limit){
        dummyApi = RetrofitClient.getClient().create(DummyApi.class);
        dummyApi.getUsersByPage(APP_ID,page,limit).enqueue(new Callback<GetUserResponse>() {
            @Override
            public void onResponse(Call<GetUserResponse> call, Response<GetUserResponse> response) {
                if (!response.isSuccessful()){
                    mainActivityControllerInterface.onUserGetFailure("Something went wrong!");
                    return;
                }

                GetUserResponse userResponse = response.body();
                mainActivityControllerInterface.onUsersGetSuccess(userResponse);
            }

            @Override
            public void onFailure(Call<GetUserResponse> call, Throwable t) {
                mainActivityControllerInterface.onUserGetFailure(t.getMessage());
            }
        });
    }

    public interface MainActivityControllerInterface {
        public void onUsersGetSuccess(GetUserResponse response);
        public void onUserGetFailure(String e);
    }
}
