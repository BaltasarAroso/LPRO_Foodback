package com.foodback.foodback.config;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by FoodBack.
 */

public interface ImageEndpoints {

//    POST    /images/establishment (com.lpro.fbrest.resources.ImagesResource)
//    POST    /images/establishment/profile (com.lpro.fbrest.resources.ImagesResource)
//    GET     /images/establishment/profile/{establishment_id} (com.lpro.fbrest.resources.ImagesResource)
//    GET     /images/establishment/{establishment_id} (com.lpro.fbrest.resources.ImagesResource)  -check
//    DELETE  /images/{imageName} (com.lpro.fbrest.resources.ImagesResource)  -check
//    GET     /images/{imageName} (com.lpro.fbrest.resources.ImagesResource)  -check

    @DELETE("/images/{imageName}")
    Call<ResponseBody> deleteImage(@Path("imageName") String imageName);

    @GET("/images/{imageName}")
    Call<ResponseBody> getImage(@Path("imageName") String imageName);

    @GET("/images/establishment/{establishment_id}")
    Call<List<String>> getEstablishmentImageNames(@Path("establishment_id") long establishment_id);

}
