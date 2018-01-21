package com.foodback.foodback.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.foodback.foodback.R;
import com.foodback.foodback.config.EstablishmentEndpoints;
import com.foodback.foodback.config.UserEndpoints;
import com.foodback.foodback.logic.Establishment;
import com.foodback.foodback.logic.Report;
import com.foodback.foodback.logic.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

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

public class EstablishmentReportListAdapter extends ArrayAdapter {

    private Context context;
    private LayoutInflater inflater;

    private ArrayList<Report> reports;

    private UserEndpoints userServices;
    private EstablishmentEndpoints estabServices;

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

        userServices = retrofit.create(UserEndpoints.class);
        estabServices = retrofit.create(EstablishmentEndpoints.class);

        TextView reportTitle = convertView.findViewById(R.id.report_estab_title);
        TextView reportContent = convertView.findViewById(R.id.report_estab_info);

        reportContent.setText(reports.get(position).getReport());

        // call chain that gets necessary info
        getReporterName(position, reportTitle);

        return convertView;
    }

    private void getReporterName(final int position, final TextView reportTitle) {
        try {
            Call<User> call = userServices.getUserById(reports.get(position).getReporter_id());

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.isSuccessful()) {
                        User tmp = response.body();
                        getEstablishmentName(position, tmp.getName(), reportTitle);
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


    private void getEstablishmentName(int position, final String reporter, final TextView reportTitle) {
        try {
            Call<Establishment> call = estabServices
                    .getEstablishment(reports.get(position).getEstablishment_id());

            call.enqueue(new Callback<Establishment>() {
                @Override
                public void onResponse(Call<Establishment> call, Response<Establishment> response) {
                    if(response.isSuccessful()) {
                        Establishment tmp = response.body();
                        setReportFields(reporter, tmp.getName(), reportTitle);
                    } else {
                        isBad(context, response);
                    }
                }

                @Override
                public void onFailure(Call<Establishment> call, Throwable t) {
                    isFailure(context, t);
                }
            });
        } catch(Exception e) {
            isException(context, e);
        }
    }

    private void setReportFields(String reporter, String establishment, TextView reportTitle) {
        reportTitle.setText(context.getString(R.string.report_title, reporter, establishment));
    }

}
