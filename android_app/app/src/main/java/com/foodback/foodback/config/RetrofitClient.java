package com.foodback.foodback.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;

/**
 * Created by Foodback.
 */

public class RetrofitClient {

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
        @Override
        public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
            Request originalRequest = chain.request();

            Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                    Credentials.basic("daniel", "123123123"));

            Request newRequest = builder.build();
            return chain.proceed(newRequest);
        }
    }).build();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://172.30.1.92:8080")
            .client(okHttpClient)
            .build();

}