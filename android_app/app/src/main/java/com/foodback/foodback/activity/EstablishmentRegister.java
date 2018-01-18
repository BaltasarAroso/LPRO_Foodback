package com.foodback.foodback.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.foodback.foodback.R;
import com.foodback.foodback.config.EstablishmentEndpoints;
import com.foodback.foodback.logic.Category;
import com.foodback.foodback.logic.Establishment;
import com.foodback.foodback.utils.APIError;
import com.foodback.foodback.utils.CategoryUtils;
import com.foodback.foodback.utils.ErrorUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.foodback.foodback.config.FoodbackClient.retrofit;
import static com.foodback.foodback.utils.ErrorDisplay.isBad;
import static com.foodback.foodback.utils.ErrorDisplay.isException;
import static com.foodback.foodback.utils.ErrorDisplay.isFailure;

public class EstablishmentRegister extends AppCompatActivity  {

    protected EditText editname, editaddress, editemail, editcontact,
            editusername, editpassword, editzone, editcity;
    protected Spinner editcategory;
    protected Button buttonRegisterEstab;
    protected CheckBox editdelivery;

    protected String name, category, address, email, contact, username, password, zone, city;
    protected Boolean delivery;

    protected Establishment estab;

    List<Category> categoryList;
    protected int category_id;

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
//        populateSpinner();
        //TODO: test
        catUtils = new CategoryUtils();
        catUtils.populateSpinner(EstablishmentRegister.this, editcategory, null);
    }

    private void register() {
        initialize();
        if (validate()) {
//            for(Category x: categoryList) {
//                if(category.equals(x.getName())) {
//                    category_id = x.getId();
//                    break;
//                }
//            }
            category_id = catUtils.nameToID(category);
            estab = new Establishment(name, category_id, address, zone, city, email,
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

//    public void populateSpinner(){
//        try {
//            EstablishmentEndpoints services = retrofit.create(EstablishmentEndpoints.class);
//            Call<List<Category>> call = services.getAllCategories();
//
//            call.enqueue(new Callback<List<Category>>() {
//                @Override
//                public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
//                    if(response.isSuccessful()) {
//                        categoryList = response.body();
//                        if(categoryList.size() == 0) {
//                            Toast.makeText(EstablishmentRegister.this,
//                                    "No establishment categories found.",
//                                    Toast.LENGTH_SHORT).show();
//                        } else {
//                            List<String> spinnerArray = new ArrayList<>();
//
//                            for(Category x: categoryList) {
//                                spinnerArray.add(x.getName());
//                            }
//
//                            ArrayAdapter<String> adapter = new ArrayAdapter<>(
//                                    EstablishmentRegister.this,
//                                    android.R.layout.simple_spinner_item,
//                                    spinnerArray);
//
//                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                            editcategory.setAdapter(adapter);
//                        }
//                    } else {
////                        APIError apiError = ErrorUtils.parseError(response);
////                        Toast.makeText(EstablishmentRegister.this,
////                                apiError.getMessage(),
////                                Toast.LENGTH_SHORT).show();
//                        isBad(EstablishmentRegister.this, response);
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<List<Category>> call, Throwable t) {
////                    Toast.makeText(EstablishmentRegister.this,
////                            R.string.error_server_response,
////                            Toast.LENGTH_SHORT).show();
//                    isFailure(EstablishmentRegister.this, t);
//                }
//            });
//        } catch(Exception e) {
////            Log.e("DEBUG", Log.getStackTraceString(e));
////            Toast.makeText(EstablishmentRegister.this,
////                    R.string.error_unexpected,
////                    Toast.LENGTH_SHORT).show();
//            isException(EstablishmentRegister.this, e);
//        }
//    }

    private void onRegisterSuccess(Establishment estab) {
        try {
            EstablishmentEndpoints services = retrofit.create(EstablishmentEndpoints.class);
            Call<ResponseBody> call = services.createEstablishment(estab);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(EstablishmentRegister.this,
                                "Registered Successfully.",
                                Toast.LENGTH_SHORT).show();
                        //change activity
                    } else {
//                        APIError apiError = ErrorUtils.parseError(response);
//                        Toast.makeText(EstablishmentRegister.this,
//                                apiError.getMessage(),
//                                Toast.LENGTH_SHORT).show();
                        isBad(EstablishmentRegister.this, response);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
//                    Log.e("DEBUG",Log.getStackTraceString(t));
//                    Toast.makeText(EstablishmentRegister.this,
//                            R.string.error_server_response,
//                            Toast.LENGTH_SHORT).show();
                    isFailure(EstablishmentRegister.this, t);
                }
            });
        } catch(Exception e) {
//            Log.e("DEBUG",Log.getStackTraceString(e));
//            Toast.makeText(EstablishmentRegister.this,
//                    R.string.error_unexpected,
//                    Toast.LENGTH_SHORT).show();
            isException(EstablishmentRegister.this, e);
        }
    }


}
