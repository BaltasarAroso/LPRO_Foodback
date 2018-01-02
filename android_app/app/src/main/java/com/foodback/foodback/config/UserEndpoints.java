package com.foodback.foodback.config;

import com.foodback.foodback.logic.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by FoodBack.
 */

public interface UserEndpoints {

//    GET     /users (com.lpro.fbrest.resources.UsersResource)
//    POST    /users (com.lpro.fbrest.resources.UsersResource)  -check
//    PUT     /users/upgrade (com.lpro.fbrest.resources.UsersResource)
//    GET     /users/{username} (com.lpro.fbrest.resources.UsersResource)


    @POST("/users")
    Call<ResponseBody> createUser(@Body User user);
}