package com.foodback.foodback.config;

import com.foodback.foodback.logic.Meal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by FoodBack.
 */

public interface MealEndpoints {

//    GET     /meals/{establishment_id} (com.lpro.fbrest.resources.MealsResource)  -check
//    GET     /meals/featured (com.lpro.fbrest.resources.MealsResource)  -check

    @GET("/meals/{establishment_id}")
    Call<List<Meal>>  getAllMeals(@Path("establishment_id") long establishment_id);

    @GET("/meals/featured")
    Call<List<Meal>> getAllFeaturedMeals();

}
