package com.foodback.foodback.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.foodback.foodback.R;
import com.foodback.foodback.config.FeaturedEndpoints;
import com.foodback.foodback.logic.Featured;
import com.foodback.foodback.logic.Meal;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Locale;

import okhttp3.ResponseBody;
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

public class EstablishmentMenuAdapter extends ArrayAdapter {

    private Context context;
    private LayoutInflater inflater;

    private ArrayList<Meal> meals;

    private FeaturedEndpoints featuredEndpoints;

    public EstablishmentMenuAdapter(Context context, ArrayList<Meal> meals) {
        super(context, R.layout.layout_estabpage_menu, meals);

        this.context = context;
        this.meals = meals;

        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        if(null == convertView) {
            convertView = inflater.inflate(R.layout.layout_estabpage_menu, parent, false);
        }

        TextView menuTitle = convertView.findViewById(R.id.menu_title);
        TextView menuPrice = convertView.findViewById(R.id.menu_price);
        final Spinner spotlightSpinner = convertView.findViewById(R.id.spotlight_spinner);
        final Button btnSpotlight = convertView.findViewById(R.id.btnSpotlight);

        menuTitle.setText(meals.get(position).getMeal());
        menuPrice.setText(String.format(Locale.UK, "%1.0f€", meals.get(position).getPrice()));

        btnSpotlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long tsLong = System.currentTimeMillis();
                Timestamp ts = new Timestamp(tsLong);
                Featured featured = new Featured(
                        meals.get(position).getId(),
                        ts,
                        spotlightSpinner.getSelectedItemPosition()+1);

                createNewFeatured(featured, btnSpotlight);

                btnSpotlight.setVisibility(View.INVISIBLE);
            }
        });

        return convertView;
    }

    private void createNewFeatured(Featured featured, final Button btnSpotlight) {

        try {
            featuredEndpoints = retrofit.create(FeaturedEndpoints.class);
            Call<ResponseBody> call = featuredEndpoints.newFeatured(featured);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(context, "Success.", Toast.LENGTH_SHORT).show();
                    } else {
                        isBad(context, response);
                        btnSpotlight.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    isFailure(context, t);
                    btnSpotlight.setVisibility(View.VISIBLE);
                }
            });

        } catch(Exception e) {
            isException(context, e);
            btnSpotlight.setVisibility(View.VISIBLE);
        }

    }

}
