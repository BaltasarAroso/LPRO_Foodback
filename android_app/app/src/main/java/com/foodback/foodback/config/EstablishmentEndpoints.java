package com.foodback.foodback.config;

import com.foodback.foodback.logic.Establishment;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by FoodBack.
 */

public interface EstablishmentEndpoints {

//    GET     /establishments (com.lpro.fbrest.resources.EstablishmentsResource)
//    POST    /establishments (com.lpro.fbrest.resources.EstablishmentsResource)  -check
//    PUT     /establishments (com.lpro.fbrest.resources.EstablishmentsResource)
//    GET     /establishments/{name} (com.lpro.fbrest.resources.EstablishmentsResource)


    @POST("/establishments")
    Call<ResponseBody> createEstablishment(@Body Establishment establishment);
}