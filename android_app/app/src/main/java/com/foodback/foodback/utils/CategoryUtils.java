package com.foodback.foodback.utils;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.foodback.foodback.activity.EstablishmentRegister;
import com.foodback.foodback.config.EstablishmentEndpoints;
import com.foodback.foodback.logic.Category;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.foodback.foodback.config.FoodbackClient.retrofit;
import static com.foodback.foodback.utils.ErrorDisplay.isBad;
import static com.foodback.foodback.utils.ErrorDisplay.isException;
import static com.foodback.foodback.utils.ErrorDisplay.isFailure;

/**
 * Created by FoodBack.
 */

public class CategoryUtils {

    /**
     * stores categories fetched from server
     */
    private List<Category> categoryList;

    /**
     * @return list of all categories
     */
    public List<Category> getCategories() {
        return categoryList;
    }

    /**
     * @param name
     * @return corresponding category ID
     */
    public int nameToID(String name) {
        for(Category x: categoryList) {
            if(name.equals(x.getName())) {
                return x.getId();
            }
        }

        return 1;
    }

    /**
     * @param context
     * @param spinner
     */
    public void populateSpinner(final Context context, final Spinner spinner, final Integer id){
        try {
            EstablishmentEndpoints services = retrofit.create(EstablishmentEndpoints.class);
            Call<List<Category>> call = services.getAllCategories();

            call.enqueue(new Callback<List<Category>>() {
                @Override
                public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                    if(response.isSuccessful()) {
                        categoryList = response.body();
                        if(categoryList.size() == 0) {
                            Toast.makeText(context,
                                    "No establishment categories found.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            List<String> spinnerArray = new ArrayList<>();

                            for(Category x: categoryList) {
                                spinnerArray.add(x.getName());
                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                                    context,
                                    android.R.layout.simple_spinner_item,
                                    spinnerArray);

                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner.setAdapter(adapter);
                            if(id != null) spinner.setSelection(id-1, true);
                        }
                    } else {
                        isBad(context, response);
                    }
                }

                @Override
                public void onFailure(Call<List<Category>> call, Throwable t) {
                    isFailure(context, t);
                }
            });
        } catch(Exception e) {
            isException(context, e);
        }
    }

}
