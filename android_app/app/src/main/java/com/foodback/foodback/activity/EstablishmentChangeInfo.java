package com.foodback.foodback.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.foodback.foodback.R;

public class EstablishmentChangeInfo extends Fragment {

    protected EditText editname, editaddress, editzone, editcity, editemail, editcontact, editusername, editpassword;
    protected Spinner editcategory;
    protected CheckBox editdelivery;

    protected Button buttonChangeEstab;

    protected String name, category, address, zone, city, email, contact, username, password, delivery;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.activity_establishment_change_info, container, false);
        return myView;
    }

    @Override
    public void onStart() {
        super.onStart();

        // Initializing Views
        editname = getView().findViewById(R.id.name);
        editcategory = getView().findViewById(R.id.category);
        editaddress = getView().findViewById(R.id.address);
        editemail = getView().findViewById(R.id.email);
        editzone = getView().findViewById(R.id.zone);
        editcity = getView().findViewById(R.id.city);
        editcontact = getView().findViewById(R.id.contact);
        editusername = getView().findViewById(R.id.username);
        editpassword = getView().findViewById(R.id.password);
        editdelivery = getView().findViewById(R.id.delivery);

        buttonChangeEstab = getView().findViewById(R.id.buttonChangeEstab);

        // Adding listener to button
        buttonChangeEstab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // confirm register
                changeEstab();
            }

        });
    }

    private void changeEstab() {
        initialize();
        if (!validateChangesEstab()) {
            Toast.makeText(getActivity(), "Changes in Establishment have failed", Toast.LENGTH_SHORT).show();
        } else {
            onChangeEstabSuccess();
        }
    }

    private void initialize() {
        name = editname.getText().toString();
        category = editcategory.getSelectedItem().toString();
        address = editaddress.getText().toString();
        zone = editzone.getText().toString();
        city = editcity.getText().toString();
        email = editemail.getText().toString();
        contact = editcontact.getText().toString();
        username = editusername.getText().toString();
        password = editpassword.getText().toString();
        delivery = editdelivery.getText().toString();
    }

    private boolean validateChangesEstab() {
        boolean valid = true;

        // username must have something that not exceeds 32 characters
        if (name.isEmpty() || name.length() > 32) {
            editname.setError("Please enter a valid name");
            valid = false;
        }
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editemail.setError("Please enter a valid email");
            valid = false;
        }

        // is needed to add more parameters of validation in this ones
        if (address.isEmpty()) {
            editaddress.setError("Please enter a valid address");
            valid = false;
        }
        if (zone.isEmpty()) {
            editzone.setError("Please enter a valid zone");
            valid = false;
        }
        if (city.isEmpty()) {
            editcity.setError("Please enter a valid city");
            valid = false;
        }
        if (contact.isEmpty()) {
            editcontact.setError("Please enter a valid contact");
            valid = false;
        }
        if (username.isEmpty()) {
            editusername.setError("Please enter a valid username");
            valid = false;
        }
        if (password.isEmpty()) {
            editpassword.setError("Please enter a valid password");
            valid = false;
        }

        return valid;
    }

    private void onChangeEstabSuccess() {
        //TODO change the User parameters on success
    }

}
