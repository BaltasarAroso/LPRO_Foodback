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

    protected OkHttpClient okHttpClient;
    protected Retrofit retrofit;
    Gson gson;

    protected String username;
    protected String password;

    public void startup() {
        gson = new GsonBuilder()
                .setLenient()
                .create();

        okHttpClient= new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                        Credentials.basic(username, password));

                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        }).build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://172.30.1.92:8080")
                .client(okHttpClient)
                .build();
    }

    public void setCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Retrofit getRetrofit() {
        return this.retrofit;
    }

}