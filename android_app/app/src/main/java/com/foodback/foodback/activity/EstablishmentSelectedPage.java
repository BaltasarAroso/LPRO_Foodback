package com.foodback.foodback.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.foodback.foodback.R;
import com.foodback.foodback.config.MealEndpoints;
import com.foodback.foodback.logic.Establishment;
import com.foodback.foodback.logic.Meal;
import com.foodback.foodback.utils.DialogReport;
import com.foodback.foodback.utils.ErrorMessageAdapter;
import com.foodback.foodback.utils.EstablishmentMenuAdapter;
import com.foodback.foodback.utils.GlideApp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
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

public class EstablishmentSelectedPage extends AppCompatActivity implements DialogReport.DialogReportListener {

    protected TextView editname, editcategory, editaddress, editcontact, editavgprice,
            editzone, editcity, editschedule1, editschedule2, editrating;
    protected CheckBox editdelivery;
    protected ImageView editimage;

    protected Establishment estab;

    protected ArrayList<Meal> meals = new ArrayList<>();
    protected ArrayList<String> errors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establishment_selected_page);

        // Initializing Views
        editimage = findViewById(R.id.estab_page_image);
        editname = findViewById(R.id.estab_page_name);
        editcategory = findViewById(R.id.estab_page_category);
        editaddress = findViewById(R.id.estab_page_address);
        editcontact = findViewById(R.id.estab_page_contact);
        editavgprice = findViewById(R.id.estab_page_avg_price);
        editzone = findViewById(R.id.estab_page_zone);
        editcity = findViewById(R.id.estab_page_city);
        editschedule1 = findViewById(R.id.estab_page_schedule1);
        editschedule2 = findViewById(R.id.estab_page_schedule2);
        editdelivery = findViewById(R.id.estab_page_delivery);
        editrating = findViewById(R.id.estab_page_rating);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            String serialized = bundle.getString("establishment");
            Type type = new TypeToken<Establishment>() {}.getType();
            Gson gson = new Gson();
            estab = gson.fromJson(serialized, type);

            if(estab != null) {
                editname.setText(estab.getName());
                editcategory.setText(estab.getCategory());
                editaddress.setText(estab.getAddress());
                editcontact.setText(estab.getContact());
                editavgprice.setText(getString(R.string.display_price, estab.getAvg_price()));
                editzone.setText(estab.getZone());
                editcity.setText(estab.getCity());
                editschedule1.setText(estab.getSchedule1());
                editschedule2.setText(estab.getSchedule2());
                if(estab.getDelivery()) {
                    editdelivery.setChecked(true);
                }
                if(estab.getRating() > 0) {
                    editrating.setText(String.format(Locale.UK, "%.1f", estab.getRating()));
                }

                GlideApp.with(EstablishmentSelectedPage.this)
                        .load(getBaseUrl() + "/images/establishment/profile/" + estab.getId())
                        .transition(withCrossFade())
                        .fallback(R.drawable.foodback_logo)
                        .fitCenter()
                        .into(editimage);
            }
        }

        fillMealList();
    }

    private void fillMealList() {
        try {
            MealEndpoints services = retrofit.create(MealEndpoints.class);
            Call<List<Meal>> call = services.getAllMeals(estab.getId());

            call.enqueue(new Callback<List<Meal>>() {
                @Override
                public void onResponse(Call<List<Meal>> call, Response<List<Meal>> response) {
                    if(response.isSuccessful()) {
                        List<Meal> tmp = response.body();
                        meals.addAll(tmp);

                        declareList();
                    } else {
                        if(response.code() == 404) {
                            declareError();
                        } else {
                            isBad(EstablishmentSelectedPage.this, response);
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Meal>> call, Throwable t) {
                    isFailure(EstablishmentSelectedPage.this, t);
                }
            });

        } catch(Exception e) {
            isException(EstablishmentSelectedPage.this, e);
        }
    }

    private void declareList() {
        ListView listMeals = findViewById(R.id.list_menu);
        listMeals.setAdapter(new EstablishmentMenuAdapter(EstablishmentSelectedPage.this, meals));
    }

    private void declareError() {
        errors.add("Sem refeições encontradas.");

        ListView listMeals = findViewById(R.id.list_menu);
        listMeals.setAdapter(new ErrorMessageAdapter(EstablishmentSelectedPage.this, errors));
    }

    private void opendialog() {
        DialogReport dialogReport = new DialogReport();
        dialogReport.show(getSupportFragmentManager(), "popup_report");
    }

    @Override
    public void applyfield(String editReport) {
        // TODO o popup passa para esta string o que foi escrito. Enviar isto para o servidor
    }


}
