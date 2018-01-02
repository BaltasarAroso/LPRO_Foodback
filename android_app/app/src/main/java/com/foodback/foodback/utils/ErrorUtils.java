package com.foodback.foodback.utils;

import android.util.Log;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

import static com.foodback.foodback.config.FoodbackClient.retrofit;

/**
 * Created by FoodBack.
 */

public class ErrorUtils {

    public static APIError parseError(Response<?> response) {
        Converter<ResponseBody, APIError> converter;
        converter = retrofit.responseBodyConverter(APIError.class, new Annotation[0]);

        APIError error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            Log.e("DEBUG",Log.getStackTraceString(e)); //DEBUG
            return new APIError();
        }

        return error;
    }
}