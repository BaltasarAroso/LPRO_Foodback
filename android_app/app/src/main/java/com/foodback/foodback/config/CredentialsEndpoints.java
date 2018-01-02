package com.foodback.foodback.config;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by FoodBack.
 */

public interface CredentialsEndpoints {

//    GET     /credentials (com.lpro.fbrest.resources.CredentialsResource)  -check
//    GET     /credentials/establishment (com.lpro.fbrest.resources.CredentialsResource)
//    GET     /credentials/user (com.lpro.fbrest.resources.CredentialsResource)  -check


//    @Headers("Content-Type: application/json")
    /**
     * @return Status Code, meaning there is a connection
     */
    @GET("/credentials")
    Call<ResponseBody> checkConnection();

    @GET("/credentials/user")
    Call<ResponseBody> verifyUserCredentials();

}