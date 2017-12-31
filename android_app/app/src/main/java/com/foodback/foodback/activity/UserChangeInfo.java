package com.foodback.foodback.activity;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.foodback.foodback.R;

public class UserChangeInfo extends AppCompatActivity {

    protected EditText editname, editaddress, editzone, editcity, editemail, editcontact, editusername, editpassword;
    protected CheckBox editpremium;
    protected DatePicker editbirth;

    protected Button buttonChangeUser;

    protected String name, address, zone, city, email, contact, username, password, premium;
    protected Integer day, month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_change_info);

        // Initializing Views
        editname = findViewById(R.id.name);
        editaddress = findViewById(R.id.address);
        editemail = findViewById(R.id.email);
        //editzone = findViewById(R.id.zone);
        //editcity = findViewById(R.id.city);
        editcontact = findViewById(R.id.contact);
        editusername = findViewById(R.id.username);
        editpassword = findViewById(R.id.password);
        editbirth = findViewById(R.id.birth);
        editpremium = findViewById(R.id.premium);

        buttonChangeUser = (Button) findViewById(R.id.buttonChangeUser);

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
            Toast.makeText(this, "Changes in User have failed", Toast.LENGTH_SHORT).show();
        } else {
            onChangeUserSuccess();
        }
    }

    private void initialize() {
        name = editname.getText().toString();
        address = editaddress.getText().toString();
        //zone = editzone.getText().toString();
        //city = editcity.getText().toString();
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
        /*if (zone.isEmpty()) {
            editzone.setError("Please enter a valid zone");
            valid = false;
        }
        if (city.isEmpty()) {
            editcity.setError("Please enter a valid city");
            valid = false;
        }*/
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
