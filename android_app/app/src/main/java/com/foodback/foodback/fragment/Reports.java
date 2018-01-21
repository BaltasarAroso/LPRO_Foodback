package com.foodback.foodback.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.foodback.foodback.R;
import com.foodback.foodback.config.ReportEndpoints;
import com.foodback.foodback.logic.Report;
import com.foodback.foodback.utils.CommentReportListAdapter;
import com.foodback.foodback.utils.EstablishmentReportListAdapter;

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
public class Reports extends Fragment {

    ArrayList<Report> commentReports = new ArrayList<>();
    ArrayList<Report> estabReports = new ArrayList<>();

    ReportEndpoints services;

    public Reports() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_reports, container, false);

        services = retrofit.create(ReportEndpoints.class);

        fillCommentReports(view);

        fillEstablishmentReports(view);

        return view;
    }

    private void fillCommentReports(final View view) {

        try {
            Call<List<Report>> call = services.getAllCommentReports();

            call.enqueue(new Callback<List<Report>>() {
                @Override
                public void onResponse(Call<List<Report>> call, Response<List<Report>> response) {
                    if(response.isSuccessful()) {
                        List<Report> tmp = response.body();
                        commentReports.addAll(tmp);

                        declareCommentReportList(view);
                    } else {
                        isBad(getActivity(), response);
                    }
                }

                @Override
                public void onFailure(Call<List<Report>> call, Throwable t) {
                    isFailure(getActivity(), t);
                }
            });

        } catch(Exception e) {
            isException(getActivity(), e);
        }
    }

    private void declareCommentReportList(View view) {

        ListView listCommentReports = view.findViewById(R.id.list_reports_users);
        listCommentReports.setAdapter(new CommentReportListAdapter(getActivity(), commentReports));
    }

    private void fillEstablishmentReports(final View view) {

        try {
            Call<List<Report>> call = services.getAllEstablishmentReports();

            call.enqueue(new Callback<List<Report>>() {
                @Override
                public void onResponse(Call<List<Report>> call, Response<List<Report>> response) {
                    if(response.isSuccessful()) {
                        List<Report> tmp = response.body();
                        estabReports.addAll(tmp);

                        declareEstablishmentReportList(view);
                    } else {
                        isBad(getActivity(), response);
                    }
                }
                @Override
                public void onFailure(Call<List<Report>> call, Throwable t) {
                    isFailure(getActivity(), t);
                }
            });
        } catch(Exception e) {
            isException(getActivity(), e);
        }
    }

    private void declareEstablishmentReportList(View view) {

        ListView listEstabReports = view.findViewById(R.id.list_reports_estabs);
        listEstabReports.setAdapter(new EstablishmentReportListAdapter(getActivity(), estabReports));
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
