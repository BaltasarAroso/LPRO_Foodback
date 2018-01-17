package com.foodback.foodback.config;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PUT;

/**
 * Created by FoodBack.
 */

public interface CredentialsEndpoints {

//    GET     /credentials (com.lpro.fbrest.resources.CredentialsResource)  -check
//    GET     /credentials/establishment (com.lpro.fbrest.resources.CredentialsResource)  -check
//    GET     /credentials/user (com.lpro.fbrest.resources.CredentialsResource)  -check
//    GET     /credentials/admin (com.lpro.fbrest.resources.CredentialsResource)
//    PUT     /credentials (com.lpro.fbrest.resources.CredentialsResource)

    /**
     * @return Status Code, meaning there is a connection
     */
    @GET("/credentials")
    Call<ResponseBody> checkConnection();

    @GET("/credentials/user")
    Call<ResponseBody> verifyUserCredentials();

    @GET("/credentials/establishment")
    Call<ResponseBody> verifyEstablishmentCredentials();

    @FormUrlEncoded
    @PUT("/credentials")
    Call<ResponseBody> updateCredentials(
            @Field("username") String username,
            @Field("password") String password
    );

}