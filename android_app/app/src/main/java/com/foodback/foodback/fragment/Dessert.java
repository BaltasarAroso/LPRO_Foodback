package com.foodback.foodback.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.foodback.foodback.R;
import com.foodback.foodback.config.EstablishmentEndpoints;
import com.foodback.foodback.logic.Establishment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.foodback.foodback.config.FoodbackClient.retrofit;
import static com.foodback.foodback.utils.ErrorDisplay.isBad;
import static com.foodback.foodback.utils.ErrorDisplay.isException;
import static com.foodback.foodback.utils.ErrorDisplay.isFailure;
import static com.foodback.foodback.utils.EstablishmentUtils.createDictionary;

/**
 * A simple {@link Fragment} subclass.
 */
public class Dessert extends Fragment {

    ArrayList<Establishment> desserts = new ArrayList<>();

    public Dessert() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_dessert, container, false);

        fillEstablishmentDictionary(view);

        return view;
    }

    private void fillEstablishmentDictionary(final View view) {
        try {
            EstablishmentEndpoints services = retrofit.create(EstablishmentEndpoints.class);
            Call<List<Establishment>> call = services.getEstablishmentsFiltered(3);

            call.enqueue(new Callback<List<Establishment>>() {
                @Override
                public void onResponse(Call<List<Establishment>> call, Response<List<Establishment>> response) {
                    if(response.isSuccessful()) {
                        List<Establishment> tmp = response.body();
                        desserts.addAll(tmp);
                        BindDictionary<Establishment> dictionary = createDictionary(getActivity());

                        declareList(view, dictionary);
                    } else {
                        isBad(getActivity(), response);
                    }
                }

                @Override
                public void onFailure(Call<List<Establishment>> call, Throwable t) {
                    isFailure(getActivity(), t);
                }
            });

        } catch(Exception e) {
            isException(getActivity(), e);
        }
    }

    private void declareList(View view, BindDictionary<Establishment> dictionary) {

        FunDapter<Establishment> adapter = new FunDapter<Establishment>(Dessert.this.getActivity(), desserts, R.layout.layout_establishment, dictionary);

        ListView listDesserts = view.findViewById(R.id.list_desserts);
        listDesserts.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

}
