package com.foodback.foodback.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.amigold.fundapter.interfaces.DynamicImageLoader;
import com.foodback.foodback.R;
import com.foodback.foodback.config.EstablishmentEndpoints;
import com.foodback.foodback.logic.Establishment;
import com.foodback.foodback.utils.GlideApp;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.foodback.foodback.config.FoodbackClient.getBaseUrl;
import static com.foodback.foodback.config.FoodbackClient.retrofit;
import static com.foodback.foodback.utils.ErrorDisplay.isBad;
import static com.foodback.foodback.utils.ErrorDisplay.isException;
import static com.foodback.foodback.utils.ErrorDisplay.isFailure;

/**
 * A simple {@link Fragment} subclass.
 */
public class Notices extends Fragment {

    ArrayList<Establishment> notices = new ArrayList<>();

    public Notices() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_notices, container, false);

        //fillEstablishmentDictionary(view);

        return view;
    }


    private BindDictionary<Establishment> createDictionary() {

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
        dictionary.addDynamicImageField(R.id.estab_image, new StringExtractor<Establishment>() {
            @Override
            public String getStringValue(Establishment estab, int position) {
                return getBaseUrl() + "/image/establishment/profile/" + estab.getId();
            }
        }, new DynamicImageLoader() {
            @Override
            public void loadImage(String url, ImageView view) {
                GlideApp.with(getActivity())
                        .load(url)
                        .placeholder(R.drawable.foodback_logo)
                        .error(R.drawable.foodback_logo)
                        .fallback(R.drawable.foodback_logo)
                        .fitCenter()
                        .thumbnail(0.1f)
                        .into(view);
            }
        });

        return dictionary;
    }

    private void declareList(View view, BindDictionary<Establishment> dictionary) {

        FunDapter<Establishment> adapter = new FunDapter<Establishment>(getActivity(), notices, R.layout.layout_notices, dictionary);

        ListView listRestaurants = view.findViewById(R.id.list_restaurants);
        listRestaurants.setAdapter(adapter);
        listRestaurants.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Establishment selectedEstab = notices.get(position);
                Toast.makeText(getActivity(), selectedEstab.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

}
