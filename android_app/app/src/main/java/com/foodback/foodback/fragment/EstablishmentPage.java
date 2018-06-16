package com.foodback.foodback.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.foodback.foodback.R;
import com.foodback.foodback.config.EstablishmentEndpoints;
import com.foodback.foodback.logic.Establishment;
import com.foodback.foodback.logic.Meal;
import com.foodback.foodback.utils.DialogReport;
import com.foodback.foodback.utils.GlideApp;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import static com.foodback.foodback.config.FoodbackClient.getBaseUrl;
import static com.foodback.foodback.config.FoodbackClient.retrofit;
import static com.foodback.foodback.utils.ErrorDisplay.isBad;
import static com.foodback.foodback.utils.ErrorDisplay.isException;
import static com.foodback.foodback.utils.ErrorDisplay.isFailure;

/**
 * A simple {@link Fragment} subclass.
 */
public class EstablishmentPage extends Fragment implements DialogReport.DialogReportListener {

    protected TextView editname, editcategory, editaddress, editcontact, editavgprice,
            editzone, editcity, editschedule1, editschedule2, editrating;
    protected CheckBox editdelivery;
    protected ImageView editimage;

    protected Establishment estab;

    protected ArrayList<Meal> meals = new ArrayList<>();
    protected ArrayList<String> errors = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_establishment_page, container, false);


        // Initializing Views
        editimage = view.findViewById(R.id.estab_page_image);
        editname = view.findViewById(R.id.estab_page_name);
        editcategory = view.findViewById(R.id.estab_page_category);
        editaddress = view.findViewById(R.id.estab_page_address);
        editcontact = view.findViewById(R.id.estab_page_contact);
        editavgprice = view.findViewById(R.id.estab_page_avg_price);
        editzone = view.findViewById(R.id.estab_page_zone);
        editcity = view.findViewById(R.id.estab_page_city);
        editschedule1 = view.findViewById(R.id.estab_page_schedule1);
        editschedule2 = view.findViewById(R.id.estab_page_schedule2);
        editdelivery = view.findViewById(R.id.estab_page_delivery);
        editrating = view.findViewById(R.id.estab_page_rating);

        getEstabInfo();

        getEstabInfo();

        return view;
    }

    private void fillFields() {
        if (estab != null) {
            editname.setText(estab.getName());
            editcategory.setText(estab.getCategory());
            editaddress.setText(estab.getAddress());
            editcontact.setText(estab.getContact());
            editavgprice.setText(getString(R.string.display_price, estab.getAvg_price()));
            editzone.setText(estab.getZone());
            editcity.setText(estab.getCity());
            editschedule1.setText(estab.getSchedule1());
            editschedule2.setText(estab.getSchedule2());
            if (estab.getDelivery()) {
                editdelivery.setChecked(true);
            }
            if (estab.getRating() > 0) {
                editrating.setText(String.format(Locale.UK, "%.1f", estab.getRating()));
            }

            GlideApp.with(getActivity())
                    .load(getBaseUrl() + "/images/establishment/profile/" + estab.getId())
                    .transition(withCrossFade())
                    .fallback(R.drawable.foodback_logo)
                    .fitCenter()
                    .into(editimage);
        }
    }

    private void getEstabInfo() {
        try {
            EstablishmentEndpoints estabServices = retrofit.create(EstablishmentEndpoints.class);
            Call<Establishment> call = estabServices.getMyEstablishment();

            call.enqueue(new Callback<Establishment>() {
                @Override
                public void onResponse(Call<Establishment> call, Response<Establishment> response) {
                    if (response.isSuccessful()) {
                        estab = response.body();

                        fillFields();
                    } else {
                        isBad(getActivity(), response);
                    }
                }

                @Override
                public void onFailure(Call<Establishment> call, Throwable t) {
                    isFailure(getActivity(), t);
                }
            });
        } catch (Exception e) {
            isException(getActivity(), e);
        }
    }

    private void opendialog() {
        DialogReport dialogReport = new DialogReport();
        dialogReport.show(getFragmentManager(), "popup_report");
    }

    @Override
    public void applyfield(String editReport) {
        // TODO o popup passa para esta string o que foi escrito. Enviar isto para o servidor
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
