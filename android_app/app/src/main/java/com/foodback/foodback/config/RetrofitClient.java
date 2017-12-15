package com.foodback.foodback.config;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Foodback.
 */

public class RetrofitClient {

    public Retrofit retrofit;

    protected OkHttpClient okHttpClient;

    protected String username;
    protected String password;

    public void startup() {
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
                .baseUrl(FoodbackInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public void setCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

}