package com.foodback.foodback.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.foodback.foodback.R;
import com.foodback.foodback.logic.Establishment;

import java.util.ArrayList;

/**
 * Created by Foodback on 2017/2018
 */

public class ErrorMessageAdapter extends ArrayAdapter {

    private Context context;
    private LayoutInflater inflater;

    private ArrayList<String> errors;

    public ErrorMessageAdapter(Context context, ArrayList<String> errors) {
        super(context, R.layout.layout_no_establishments_found, errors);

        this.context = context;
        this.errors = errors;

        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.layout_no_establishments_found, parent, false);
        }

        TextView messageError = convertView.findViewById(R.id.no_establishments_found);

        messageError.setText(errors.get(position));

        return convertView;
    }

}
