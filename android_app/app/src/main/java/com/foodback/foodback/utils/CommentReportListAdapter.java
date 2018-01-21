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
 * Created by FoodBack.
 */

public class CommentReportListAdapter extends ArrayAdapter {

    private Context context;
    private LayoutInflater inflater;

    private ArrayList<Report> reports;

    public CommentReportListAdapter(Context context, ArrayList<Report> reports) {
        super(context, R.layout.layout_reports_user, reports);

        this.context = context;
        this.reports = reports;

        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if(null == convertView) {
            convertView = inflater.inflate(R.layout.layout_reports_user, parent, false);
        }



        return convertView;
    }

}
