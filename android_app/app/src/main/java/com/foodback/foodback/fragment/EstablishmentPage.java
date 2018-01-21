package com.foodback.foodback.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foodback.foodback.R;
import com.foodback.foodback.config.EstablishmentEndpoints;
import com.foodback.foodback.logic.Establishment;

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
public class EstablishmentPage extends Fragment {

    private Establishment establishment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_establishment_page, container, false);

        fillEstablishmentInfo();

        return view;
    }

    private void fillEstablishmentInfo() {
        try {
            EstablishmentEndpoints services = retrofit.create(EstablishmentEndpoints.class);
//            Call<Establishment> call = services.getEstablishment(INSERT ID HERE);
//
//            call.enqueue(new Callback<Establishment>() {
//                @Override
//                public void onResponse(Call<Establishment> call, Response<Establishment> response) {
//                    if(response.isSuccessful()) {
//                        establishment = response.body();
//
//
//                    } else {
//                        isBad(getActivity(), response);
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<Establishment> call, Throwable t) {
//                    isFailure(getActivity(), t);
//                }
//            });
        } catch(Exception e) {
            isException(getActivity(), e);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
