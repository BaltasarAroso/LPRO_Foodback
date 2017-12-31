package com.foodback.foodback.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.foodback.foodback.R;

public class EstablishmentChangeInfos extends AppCompatActivity {

    protected EditText editname, editaddress, editemail, editcontact, editusername, editpassword;
    protected Spinner editcategory;

    protected Button buttonRegisterEstab;

    protected String name, category, address, email, contact, username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establishment_change_infos);
    }
}
