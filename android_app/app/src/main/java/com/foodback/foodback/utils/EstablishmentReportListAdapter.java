package com.foodback.foodback.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.foodback.foodback.R;
import com.foodback.foodback.logic.Report;

import java.util.ArrayList;

/**
 * Created by André.
 */

public class EstablishmentReportListAdapter extends ArrayAdapter {

    private Context context;
    private LayoutInflater inflater;

    private ArrayList<Report> reports;

    public EstablishmentReportListAdapter(Context context, ArrayList<Report> reports) {
        super(context, R.layout.layout_reports_estab, reports);

        this.context = context;
        this.reports = reports;

        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if(null == convertView) {
            convertView = inflater.inflate(R.layout.layout_reports_estab, parent, false);
        }


        return convertView;
    }

}
