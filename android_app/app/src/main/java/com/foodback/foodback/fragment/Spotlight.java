package com.foodback.foodback.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.foodback.foodback.R;
import com.foodback.foodback.activity.EstablishmentSelectedPage;
import com.foodback.foodback.config.FeaturedEndpoints;
import com.foodback.foodback.config.MealEndpoints;
import com.foodback.foodback.logic.Establishment;
import com.foodback.foodback.logic.Featured;
import com.foodback.foodback.logic.Meal;
import com.foodback.foodback.utils.ErrorMessageAdapter;
import com.foodback.foodback.utils.FeaturedMealsAdapter;
import com.google.gson.Gson;

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
public class Spotlight extends Fragment {

    ArrayList<Meal> spotlights = new ArrayList<>();
    ArrayList<String> errors = new ArrayList<>();

    public Spotlight() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_spotlight, container, false);

        fillFeaturedList(view);

        return view;
    }

    private void fillFeaturedList(final View view) {
        try {
            MealEndpoints services = retrofit.create(MealEndpoints.class);
            Call<List<Meal>> call = services.getAllFeaturedMeals();

            call.enqueue(new Callback<List<Meal>>() {
                @Override
                public void onResponse(Call<List<Meal>> call, Response<List<Meal>> response) {
                    if(response.isSuccessful()) {
                        List<Meal> tmp = response.body();
                        spotlights.addAll(tmp);

                        declareList(view);
                    } else {
                        if(response.code() == 404) {
                            declareError(view);
                        } else {
                            isBad(getActivity(), response);
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Meal>> call, Throwable t) {
                    isFailure(getActivity(), t);
                }
            });

        } catch(Exception e) {
            isException(getActivity(), e);
        }
    }

    private void declareList(View view) {

        ListView listSpotlights = view.findViewById(R.id.list_spotlights);
        listSpotlights.setAdapter(new FeaturedMealsAdapter(getActivity(), spotlights));
        listSpotlights.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Gson gson = new Gson();
                String serialized = gson.toJson(spotlights.get(position));

                Intent i = new Intent(getActivity(), EstablishmentSelectedPage.class);
                i.putExtra("spotlights", serialized);
                startActivity(i);
            }
        });
    }

    private void declareError(View view) {

        errors.add("Nenhum estabelecimento em destaque.");

        ListView listRestaurants = view.findViewById(R.id.list_spotlights);
        listRestaurants.setAdapter(new ErrorMessageAdapter(getActivity(), errors));
    }

}
