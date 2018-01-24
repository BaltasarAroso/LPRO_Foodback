package com.foodback.foodback.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foodback.foodback.R;
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
 * A simple {@link Fragment} subclass.
 */
public class UserDelivery extends Fragment {

    ArrayList<Category> categories = new ArrayList<>();
    ArrayList<String> errors = new ArrayList<>();

    public UserDelivery() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_delivery, container, false);

        fillCategoryList(view);

        return view;
    }

    private void fillCategoryList(final View view) {
        try {
            EstablishmentEndpoints services = retrofit.create(EstablishmentEndpoints.class);
            Call<List<Category>> call = services.getAllCategories();

            call.enqueue(new Callback<List<Category>>() {
                @Override
                public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                    if(response.isSuccessful()) {
                        //TODO fill spinner 1 with categories
                    } else {
                        isBad(getActivity(), response);
                    }
                }

                @Override
                public void onFailure(Call<List<Category>> call, Throwable t) {
                    isFailure(getActivity(), t);
                }
            });
        } catch(Exception e) {
            isException(getActivity(), e);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

}
