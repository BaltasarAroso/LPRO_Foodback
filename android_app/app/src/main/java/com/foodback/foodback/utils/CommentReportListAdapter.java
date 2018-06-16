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
import com.foodback.foodback.config.CommentEndpoints;
import com.foodback.foodback.config.ReportEndpoints;
import com.foodback.foodback.config.UserEndpoints;
import com.foodback.foodback.logic.Comment;
import com.foodback.foodback.logic.Report;
import com.foodback.foodback.logic.User;

import java.util.ArrayList;

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

public class CommentReportListAdapter extends ArrayAdapter {

    private Context context;
    private LayoutInflater inflater;

    private ArrayList<Report> reports;

    private UserEndpoints userServices;
    private CommentEndpoints commentServices;
    private ReportEndpoints reportServices;

    public CommentReportListAdapter(Context context, ArrayList<Report> reports) {
        super(context, R.layout.layout_reports_user, reports);

        this.context = context;
        this.reports = reports;

        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.layout_reports_user, parent, false);
        }

        userServices = retrofit.create(UserEndpoints.class);
        commentServices = retrofit.create(CommentEndpoints.class);
        reportServices = retrofit.create(ReportEndpoints.class);

        TextView reportTitle = convertView.findViewById(R.id.report_user_title);
        TextView reportContent = convertView.findViewById(R.id.report_user_info);
        final Button discard = convertView.findViewById(R.id.btnDiscardComment);
        final Button delete = convertView.findViewById(R.id.btnDeleteComment);

        discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                discardReport(position);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteComment(position);
            }
        });

        //call chain that displays the title
        getReporterName(position, reportTitle);

        //report content
        getReportedComment(position, reportContent);

        return convertView;
    }

    private void setReportContent(String report, String comment, TextView reportContent) {
        reportContent.setText(context.getString(R.string.report_content, report, comment));
    }

    private void setReportTitle(String reporter, String reported, TextView reportTitle) {
        reportTitle.setText(context.getString(R.string.report_title, reporter, reported));
    }

    private void getReportedComment(final int position, final TextView reportContent) {
        try {
            Call<Comment> call = commentServices.getComment(reports.get(position).getComment_id());

            call.enqueue(new Callback<Comment>() {
                @Override
                public void onResponse(Call<Comment> call, Response<Comment> response) {
                    if (response.isSuccessful()) {
                        Comment tmp = response.body();
                        setReportContent(
                                reports.get(position).getReport(),
                                tmp.getComment(),
                                reportContent);
                    } else {
                        isBad(context, response);
                    }
                }

                @Override
                public void onFailure(Call<Comment> call, Throwable t) {
                    isFailure(context, t);
                }
            });
        } catch (Exception e) {
            isException(context, e);
        }
    }

    private void deleteComment(final int position) {
        try {
            Call<ResponseBody> call = commentServices
                    .deleteComment(reports.get(position).getComment_id());

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        reports.remove(position);
                        CommentReportListAdapter.this.notifyDataSetChanged();
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

    private void discardReport(final int position) {
        try {
            Call<ResponseBody> call = reportServices.deleteReport(reports.get(position).getId());

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        reports.remove(position);
                        CommentReportListAdapter.this.notifyDataSetChanged();
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

    private void getReporterName(final int position, final TextView reportTitle) {
        try {
            Call<User> call = userServices.getUserById(reports.get(position).getReporter_id());

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
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
        } catch (Exception e) {
            isException(context, e);
        }
    }


    private void getReportedName(int position, final String reporter, final TextView reportTitle) {
        try {
            Call<User> call = commentServices.getCommenter(reports.get(position).getComment_id());

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        User tmp = response.body();
                        setReportTitle(reporter, tmp.getName(), reportTitle);
                    } else {
                        isBad(context, response);
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    isFailure(context, t);
                }
            });
        } catch (Exception e) {
            isException(context, e);
        }
    }
}
