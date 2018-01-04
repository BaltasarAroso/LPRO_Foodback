package com.foodback.foodback.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.foodback.foodback.R;
import com.foodback.foodback.activity.EstablishmentRegister;
import com.foodback.foodback.config.EstablishmentEndpoints;
import com.foodback.foodback.logic.Category;
import com.foodback.foodback.logic.Establishment;
import com.foodback.foodback.utils.APIError;
import com.foodback.foodback.utils.ErrorUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.foodback.foodback.config.FoodbackClient.retrofit;

public class EstablishmentChangeInfo extends Fragment {

    protected EditText editname, editaddress, editzone, editcity, editemail, editcontact, editusername, editpassword;
    protected Spinner editcategory;
    protected Button buttonChangeEstab;

    protected String name, category, address, zone, city, email, contact, username, password;
    protected Boolean delivery;

    protected Establishment estab;

    List<Category> categoryList;
    protected int category_id;

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

        buttonChangeEstab = getView().findViewById(R.id.buttonChangeEstab);

        // Adding listener to button
        buttonChangeEstab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // confirm register
                changeEstab();
            }

        });

//        // fetch categories for spinner and asynchronously fill it
//        populateSpinner();
    }

    private void changeEstab() {
        initialize();
        if (validateChangesEstab()) {
//            for(Category x: categoryList) {
//                if(category.equals(x.getName())) {
//                    category_id = x.getId();
//                    break;
//                }
//            }
//            estab = new Establishment(name, category_id, address, zone, city, email,
//                    contact, username, password, delivery);
            onChangeEstabSuccess(estab);
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
        delivery = ((CheckBox) getView().findViewById(R.id.delivery)).isChecked();
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

    public void populateSpinner(){
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
//                            Toast.makeText(getActivity(),
//                                    "No establishment categories found.",
//                                    Toast.LENGTH_SHORT).show();
//                        } else {
//                            List<String> spinnerArray = new ArrayList<String>();
//
//                            for(Category x: categoryList) {
//                                spinnerArray.add(x.getName());
//                                Log.e("DEBUG", "x.getName(): " + x.getName());
//                            }
//
//                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                                    getActivity(),
//                                    android.R.layout.simple_spinner_item,
//                                    spinnerArray);
//
//                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                            editcategory.setAdapter(adapter);
//                        }
//                    } else {
//                        APIError apiError = ErrorUtils.parseError(response);
//                        Toast.makeText(getActivity(),
//                                apiError.getMessage(),
//                                Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<List<Category>> call, Throwable t) {
//                    Toast.makeText(getActivity(),
//                            "Error getting server response.",
//                            Toast.LENGTH_SHORT).show();
//                }
//            });
//        } catch(Exception e) {
//            Log.e("DEBUG", Log.getStackTraceString(e));
//            Toast.makeText(getActivity(),
//                    "The program has encountered an error.",
//                    Toast.LENGTH_SHORT).show();
//        }
    }

    private void onChangeEstabSuccess(Establishment estab) {
//        try {
//            EstablishmentEndpoints services = retrofit.create(EstablishmentEndpoints.class);
//            Call<ResponseBody> call = services.editEstablishment(estab);
//
//            call.enqueue(new Callback<ResponseBody>() {
//                @Override
//                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                    if(response.isSuccessful()) {
//                        Toast.makeText(getActivity(),
//                                "Establishment updated successfully.",
//                                Toast.LENGTH_SHORT).show();
//                        //change activity
//                    } else {
//                        APIError apiError = ErrorUtils.parseError(response);
//                        Toast.makeText(getActivity(),
//                                apiError.getMessage(),
//                                Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponseBody> call, Throwable t) {
//                    Log.e("DEBUG",Log.getStackTraceString(t));
//                    Toast.makeText(getActivity(),
//                            "Error getting server response.",
//                            Toast.LENGTH_SHORT).show();
//                }
//            });
//        } catch(Exception e) {
//            Log.e("DEBUG",Log.getStackTraceString(e));
//            Toast.makeText(getActivity(),
//                    "Unexpected error.",
//                    Toast.LENGTH_SHORT).show();
//        }
    }

}
