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
import com.foodback.foodback.logic.Order;
import com.foodback.foodback.logic.OrderMeal;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Foodback on 2017/2018.
 */

public class TrackOrderListAdapter extends ArrayAdapter {

    private Context context;
    private LayoutInflater inflater;

    private ArrayList<Order> orders;

    public TrackOrderListAdapter(Context context, ArrayList<Order> orders) {
        super(context, R.layout.layout_track_order, orders);

        this.context = context;
        this.orders = orders;

        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        if(null == convertView) {
            convertView = inflater.inflate(R.layout.layout_track_order, parent, false);
        }

        TextView orderName = convertView.findViewById(R.id.order_name);
        TextView orderState = convertView.findViewById(R.id.order_state);


        String total = null;

        for (OrderMeal x: orders.get(position).getMeals()) {
            total += x.getMeal();
        }

        orderName.setText(total);
        orderState.setText(orders.get(position).getState());

        return convertView;
    }
}
