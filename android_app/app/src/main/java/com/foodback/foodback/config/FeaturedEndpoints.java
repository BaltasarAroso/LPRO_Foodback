package com.foodback.foodback.config;

import com.foodback.foodback.logic.Featured;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by FoodBack.
 */

public interface FeaturedEndpoints {

//    POST     /featured (com.lpro.fbrest.resources.FeaturedResource)  -check

    @POST("/featured")
    Call<ResponseBody> newFeatured(@Body Featured featured);

}
