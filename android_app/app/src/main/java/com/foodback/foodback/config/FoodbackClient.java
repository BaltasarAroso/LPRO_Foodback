package com.foodback.foodback.config;

import android.support.annotation.NonNull;

import com.foodback.foodback.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Foodback.
 */

public class FoodbackClient {

//    private static final String BASE_URL = "http://10.0.2.2:3000";
//    private static final String BASE_URL = "http://192.168.1.10:3000";
    private static final String BASE_URL = "http://172.30.6.175:3000";

    public static Retrofit retrofit =  new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static String username = "";
    private static String password = "";

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static void setCredentials(String user, String pw) {
        if(user != null) username = user;
        if(pw != null) password = pw;
        new FoodbackClient();
    }

    private FoodbackClient() {
        this.addAuth();
    }

    private void addAuth() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

        okHttpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                        Credentials.basic(username, password));

                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        });

        if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient.addInterceptor(logging);
        }

        Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient.build())
                .build();
    }

}