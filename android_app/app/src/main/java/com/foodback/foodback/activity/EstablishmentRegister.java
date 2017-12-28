package com.foodback.foodback.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.foodback.foodback.R;

public class EstablishmentRegister extends AppCompatActivity  {

    protected EditText editname, editaddress, editemail, editcontact, editusername, editpassword;
    protected Spinner editcategory;

    protected Button buttonRegisterEstab;

    protected String name, category, address, email, contact, username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establishment_register);

        // Initializing Views
        editname = (EditText) findViewById(R.id.editname);
        editcategory = (Spinner) findViewById(R.id.editcategory);
        editaddress = (EditText) findViewById(R.id.editaddress);
        editemail = (EditText) findViewById(R.id.editemail);
        editcontact = (EditText) findViewById(R.id.editcontact);
        editusername = (EditText) findViewById(R.id.editusername);
        editpassword = (EditText) findViewById(R.id.editpassword);

        buttonRegisterEstab = (Button) findViewById(R.id.buttonRegisterEstab);

        // Adding listener to button
        buttonRegisterEstab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // confirm register
                register();
            }

        });
    }

    private void register() {
        initialize();
        if (!validate()) {
            Toast.makeText(this, "Signup has failed", Toast.LENGTH_SHORT).show();
        } else {
            onRegisterSuccess();
        }
    }

    private void initialize() {
        name = editname.getText().toString();
        category = editcategory.getSelectedItem().toString();
        address = editaddress.getText().toString();
        email = editemail.getText().toString();
        contact = editcontact.getText().toString();
        username = editusername.getText().toString();
        password = editpassword.getText().toString();
    }

    private boolean validate() {
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

    private void onRegisterSuccess() {
        // TODO register the user on success
    }


}
