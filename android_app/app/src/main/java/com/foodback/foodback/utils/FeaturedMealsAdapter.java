package com.foodback.foodback.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.foodback.foodback.R;
import com.foodback.foodback.config.EstablishmentEndpoints;
import com.foodback.foodback.logic.Establishment;
import com.foodback.foodback.logic.Meal;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import static com.foodback.foodback.config.FoodbackClient.getBaseUrl;
import static com.foodback.foodback.config.FoodbackClient.retrofit;
import static com.foodback.foodback.utils.ErrorDisplay.isBad;
import static com.foodback.foodback.utils.ErrorDisplay.isException;
import static com.foodback.foodback.utils.ErrorDisplay.isFailure;

/**
 * Created by Foodback.
 */

public class FeaturedMealsAdapter extends ArrayAdapter {

    private Context context;
    private LayoutInflater inflater;

    private ArrayList<Meal> meals;
    private ArrayList<Establishment> estabishments;

    public FeaturedMealsAdapter(Context context, ArrayList<Meal> meals) {
        super(context, R.layout.layout_spotlights, meals);

        this.context = context;
        this.meals = meals;

        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.layout_spotlights, parent, false);
        }

        TextView meal_name = convertView.findViewById(R.id.meal_name);
//        TextView meal_limit_date = convertView.findViewById(R.id.meal_limit_date);
        TextView meal_price = convertView.findViewById(R.id.meal_price);

        meal_name.setText(meals.get(position).getMeal());

        String days;
        if (meals.get(position).getTime_left() > 1) {
            days = "dias";
        } else {
            days = "dia";
        }
//        meal_limit_date.setText(String.format(Locale.UK, "%1$s %2$s", meals.get(position).getTime_left(), days));
        meal_price.setText(String.format(Locale.UK, "%1$.2f€", meals.get(position).getPrice()));

        getEstablishments(position, convertView);

        return convertView;
    }

    private void getEstablishments(int position, final View convertView) {

        try {
            EstablishmentEndpoints services = retrofit.create(EstablishmentEndpoints.class);
            Call<Establishment> call = services
                    .getEstablishment(meals.get(position).getEstablishment_id());

            call.enqueue(new Callback<Establishment>() {
                @Override
                public void onResponse(Call<Establishment> call, Response<Establishment> response) {
                    if (response.isSuccessful()) {
                        Establishment establishment = response.body();

                        TextView estab_name = convertView.findViewById(R.id.estab_name);
                        TextView estab_zone = convertView.findViewById(R.id.estab_zone);
                        TextView estab_city = convertView.findViewById(R.id.estab_city);
                        TextView estab_category = convertView.findViewById(R.id.estab_category);
                        TextView estab_contact = convertView.findViewById(R.id.estab_contact);
                        TextView estab_delivery = convertView.findViewById(R.id.estab_delivery);

                        ImageView estab_image = convertView.findViewById(R.id.estab_image);

                        estab_name.setText(establishment.getName());
                        estab_zone.setText(establishment.getZone());
                        estab_city.setText(establishment.getCity());
                        estab_category.setText(establishment.getCategory());
                        estab_contact.setText(establishment.getContact());

                        if (establishment.getDelivery()) {
                            estab_delivery.setText(context.getString(R.string.display_delivery));
                        }

                        GlideApp.with(context)
                                .load(getBaseUrl() + "/images/establishment/profile/" + establishment.getId())
                                .transition(withCrossFade())
                                .fallback(R.drawable.foodback_logo)
                                .fitCenter()
                                .into(estab_image);
                    } else {
                        isBad(context, response);
                    }
                }

                @Override
                public void onFailure(Call<Establishment> call, Throwable t) {
                    isFailure(context, t);
                }
            });
        } catch (Exception e) {
            isException(context, e);
        }
    }

}
