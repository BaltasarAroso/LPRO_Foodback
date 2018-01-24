package com.foodback.foodback.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.foodback.foodback.R;
import com.foodback.foodback.config.OrderEndpoints;
import com.foodback.foodback.logic.OrderMeal;
import com.foodback.foodback.utils.ErrorMessageAdapter;
import com.foodback.foodback.utils.EstablishmentOrderListAdapter;

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
public class EstablishmentDelivery extends Fragment {

    ArrayList<OrderMeal> orders = new ArrayList<>();
    ArrayList<String> errors = new ArrayList<>();

    public EstablishmentDelivery() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_establishment_delivery, container, false);

        fillDeliveryList(view);

        return view;
    }

    private void fillDeliveryList(final View view) {
        try {
            OrderEndpoints services = retrofit.create(OrderEndpoints.class);
            Call<List<OrderMeal>> call = services.getUnpreparedOrders();

            call.enqueue(new Callback<List<OrderMeal>>() {
                @Override
                public void onResponse(Call<List<OrderMeal>> call, Response<List<OrderMeal>> response) {
                    if(response.isSuccessful()) {
                        List<OrderMeal> tmp = response.body();
                        orders.addAll(tmp);

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
                public void onFailure(Call<List<OrderMeal>> call, Throwable t) {
                    isFailure(getActivity(), t);
                }
            });
        } catch(Exception e) {
            isException(getActivity(), e);
        }
    }

    private void declareList(View view) {
        ListView listOrders = view.findViewById(R.id.list_deliveries_estabs);
        listOrders.setAdapter(new EstablishmentOrderListAdapter(getActivity(), orders));
    }

    private void declareError(View view) {

        errors.add("Sem pedidos.");

        ListView listOrders = view.findViewById(R.id.list_deliveries_estabs);
        listOrders.setAdapter(new ErrorMessageAdapter(getActivity(), errors));
    }

    @Override
    public void onStart() {
        super.onStart();
    }

}
