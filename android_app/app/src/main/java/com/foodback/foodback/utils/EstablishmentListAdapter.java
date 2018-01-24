package com.foodback.foodback.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.foodback.foodback.R;
import com.foodback.foodback.logic.Establishment;

import java.util.ArrayList;
import java.util.Locale;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import static com.foodback.foodback.config.FoodbackClient.getBaseUrl;

/**
 * Created by FoodBack.
 */

public class EstablishmentListAdapter extends ArrayAdapter {

    private Context context;
    private LayoutInflater inflater;

    private ArrayList<Establishment> establishments;

    public EstablishmentListAdapter(Context context, ArrayList<Establishment> establishments) {
        super(context, R.layout.layout_establishment, establishments);

        this.context = context;
        this.establishments = establishments;

        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if(null == convertView) {
            convertView = inflater.inflate(R.layout.layout_establishment, parent, false);
        }

        TextView estab_name = convertView.findViewById(R.id.estab_name);
        TextView estab_zone = convertView.findViewById(R.id.estab_zone);
        TextView estab_city = convertView.findViewById(R.id.estab_city);
        TextView estab_category = convertView.findViewById(R.id.estab_category);
        TextView estab_contact = convertView.findViewById(R.id.estab_contact);
        TextView estab_avg_price = convertView.findViewById(R.id.estab_avg_price);
        TextView estab_delivery = convertView.findViewById(R.id.estab_delivery);
        TextView estab_rating = convertView.findViewById(R.id.estab_rating);
        ImageView estab_image = convertView.findViewById(R.id.estab_image);

        estab_name.setText(establishments.get(position).getName());
        estab_zone.setText(establishments.get(position).getZone());
        estab_city.setText(establishments.get(position).getCity());
        estab_category.setText(establishments.get(position).getCategory());
        estab_contact.setText(establishments.get(position).getContact());

        if(establishments.get(position).getAvg_price() > 0) {
            estab_avg_price.setText(context.getString(R.string.display_price, establishments.get(position).getAvg_price()));
        }

        if(establishments.get(position).getDelivery()) {
            estab_delivery.setText(context.getString(R.string.display_delivery));
        }

        if(establishments.get(position).getRating() > 0) {
            estab_rating.setText(String.format(Locale.UK, "%.1f", establishments.get(position).getRating()));
        }

        GlideApp.with(context)
                .load(getBaseUrl() + "/images/establishment/profile/" + establishments.get(position).getId())
                .transition(withCrossFade())
                .fallback(R.drawable.foodback_logo)
                .error(R.drawable.foodback_logo)
                .fitCenter()
                .into(estab_image);

        return convertView;
    }

}
