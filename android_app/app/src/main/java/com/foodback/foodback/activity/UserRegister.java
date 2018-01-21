package com.foodback.foodback.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.foodback.foodback.R;
import com.foodback.foodback.config.UserEndpoints;
import com.foodback.foodback.logic.User;

import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.foodback.foodback.config.FoodbackClient.retrofit;
import static com.foodback.foodback.utils.ErrorDisplay.isBad;
import static com.foodback.foodback.utils.ErrorDisplay.isException;
import static com.foodback.foodback.utils.ErrorDisplay.isFailure;


public class UserRegister extends AppCompatActivity {

    protected EditText editname, editaddress, editemail, editcontact,
            editusername, editpassword, editcity, editzone;
    protected Button buttonRegister;
    protected TextView linkestab_signup;
    protected DatePicker editbirth;

    protected String name, address, email, contact, username, password, city, zone;
    protected Date birth;
    protected Boolean premium;

    protected User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        // Initializing Views
        editname = findViewById(R.id.name);
        editaddress = findViewById(R.id.address);
        editemail = findViewById(R.id.email);
        editcontact = findViewById(R.id.contact);
        editusername = findViewById(R.id.username);
        editpassword = findViewById(R.id.password);
        editcity = findViewById(R.id.city);
        editzone = findViewById(R.id.zone);
        editbirth = findViewById(R.id.birth);

        buttonRegister = findViewById(R.id.buttonRegister);

        linkestab_signup = findViewById(R.id.estab_signup);

        // Adding listener to button
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            // confirm register
            register();
            }

        });

        linkestab_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent i = new Intent();
            i.setClass(UserRegister.this, EstablishmentRegister.class);
            startActivity(i);
            }

        });

    }

    private void register() {
        initialize();
        if (!validate()) {
            Toast.makeText(this, "Signup has failed", Toast.LENGTH_SHORT).show();
        } else {
            birth = new Date(editbirth.getYear()-1900, editbirth.getMonth(), editbirth.getDayOfMonth());
            user = new User(username, password, name, email, address, birth, premium, zone, city);
            onRegisterSuccess(user);
        }
    }

    private void initialize() {
        name = editname.getText().toString();
        address = editaddress.getText().toString();
        email = editemail.getText().toString();
        contact = editcontact.getText().toString();
        username = editusername.getText().toString();
        password = editpassword.getText().toString();
        premium = ((CheckBox) findViewById(R.id.premium)).isChecked();
        city = editcity.getText().toString();
        zone = editzone.getText().toString();
    }

    private boolean validate() {
        boolean valid = true;

        // username must have something that not exceeds 32 characters
        if (name.isEmpty() || name.length() > 64) {
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
        if (city.isEmpty()) {
            editcity.setError("Please enter a valid city");
            valid = false;
        }
        if (zone.isEmpty()) {
            editzone.setError("Please enter a valid zone");
            valid = false;
        }

        return valid;
    }

    private void onRegisterSuccess(User user) {
        try {
            UserEndpoints services = retrofit.create(UserEndpoints.class);
            Call<ResponseBody> call = services.createUser(user);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(UserRegister.this, "Registered Successfully.", Toast.LENGTH_SHORT).show();
                        //change activity
                    } else {
                        isBad(UserRegister.this, response);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    isFailure(UserRegister.this, t);
                }
            });
        } catch(Exception e) {
            isException(UserRegister.this, e);
        }
    }

}
