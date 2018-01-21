package com.foodback.foodback.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.TextView;

import com.foodback.foodback.R;
import com.foodback.foodback.logic.Establishment;

public class EstablishmentSelectedPage extends AppCompatActivity {

    protected TextView editname, editcategory, editaddress, editcontact, editavgprice, editzone, editcity, editschedule1, editschedule2, editrating;
    protected CheckBox editdelivery;

    protected Establishment estab;

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
            editname.setText(bundle.getString(estab.getName()));
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
    }


}
