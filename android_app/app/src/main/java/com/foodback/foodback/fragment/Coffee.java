package com.foodback.foodback.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class Coffee extends Fragment {

    ArrayList<Establishment> coffees = new ArrayList<>();
    ArrayList<String> errors = new ArrayList<>();

    public Coffee() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_coffee, container, false);

        fillEstablishmentList(view);

        return view;
    }

    private void fillEstablishmentList(final View view) {
        try {
            EstablishmentEndpoints services = retrofit.create(EstablishmentEndpoints.class);
            Call<List<Establishment>> call = services.getEstablishmentsFiltered(1);

            call.enqueue(new Callback<List<Establishment>>() {
                @Override
                public void onResponse(Call<List<Establishment>> call, Response<List<Establishment>> response) {
                    if(response.isSuccessful()) {
                        List<Establishment> tmp = response.body();
                        coffees.addAll(tmp);

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

        ListView listCoffees = view.findViewById(R.id.list_coffees);
        listCoffees.setAdapter(new EstablishmentListAdapter(getActivity(), coffees));
        listCoffees.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Establishment selectedEstab = coffees.get(position);
                Toast.makeText(getActivity(), selectedEstab.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void declareError(View view) {

        errors.add("No establishment found.");

        ListView listCoffees = view.findViewById(R.id.list_coffees);
        listCoffees.setAdapter(new ErrorMessageAdapter(getActivity(), errors));
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
