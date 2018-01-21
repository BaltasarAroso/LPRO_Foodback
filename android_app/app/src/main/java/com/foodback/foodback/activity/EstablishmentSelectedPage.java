package com.foodback.foodback.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.foodback.foodback.R;
import com.foodback.foodback.logic.Establishment;
import com.foodback.foodback.utils.CategoryUtils;

public class EstablishmentSelectedPage extends AppCompatActivity {

    protected TextView editname, editaddress, editcontact, editavgprice, editzone, editcity;
    protected Spinner editcategory;
    protected CheckBox editdelivery;

    protected String name, category, address, email, contact, zone, city;
    protected Integer avg_price;
    protected Boolean delivery;

    protected Establishment estab;

    CategoryUtils catUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establishment_selected_page);

        // Initializing Views
        editname = findViewById(R.id.estab_page_name);
        //editcategory = findViewById(R.id.estab_page_category);
        editaddress = findViewById(R.id.estab_page_address);
        editcontact = findViewById(R.id.estab_page_contact);
        editavgprice = findViewById(R.id.estab_page_avg_price);
        editzone = findViewById(R.id.estab_page_zone);
        editcity = findViewById(R.id.estab_page_city);
        editdelivery = findViewById(R.id.estab_page_delivery);

        // fetch categories for spinner and asynchronously fill it
        catUtils = new CategoryUtils();
        catUtils.populateSpinner(EstablishmentSelectedPage.this, editcategory, null);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            editname.setText(bundle.getString(estab.getName()));
            editaddress.setText(bundle.getString(estab.getAddress()));
            editcontact.setText(bundle.getString(estab.getContact()));
            editavgprice.setText(bundle.getString(String.valueOf(estab.getAvg_price())));
            editzone.setText(bundle.getString(estab.getZone()));
            editcity.setText(bundle.getString(estab.getCity()));
            editdelivery.setText(bundle.getString(estab.getName()));

        }
    }


}
