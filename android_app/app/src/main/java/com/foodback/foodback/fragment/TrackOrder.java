package com.foodback.foodback.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.foodback.foodback.R;
import com.foodback.foodback.config.OrderEndpoints;
import com.foodback.foodback.logic.Order;
import com.foodback.foodback.utils.TrackOrderListAdapter;

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
public class TrackOrder extends Fragment {

    ArrayList<Order> orders = new ArrayList<>();
    ArrayList<String> errors = new ArrayList<>();

    public TrackOrder() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_track_order, container, false);

        getMyOrders(view);

        return view;
    }

    private void getMyOrders(final View view) {
        try {
            OrderEndpoints services = retrofit.create(OrderEndpoints.class);
            Call<List<Order>> call = services.getOrdersMadeByUser();

            call.enqueue(new Callback<List<Order>>() {
                @Override
                public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                    if (response.isSuccessful()) {
                        List<Order> tmp = response.body();
                        orders.addAll(tmp);
                        declareTrackingList(view);
                    } else {
                        if (response.code() == 404) {
                            declareTrackingError(view);
                        } else {
                            isBad(getContext(), response);
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Order>> call, Throwable t) {
                    isFailure(getContext(), t);
                }
            });

        } catch (Exception e) {
            isException(getContext(), e);
        }
    }

    private void declareTrackingList(View view) {

        ListView listTrackOrders = view.findViewById(R.id.list_track_orders);
        listTrackOrders.setAdapter(new TrackOrderListAdapter(getActivity(), orders));
    }

    private void declareTrackingError(View view) {

        errors.add("Não possui nenhuma encomenda.");

        ListView listTrackOrders = view.findViewById(R.id.list_track_orders);
        listTrackOrders.setAdapter(new TrackOrderListAdapter(getActivity(), orders));
    }

}
