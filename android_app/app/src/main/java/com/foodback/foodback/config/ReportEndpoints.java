package com.foodback.foodback.config;

import com.foodback.foodback.logic.Report;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by FoodBack.
 */

public interface ReportEndpoints {

//    GET     /reports/comments (com.lpro.fbrest.resources.ReportsResource)  -check
//    POST    /reports/comments (com.lpro.fbrest.resources.ReportsResource)  -check
//    GET     /reports/establishments (com.lpro.fbrest.resources.ReportsResource)  -check
//    POST    /reports/establishments (com.lpro.fbrest.resources.ReportsResource)  -check
//    DELETE  /reports/{id} (com.lpro.fbrest.resources.ReportsResource)  -check

    @POST("/reports/comments")
    Call<ResponseBody> newCommentReport(@Body Report report);

    @GET("/reports/comments")
    Call<List<Report>> getAllCommentReports();

    @POST("reports/establishments")
    Call<ResponseBody> newEstablishmentReport(@Body Report report);

    @GET("/reports/establishments")
    Call<List<Report>> getAllEstablishmentReports();

    @DELETE("/reports/{id}")
    Call<ResponseBody> deleteReport(@Path("id") long id);

}
