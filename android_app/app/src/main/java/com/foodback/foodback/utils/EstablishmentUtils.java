package com.foodback.foodback.utils;

import android.content.Context;
import android.widget.ImageView;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.extractors.StringExtractor;
import com.amigold.fundapter.interfaces.DynamicImageLoader;
import com.foodback.foodback.R;
import com.foodback.foodback.logic.Establishment;

import java.util.Locale;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import static com.foodback.foodback.config.FoodbackClient.getBaseUrl;

/**
 * Created by FoodBack.
 */

public class EstablishmentUtils {

    /**
     * @param context the activity/fragment the screen is on
     * @return a dictionary that maps establishment parameters on views
     */
    public static BindDictionary<Establishment> createDictionary(final Context context) {

        BindDictionary<Establishment> dictionary = new BindDictionary<>();

        dictionary.addStringField(R.id.estab_name, new StringExtractor<Establishment>() {
            @Override
            public String getStringValue(Establishment estab, int position) {
                return estab.getName();
            }
        });
        dictionary.addStringField(R.id.estab_zone, new StringExtractor<Establishment>() {
            @Override
            public String getStringValue(Establishment estab, int position) {
                return estab.getZone();
            }
        });
        dictionary.addStringField(R.id.estab_city, new StringExtractor<Establishment>() {
            @Override
            public String getStringValue(Establishment estab, int position) {
                return estab.getCity();
            }
        });
        dictionary.addStringField(R.id.estab_category, new StringExtractor<Establishment>() {
            @Override
            public String getStringValue(Establishment estab, int position) {
                return estab.getCategory();
            }
        });
        dictionary.addStringField(R.id.estab_contact, new StringExtractor<Establishment>() {
            @Override
            public String getStringValue(Establishment estab, int position) {
                return estab.getContact();
            }
        });
        dictionary.addStringField(R.id.estab_avg_price, new StringExtractor<Establishment>() {
            @Override
            public String getStringValue(Establishment estab, int position) {
                if (estab.getAvg_price() > 0) {
                    return "" + estab.getAvg_price() + "€";
                } else {
                    return null;
                }
            }
        });
        dictionary.addStringField(R.id.estab_delivery, new StringExtractor<Establishment>() {
            @Override
            public String getStringValue(Establishment estab, int position) {
                if (estab.getDelivery()) {
                    return "Encomendas";
                } else {
                    return null;
                }
            }
        });
        dictionary.addStringField(R.id.estab_rating, new StringExtractor<Establishment>() {
            @Override
            public String getStringValue(Establishment estab, int position) {
                if (estab.getRating() > 0) {
                    return String.format(Locale.UK, "%.1f", estab.getRating());
                } else {
                    return null;
                }
            }
        });
        dictionary.addDynamicImageField(R.id.estab_image, new StringExtractor<Establishment>() {
            @Override
            public String getStringValue(Establishment estab, int position) {
                return getBaseUrl() + "/image/establishment/profile/" + estab.getId();
            }
        }, new DynamicImageLoader() {
            @Override
            public void loadImage(String url, ImageView view) {
                GlideApp.with(context)
                        .load(url)
                        .transition(withCrossFade())
                        .fallback(R.drawable.foodback_logo)
                        .fitCenter()
                        .into(view);
            }
        });

        return dictionary;
    }

}
