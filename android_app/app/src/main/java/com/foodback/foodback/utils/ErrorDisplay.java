package com.foodback.foodback.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.foodback.foodback.R;

import retrofit2.Response;

/**
 * Created by Foodback.
 */

public class ErrorDisplay {

    public static void isBad(Context context, Response<?> response) {
        APIError apiError = ErrorUtils.parseError(response);
        Toast.makeText(context, apiError.getMessage(), Toast.LENGTH_SHORT).show();
    }

    public static void isFailure(Context context, Throwable t) {
        Log.e("DEBUG",Log.getStackTraceString(t));
        Toast.makeText(context, R.string.error_server_response, Toast.LENGTH_SHORT).show();
    }

    public static void isException(Context context, Exception e) {
        Log.e("DEBUG",Log.getStackTraceString(e));
        Toast.makeText(context, R.string.error_unexpected, Toast.LENGTH_SHORT).show();
    }

}
