package com.foodback.foodback.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.foodback.foodback.R;
import com.foodback.foodback.activity.EstablishmentSelectedPage;
import com.foodback.foodback.config.EstablishmentEndpoints;
import com.foodback.foodback.logic.Establishment;
import com.foodback.foodback.utils.ErrorMessageAdapter;
import com.foodback.foodback.utils.EstablishmentListAdapter;
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
public class Dessert extends Fragment {

    ArrayList<Establishment> desserts = new ArrayList<>();
    ArrayList<String> errors = new ArrayList<>();

    public Dessert() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_dessert, container, false);

        fillEstablishmentList(view);

        return view;
    }

    private void fillEstablishmentList(final View view) {
        try {
            EstablishmentEndpoints services = retrofit.create(EstablishmentEndpoints.class);
            Call<List<Establishment>> call = services.getEstablishmentsFiltered(3);

            call.enqueue(new Callback<List<Establishment>>() {
                @Override
                public void onResponse(Call<List<Establishment>> call, Response<List<Establishment>> response) {
                    if(response.isSuccessful()) {
                        List<Establishment> tmp = response.body();
                        desserts.addAll(tmp);

                        declareList(view);
                    } else {
                        if(response.code() == 404) {
                            declareError(view);
                        } else
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

    private void declareList(View view) {

        ListView listDesserts = view.findViewById(R.id.list_desserts);
        listDesserts.setAdapter(new EstablishmentListAdapter(getActivity(), desserts));
        listDesserts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Gson gson = new Gson();
                String serialized = gson.toJson(desserts.get(position));

                Intent i = new Intent(getActivity(), EstablishmentSelectedPage.class);
                i.putExtra("establishment", serialized);
                startActivity(i);
            }
        });
    }

    private void declareError(View view) {

        errors.add("Nenhuma sobremesa encontrado.");

        ListView listDesserts = view.findViewById(R.id.list_desserts);
        listDesserts.setAdapter(new ErrorMessageAdapter(getActivity(), errors));
    }

    @Override
    public void onStart() {
        super.onStart();
    }

}
