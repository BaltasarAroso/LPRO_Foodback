package com.foodback.foodback.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.foodback.foodback.R;
import com.foodback.foodback.config.EstablishmentEndpoints;
import com.foodback.foodback.logic.Establishment;
import com.foodback.foodback.utils.CategoryUtils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.foodback.foodback.config.FoodbackClient.retrofit;
import static com.foodback.foodback.utils.ErrorDisplay.isBad;
import static com.foodback.foodback.utils.ErrorDisplay.isException;
import static com.foodback.foodback.utils.ErrorDisplay.isFailure;

public class EstablishmentRegister extends AppCompatActivity {

    protected EditText editname, editaddress, editemail, editcontact, editavgprice,
            editusername, editpassword, editzone, editcity;
    protected Spinner editcategory;
    protected Button buttonRegisterEstab;
    protected CheckBox editdelivery;

    protected String name, category, address, email, contact, username, password, zone, city;
    protected Integer avg_price;
    protected Boolean delivery;

    protected Establishment estab;

    CategoryUtils catUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establishment_register);

        // Initializing Views
        editname = findViewById(R.id.name);
        editcategory = findViewById(R.id.category);
        editaddress = findViewById(R.id.address);
        editemail = findViewById(R.id.email);
        editcontact = findViewById(R.id.contact);
        editavgprice = findViewById(R.id.avg_price);
        editusername = findViewById(R.id.username);
        editpassword = findViewById(R.id.password);
        editzone = findViewById(R.id.zone);
        editcity = findViewById(R.id.city);
        editdelivery = findViewById(R.id.delivery);

        buttonRegisterEstab = findViewById(R.id.buttonRegisterEstab);

        // Adding listener to button
        buttonRegisterEstab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // confirm register
                register();
            }
        });

        // fetch categories for spinner and asynchronously fill it
        catUtils = new CategoryUtils();
        catUtils.populateSpinner(EstablishmentRegister.this, editcategory, null);
    }

    private void register() {
        initialize();
        if (validate()) {
            estab = new Establishment(name, category, address, zone, city, email,
                    contact, 0, null, null, username, password, delivery);
            onRegisterSuccess(estab);
        }
    }

    private void initialize() {
        name = editname.getText().toString();
        category = editcategory.getSelectedItem().toString();
        address = editaddress.getText().toString();
        email = editemail.getText().toString();
        contact = editcontact.getText().toString();
        avg_price = Integer.parseInt(editavgprice.getText().toString());
        username = editusername.getText().toString();
        password = editpassword.getText().toString();
        zone = editzone.getText().toString();
        city = editcity.getText().toString();
        delivery = editdelivery.isChecked();
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

        // need to add more parameters of validation in these
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

    private void onRegisterSuccess(Establishment estab) {
        try {
            EstablishmentEndpoints services = retrofit.create(EstablishmentEndpoints.class);
            Call<ResponseBody> call = services.createEstablishment(estab);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(EstablishmentRegister.this,
                                "Registered Successfully.",
                                Toast.LENGTH_SHORT).show();
                        Intent i = new Intent();
                        i.setClass(EstablishmentRegister.this, LogIn.class);
                        startActivity(i);
                    } else {
                        isBad(EstablishmentRegister.this, response);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    isFailure(EstablishmentRegister.this, t);
                }
            });
        } catch (Exception e) {
            isException(EstablishmentRegister.this, e);
        }
    }


}
