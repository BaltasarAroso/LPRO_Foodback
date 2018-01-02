package com.foodback.foodback.fragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.foodback.foodback.R;

public class UserChangeInfo extends Fragment {

    protected EditText editname, editaddress, editzone, editcity, editemail, editcontact, editusername, editpassword;
    protected CheckBox editpremium;
    protected DatePicker editbirth;

    protected Button buttonChangeUser;

    protected String name, address, zone, city, email, contact, username, password, premium;
    protected Integer day, month, year;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_user_change_info, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        // Initializing Views
        editname = getView().findViewById(R.id.name);
        editaddress = getView().findViewById(R.id.address);
        editemail = getView().findViewById(R.id.email);
        editzone = getView().findViewById(R.id.zone);
        editcity = getView().findViewById(R.id.city);
        editcontact = getView().findViewById(R.id.contact);
        editusername = getView().findViewById(R.id.username);
        editpassword = getView().findViewById(R.id.password);
        editbirth = getView().findViewById(R.id.birth);
        editpremium = getView().findViewById(R.id.premium);

        buttonChangeUser = getView().findViewById(R.id.buttonChangeUser);

        // Adding listener to button
        buttonChangeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // confirm register
                changeUser();
            }

        });
    }

    private void changeUser() {
        initialize();
        if (!validateChangesUser()) {
            Toast.makeText(getActivity(), "Changes in User have failed", Toast.LENGTH_SHORT).show();
        } else {
            onChangeUserSuccess();
        }
    }

    private void initialize() {
        name = editname.getText().toString();
        address = editaddress.getText().toString();
        zone = editzone.getText().toString();
        city = editcity.getText().toString();
        email = editemail.getText().toString();
        contact = editcontact.getText().toString();
        username = editusername.getText().toString();
        password = editpassword.getText().toString();
        premium = editpremium.getText().toString();

        day = editbirth.getDayOfMonth();
        month = editbirth.getMonth() + 1;
        year = editbirth.getYear();
    }

    private boolean validateChangesUser() {
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


    private void onChangeUserSuccess() {
        //TODO change the Estab parameters on success
    }
}
