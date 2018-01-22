package com.foodback.foodback.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.foodback.foodback.R;
import com.foodback.foodback.activity.EstablishmentSelectedPage;
import com.foodback.foodback.config.EstablishmentEndpoints;
import com.foodback.foodback.config.ReportEndpoints;
import com.foodback.foodback.config.UserEndpoints;
import com.foodback.foodback.logic.Establishment;
import com.foodback.foodback.logic.Report;
import com.foodback.foodback.logic.User;
import com.foodback.foodback.utils.CommentReportListAdapter;
import com.foodback.foodback.utils.ErrorMessageAdapter;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
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
    ArrayList<String> errors = new ArrayList<>();

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
                        if (response.code() == 404) {
                            declareCommentError(view);
                        } else {
                            isBad(getActivity(), response);
                        }
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

    private void declareCommentError(View view) {

        errors.add("Nenhuma denúncia encontrada");

        ListView listRestaurants = view.findViewById(R.id.list_reports_users);
        listRestaurants.setAdapter(new ErrorMessageAdapter(getActivity(), errors));
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
                        if(response.code() == 404) {
                            declareEstabError(view);
                        } else {
                            isBad(getActivity(), response);
                        }
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

    private void declareEstabError(View view) {

        errors.add("Nenhuma denúncia encontrada");

        ListView listRestaurants = view.findViewById(R.id.list_reports_estabs);
        listRestaurants.setAdapter(new ErrorMessageAdapter(getActivity(), errors));
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    /****************************************************************************************************/

    public class EstablishmentReportListAdapter extends ArrayAdapter {

        private Context context;
        private LayoutInflater inflater;

        private ArrayList<Report> reports;

        private UserEndpoints userServices;
        private EstablishmentEndpoints estabServices;
        private ReportEndpoints reportServices;

        public EstablishmentReportListAdapter(Context context, ArrayList<Report> reports) {
            super(context, R.layout.layout_reports_estab, reports);

            this.context = context;
            this.reports = reports;

            inflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
            if(null == convertView) {
                convertView = inflater.inflate(R.layout.layout_reports_estab, parent, false);
            }

            userServices = retrofit.create(UserEndpoints.class);
            estabServices = retrofit.create(EstablishmentEndpoints.class);
            reportServices = retrofit.create(ReportEndpoints.class);

            TextView reportTitle = convertView.findViewById(R.id.report_estab_title);
            TextView reportContent = convertView.findViewById(R.id.report_estab_info);
            final Button discard = convertView.findViewById(R.id.btnDiscardChangeInfo);
            final Button delete = convertView.findViewById(R.id.btnDeleteEstabInfo);

            discard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    discardReport(position);
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeEstabInfo(reports.get(position));
                }
            });

            reportContent.setText(reports.get(position).getReport());

            //call chain that does all the work
            getReporterName(position, reportTitle);

            return convertView;
        }

        private void discardReport(final int position) {
            try {
                Call<ResponseBody> call = reportServices.deleteReport(reports.get(position).getId());

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()) {
                            reports.remove(position);
                            EstablishmentReportListAdapter.this.notifyDataSetChanged();
                        } else {
                            isBad(context, response);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        isFailure(context, t);
                    }
                });
            } catch(Exception e) {
                isException(context, e);
            }
        }

        private void changeEstabInfo(Report report) {
            EstablishmentChangeInfo fragment = new EstablishmentChangeInfo();
            Bundle args = new Bundle();
            args.putString("establishment_id", "" + report.getEstablishment_id());
            fragment.setArguments(args);
            FragmentTransaction fm = getFragmentManager().beginTransaction();
            fm.replace(R.id.admin_menu, fragment);
            fm.addToBackStack(null);
            fm.show(fragment);
            fm.commit();
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


}
