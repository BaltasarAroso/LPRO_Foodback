package com.foodback.foodback.utils;

/**
 * Created by Foodback.
 */

public class APIError {

    private int statusCode;
    private String endpoint;
    private String message = "Unknown Error.";

    public int getStatusCode() {
        return statusCode;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getMessage() {
        return message;
    }
}