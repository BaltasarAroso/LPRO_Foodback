package com.foodback.foodback.config;

import com.foodback.foodback.logic.Order;
import com.foodback.foodback.logic.OrderMeal;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by FoodBack.
 */

public interface OrderEndpoints {

//    GET     /orders (com.lpro.fbrest.resources.OrdersResource)  -check
//    POST    /orders (com.lpro.fbrest.resources.OrdersResource)  -check
//    GET     /orders/establishment (com.lpro.fbrest.resources.OrdersResource)  -check
//    PUT     /orders/establishment/{orders_meal_id} (com.lpro.fbrest.resources.OrdersResource)  -check
//    GET     /orders/{order_id} (com.lpro.fbrest.resources.OrdersResource)

    @GET("/orders/establishment")
    Call<List<OrderMeal>> getUnpreparedOrders();

    @PUT("/orders/establishment/{orders_meal_id}")
    Call<ResponseBody> makeOrderMealReady(@Path("orders_meal_id") long orders_meal_id);

    @GET("/orders")
    Call<List<Order>> getOrdersMadeByUser();

    @POST("/orders")
    Call<ResponseBody> newOrder(@Body Order order);

}
