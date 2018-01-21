package com.foodback.foodback.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.foodback.foodback.R;
import com.foodback.foodback.logic.Establishment;
import com.foodback.foodback.utils.DialogReport;

public class EstablishmentSelectedPage extends AppCompatActivity implements DialogReport.DialogReportListener {

    protected TextView editcategory, editaddress, editcontact, editavgprice, editzone, editcity, editschedule1, editschedule2, editrating;
    protected Toolbar editname;
    protected CheckBox editdelivery;

    protected Establishment estab;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establishment_selected_page);

        // Initializing Views
        editname = findViewById(R.id.estab_page_name);
        editcategory = findViewById(R.id.estab_page_category);
        editaddress = findViewById(R.id.estab_page_address);
        editcontact = findViewById(R.id.estab_page_contact);
        editavgprice = findViewById(R.id.estab_page_avg_price);
        editzone = findViewById(R.id.estab_page_zone);
        editcity = findViewById(R.id.estab_page_city);
        editdelivery = findViewById(R.id.estab_page_delivery);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            editname.setTitle(bundle.getString(estab.getName()));
            editcategory.setText(bundle.getString(estab.getCategory()));
            editaddress.setText(bundle.getString(estab.getAddress()));
            editcontact.setText(bundle.getString(estab.getContact()));
            editavgprice.setText(bundle.getString(String.valueOf(estab.getAvg_price())));
            editzone.setText(bundle.getString(estab.getZone()));
            editcity.setText(bundle.getString(estab.getCity()));
            editschedule1.setText(bundle.getString(estab.getSchedule1()));
            editschedule2.setText(bundle.getString(estab.getSchedule2()));
            editdelivery.setText(bundle.getString(estab.getName()));
            editrating.setText(bundle.getString(String.valueOf(estab.getRating())));
        }

        ImageView btnReport = findViewById(R.id.icon_report);
        btnReport.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                opendialog();
            }
        });
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
