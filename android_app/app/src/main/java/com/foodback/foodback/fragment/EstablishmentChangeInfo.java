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
import com.foodback.foodback.config.CredentialsEndpoints;
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

    protected EditText editname, editaddress, editzone, editcity, editemail,
            editcontact, editavgprice, editschedule1, editschedule2, editusername,
            editoldpass, editnewpass, editconfpass;
    protected Spinner editcategory;
    protected Button buttonChangeEstab;

    protected String name, category, address, zone, city, email, contact, schedule1, schedule2, username, password;
    protected Integer avg_price;
    protected Boolean delivery;

    protected Establishment estab;

    List<Category> categoryList;
    protected int category_id;

    CredentialsEndpoints credentialServices;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_establishment_change_info, container, false);
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
        editschedule1 = getView().findViewById(R.id.schedule1);
        editschedule2 = getView().findViewById(R.id.schedule2);
//        editavgprice = getView().findViewById(R.id.avg_price);
        //TODO remove avg price from .java and .xml
        editusername = getView().findViewById(R.id.username);
        editoldpass = getView().findViewById(R.id.oldpass);
        editnewpass = getView().findViewById(R.id.newpass);
        editconfpass = getView().findViewById(R.id.confpass);

        buttonChangeEstab = getView().findViewById(R.id.buttonChangeEstab);

        // Adding listener to button
        buttonChangeEstab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // confirm register
                changeEstab();
            }

        });

        // fetch categories for spinner and asynchronously fill it
        populateSpinner();
    }

    private void changeEstab() {
        initialize();
        if (validateChangesEstab()) {
            for(Category x: categoryList) {
                if(category.equals(x.getName())) {
                    category_id = x.getId();
                    break;
                }
            }
            estab = new Establishment(name, category_id, address, zone, city, email,
                    contact, 0, schedule1, schedule2, username, password, delivery);
            verifyOldPassword(estab, password);
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
//        avg_price = Integer.parseInt(editavgprice.getText().toString());;
        schedule1 = editschedule1.getText().toString();
        schedule2 = editschedule2.getText().toString();
        username = editusername.getText().toString();
        password = editoldpass.getText().toString();
        delivery = ((CheckBox) getView().findViewById(R.id.delivery)).isChecked();
    }

    private boolean validateChangesEstab() {

        // username must have something that not exceeds 32 characters
        if (name.length() > 32) {
            editname.setError("Please enter a valid name (max size of 32 characters)");
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editemail.setError("Please enter a valid email");
            return false;
        }
        if (editnewpass.toString().equals(editconfpass.toString())) {
            editnewpass.setError("Passwords don't match");
            editconfpass.setError("Passwords don't match");
            return false;
        }

        return true;
    }

    public void populateSpinner(){
        try {
            EstablishmentEndpoints services = retrofit.create(EstablishmentEndpoints.class);
            Call<List<Category>> call = services.getAllCategories();

            call.enqueue(new Callback<List<Category>>() {
                @Override
                public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                    if(response.isSuccessful()) {
                        categoryList = response.body();
                        if(categoryList.size() == 0) {
                            Toast.makeText(getActivity(),
                                    "No establishment categories found.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            List<String> spinnerArray = new ArrayList<>();

                            for(Category x: categoryList) {
                                spinnerArray.add(x.getName());
                                Log.e("DEBUG", "x.getName(): " + x.getName());
                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                                    getActivity(),
                                    android.R.layout.simple_spinner_item,
                                    spinnerArray);

                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            editcategory.setAdapter(adapter);
                        }
                    } else {
                        APIError apiError = ErrorUtils.parseError(response);
                        Toast.makeText(getActivity(),
                                apiError.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<Category>> call, Throwable t) {
                    Toast.makeText(getActivity(),
                            "Error getting server response.",
                            Toast.LENGTH_SHORT).show();
                }
            });
        } catch(Exception e) {
            Log.e("DEBUG", Log.getStackTraceString(e));
            Toast.makeText(getActivity(),
                    "The program has encountered an error.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void verifyOldPassword(final Establishment estab, final String password) {
        try {
            credentialServices = retrofit.create(CredentialsEndpoints.class);
            Call<ResponseBody> credentialCall = credentialServices.verifyEstablishmentCredentials();

            credentialCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(getActivity(),
                                "Establishment updated successfully.",
                                Toast.LENGTH_SHORT).show();
                        changeEstabOnServer(estab, password);
                    } else {
                        editoldpass.setError("Wrong password");

                        APIError apiError = ErrorUtils.parseError(response);
                        Toast.makeText(getActivity(),
                                apiError.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("DEBUG",Log.getStackTraceString(t));
                    Toast.makeText(getActivity(),
                            "Error getting server response.",
                            Toast.LENGTH_SHORT).show();
                }
            });
        } catch(Exception e) {
            Log.e("DEBUG",Log.getStackTraceString(e));
            Toast.makeText(getActivity(),
                    "Unexpected error.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void changeEstabOnServer(Establishment estab, final String password) {
        try {
            EstablishmentEndpoints dataServices = retrofit.create(EstablishmentEndpoints.class);
            Call<ResponseBody> dataCall = dataServices.editTmpEstablishment(estab);


            dataCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(getActivity(),
                                "Establishment updated successfully.",
                                Toast.LENGTH_SHORT).show();
                        changeCredentials(password);
                    } else {
                        APIError apiError = ErrorUtils.parseError(response);
                        Toast.makeText(getActivity(),
                                apiError.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("DEBUG",Log.getStackTraceString(t));
                    Toast.makeText(getActivity(),
                            "Error getting server response.",
                            Toast.LENGTH_SHORT).show();
                }
            });
        } catch(Exception e) {
            Log.e("DEBUG",Log.getStackTraceString(e));
            Toast.makeText(getActivity(),
                    "Unexpected error.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void changeCredentials(String password) {
        try {
            Call<ResponseBody> credentialCall = credentialServices
                    .updateCredentials(null, password);

            credentialCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(getActivity(),
                                "Establishment updated successfully.",
                                Toast.LENGTH_SHORT).show();
                        //TODO change activity
                    } else {
                        APIError apiError = ErrorUtils.parseError(response);
                        Toast.makeText(getActivity(),
                                apiError.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("DEBUG",Log.getStackTraceString(t));
                    Toast.makeText(getActivity(),
                            "Error getting server response.",
                            Toast.LENGTH_SHORT).show();
                }
            });
        } catch(Exception e) {
            Log.e("DEBUG",Log.getStackTraceString(e));
            Toast.makeText(getActivity(),
                    "Unexpected error.",
                    Toast.LENGTH_SHORT).show();
        }
    }

}
