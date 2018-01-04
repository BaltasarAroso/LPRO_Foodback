package com.foodback.foodback.utils;

import android.util.Log;

import com.foodback.foodback.config.EstablishmentEndpoints;
import com.foodback.foodback.logic.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.foodback.foodback.config.FoodbackClient.retrofit;

/**
 * Created by FoodBack.
 */

public class CategoryUtils {

    /**
     * Holds temporary error message
     */
    private String errorMessage = "";

    /**
     * For storage of all categories
     */
    private List<Category> categories;

    /**
     * @return temporary error message
     */
    public String popErrorMessage() {
        String tmp = errorMessage;
        errorMessage = "";
        return tmp;
    }

    /**
     * set error message
     * @param errorMessage is the error message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return list of all categories
     */
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * fetches all categories from server
     */
    public void fetchAllCategories(){
        try {
            EstablishmentEndpoints services = retrofit.create(EstablishmentEndpoints.class);
            Call<List<Category>> call = services.getAllCategories();

            call.enqueue(new Callback<List<Category>>() {
                @Override
                public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                    if(response.isSuccessful()) {
                        categories = response.body();
                        if(categories.size() == 0) {
                            setErrorMessage("No establishment categories found.");
                        }
                    } else {
                        APIError apiError = ErrorUtils.parseError(response);
                        setErrorMessage(apiError.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<List<Category>> call, Throwable t) {
                    setErrorMessage("Error getting server response.");
                }
            });
        } catch(Exception e) {
            Log.e("DEBUG", Log.getStackTraceString(e));
            setErrorMessage("The program has encountered an error.");
        }

    }

}
