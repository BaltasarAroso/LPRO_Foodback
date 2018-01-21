package com.foodback.foodback.config;

import com.foodback.foodback.logic.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by FoodBack.
 */

public interface UserEndpoints {

//    GET     /users (com.lpro.fbrest.resources.UsersResource)
//    POST    /users (com.lpro.fbrest.resources.UsersResource)  -check
//    PUT     /users/upgrade (com.lpro.fbrest.resources.UsersResource)
//    GET     /users/{username} (com.lpro.fbrest.resources.UsersResource)
//    GET     /users/me (com.lpro.fbrest.resources.UsersResource)
//    GET     /users/id/{id} (com.lpro.fbrest.resources.UsersResource)


    @POST("/users")
    Call<ResponseBody> createUser(@Body User user);

    @GET("users/me")
    Call<User> getAuthUser();

    @GET("users/id/{id}")
    Call<User> getUserById(@Path("id") long id);
}