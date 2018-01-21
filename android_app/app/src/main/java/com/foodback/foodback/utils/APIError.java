package com.foodback.foodback.utils;

/**
 * Created by Foodback.
 */

public class APIError {

    /**
     * response code
     */
    private int statusCode;

    /**
     * endpoint used in http request
     */
    private String endpoint;

    /**
     * default message
     */
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