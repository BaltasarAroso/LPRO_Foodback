package com.foodback.foodback.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.foodback.foodback.R;
import com.foodback.foodback.config.OrderEndpoints;
import com.foodback.foodback.logic.OrderMeal;

import java.util.ArrayList;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.foodback.foodback.config.FoodbackClient.retrofit;
import static com.foodback.foodback.utils.ErrorDisplay.isBad;
import static com.foodback.foodback.utils.ErrorDisplay.isException;
import static com.foodback.foodback.utils.ErrorDisplay.isFailure;

/**
 * Created by FoodBack.
 */

public class EstablishmentOrderListAdapter extends ArrayAdapter {

    private Context context;
    private LayoutInflater inflater;

    private ArrayList<OrderMeal> orders;

    public EstablishmentOrderListAdapter(Context context, ArrayList<OrderMeal> orders) {
        super(context, R.layout.layout_establishment_deliveries, orders);

        this.context = context;
        this.orders = orders;

        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.layout_establishment_deliveries, parent, false);
        }

        TextView deliveryName = convertView.findViewById(R.id.estab_delivery_name);
        TextView deliveryQuantity = convertView.findViewById(R.id.estab_delivery_quantity);

        deliveryName.setText(orders.get(position).getMeal());
        deliveryQuantity.setText(String.format(Locale.UK, "%d", orders.get(position).getQuantity()));

        Button btnOrderReady = convertView.findViewById(R.id.btnOrderReady);

        btnOrderReady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeReady(position);
            }
        });

        return convertView;
    }

    private void makeReady(final int position) {
        try {
            OrderEndpoints services = retrofit.create(OrderEndpoints.class);
            Call<ResponseBody> call = services.makeOrderMealReady(orders.get(position).getId());

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        orders.remove(position);
                        EstablishmentOrderListAdapter.this.notifyDataSetChanged();
                    } else {
                        isBad(context, response);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    isFailure(context, t);
                }
            });
        } catch (Exception e) {
            isException(context, e);
        }
    }


}
