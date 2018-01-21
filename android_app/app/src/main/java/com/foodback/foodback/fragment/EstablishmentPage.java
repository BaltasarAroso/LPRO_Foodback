package com.foodback.foodback.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.foodback.foodback.R;
import com.foodback.foodback.activity.EstablishmentSelectedPage;
import com.foodback.foodback.config.EstablishmentEndpoints;
import com.foodback.foodback.logic.Comment;
import com.foodback.foodback.logic.Establishment;
import com.foodback.foodback.utils.DialogReport;
import com.foodback.foodback.utils.EstablishmentListAdapter;

import java.util.ArrayList;

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
public class EstablishmentPage extends Fragment implements DialogReport.DialogReportListener {

    private Establishment establishment;
    ArrayList<Comment> comments = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_establishment_page, container, false);

        ImageView btnReport = view.findViewById(R.id.icon_report);
        btnReport.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                opendialog();
            }
        });

        fillEstablishmentInfo();

        return view;
    }

    private void opendialog() {
        DialogReport dialogReport = new DialogReport();
        dialogReport.show(getFragmentManager(), "popup_report");
    }
    @Override
    public void applyfield(String editReport) {
        // TODO o popup passa para esta string o que foi escrito. Enviar isto para o servidor
    }

    private void fillEstablishmentInfo() {
        try {
            EstablishmentEndpoints services = retrofit.create(EstablishmentEndpoints.class);
//            Call<Establishment> call = services.getEstablishment(INSERT ID HERE);
//
//            call.enqueue(new Callback<Establishment>() {
//                @Override
//                public void onResponse(Call<Establishment> call, Response<Establishment> response) {
//                    if(response.isSuccessful()) {
//                        establishment = response.body();
//
//
//                    } else {
//                        isBad(getActivity(), response);
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<Establishment> call, Throwable t) {
//                    isFailure(getActivity(), t);
//                }
//            });
        } catch(Exception e) {
            isException(getActivity(), e);
        }
    }

    //TODO inserir o adapter para o ListView do Menu e para o ListView das opiniões

    private void declareComments(View view) {

        ListView listRestaurants = view.findViewById(R.id.list_comments);
        //listRestaurants.setAdapter(new CommentListAdapter(getActivity(), comments));
    }


    @Override
    public void onStart() {
        super.onStart();
    }


}
