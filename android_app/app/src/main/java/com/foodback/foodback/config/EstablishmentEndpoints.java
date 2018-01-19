package com.foodback.foodback.config;

import com.foodback.foodback.logic.Category;
import com.foodback.foodback.logic.Establishment;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by FoodBack.
 */

public interface EstablishmentEndpoints {

//    GET     /establishments (com.lpro.fbrest.resources.EstablishmentsResource)  -check
//    POST    /establishments (com.lpro.fbrest.resources.EstablishmentsResource)  -check
//    PUT     /establishments (com.lpro.fbrest.resources.EstablishmentsResource)  -check
//    GET     /establishments/categories (com.lpro.fbrest.resources.EstablishmentsResource)  -check
//    GET     /establishments/{name} (com.lpro.fbrest.resources.EstablishmentsResource)
//    GET     /establishments/tmp (com.lpro.fbrest.resources.EstablishmentsResource)  -check
//    PUT     /establishments/tmp (com.lpro.fbrest.resources.EstablishmentsResource)  -check
//    PUT     /establishments/verify/{tmp_establishment_id} (com.lpro.fbrest.resources.EstablishmentsResource)
//    GET     /establishments/filtered (com.lpro.fbrest.resources.EstablishmentsResource) -check
//    GET     /establishments/mine (com.lpro.fbrest.resources.EstablishmentsResource)  -check


    @POST("/establishments")
    Call<ResponseBody> createEstablishment(@Body Establishment establishment);

    @GET("/establishments/categories")
    Call<List<Category>> getAllCategories();

    //admin only
    @PUT("/establishments")
    Call<ResponseBody> editEstablishment(@Body Establishment establishment);

    @GET("/establishments")
    Call<List<Establishment>> getAllEstablishments();

    @PUT("/establishments/tmp")
    Call<ResponseBody> editTmpEstablishment(@Body Establishment establishment);

    //admin - all tmp establishments
    //establishment - own tmp establishment
    @GET("/establishments/tmp")
    Call<List<Establishment>> getAllTmpEstablishments();

    @GET("/establishments/{name}")
    Call<Establishment> getEstablishment(@Path("name") String name);

    @GET("/establishments/mine")
    Call<Establishment> getMyEstablishment();

    @GET("/establishments/filtered")
    Call<List<Establishment>> getEstablishmentsFiltered(@Query("category_id") long category_id);

}