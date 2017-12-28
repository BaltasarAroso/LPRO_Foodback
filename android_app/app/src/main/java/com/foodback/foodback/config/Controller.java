package com.foodback.foodback.config;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Foodback.
 */

public class Controller implements Callback<ResponseBody> {

    public void start() {
        RetrofitClient retrofit = new RetrofitClient();
        FoodbackInterface services = retrofit.retrofit.create(FoodbackInterface.class);
        Call<ResponseBody> call = services.verifyUserCredentials();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
        if(response.isSuccessful()) {
            //alterar texto
            System.out.println("\nsuccess\n");
        } else {
            System.out.println(response.errorBody());
            System.out.println("\nfailure\n");
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        t.printStackTrace();
    }

}