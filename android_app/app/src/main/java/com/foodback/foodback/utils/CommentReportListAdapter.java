package com.foodback.foodback.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.foodback.foodback.R;
import com.foodback.foodback.config.UserEndpoints;
import com.foodback.foodback.logic.Report;
import com.foodback.foodback.logic.User;

import java.util.ArrayList;

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

public class CommentReportListAdapter extends ArrayAdapter {

    private Context context;
    private LayoutInflater inflater;

    private ArrayList<Report> reports;

    private UserEndpoints services;

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

        services = retrofit.create(UserEndpoints.class);

        TextView reportTitle = convertView.findViewById(R.id.report_user_title);

        getReporterName(position, reportTitle);

        return convertView;
    }

    private void getReporterName(final int position, final TextView reportTitle) {
        try {
            Call<User> call = services.getUserById(reports.get(position).getReporter_id());

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.isSuccessful()) {
                        User tmp = response.body();
                        getReportedName(position, tmp.getName(), reportTitle);
                    } else {
                        isBad(context, response);
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    isFailure(context, t);
                }
            });
        } catch(Exception e) {
            isException(context, e);
        }
    }


    private void getReportedName(int position, final String reporter, final TextView reportTitle) {
//        try {
//            //TODO: get person who commented
//            Call<User> call = services.getUserById(reports.get(position).get);
//
//            call.enqueue(new Callback<User>() {
//                @Override
//                public void onResponse(Call<User> call, Response<User> response) {
//                    if(response.isSuccessful()) {
//                        User tmp = response.body();
//                        setReportTitle(reporter, tmp.getName(), reportTitle);
//                    } else {
//                        isBad(context, response);
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<User> call, Throwable t) {
//                    isFailure(context, t);
//                }
//            });
//        } catch(Exception e) {
//            isException(context, e);
//        }
    }

    private void setReportTitle(String reporter, String reported, TextView reportTitle) {
        reportTitle.setText(context.getString(R.string.report_title, reporter, reported));
    }
}
