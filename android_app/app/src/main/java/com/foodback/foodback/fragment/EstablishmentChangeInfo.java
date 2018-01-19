package com.foodback.foodback.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.foodback.foodback.config.CredentialsEndpoints;
import com.foodback.foodback.config.EstablishmentEndpoints;
import com.foodback.foodback.logic.Category;
import com.foodback.foodback.logic.Establishment;
import com.foodback.foodback.utils.CategoryUtils;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.foodback.foodback.config.FoodbackClient.retrofit;
import static com.foodback.foodback.config.FoodbackClient.setCredentials;
import static com.foodback.foodback.utils.ErrorDisplay.isBad;
import static com.foodback.foodback.utils.ErrorDisplay.isException;
import static com.foodback.foodback.utils.ErrorDisplay.isFailure;

public class EstablishmentChangeInfo extends Fragment {

    protected EditText editname, editaddress, editzone, editcity, editemail,
            editcontact, editavgprice, editschedule1, editschedule2,
            editoldpass, editnewpass, editconfpass;
    protected Spinner editcategory;
    protected Button buttonChangeEstab;
    protected CheckBox editdelivery;

    protected String name, category, address, zone, city, email, contact,
            schedule1, schedule2, oldpass, confpass, newpass;
    protected Integer avg_price;
    protected Boolean delivery;

    protected Establishment estab;

    protected int category_id;

    EstablishmentEndpoints dataServices;
    CredentialsEndpoints credentialServices;

    CategoryUtils catUtils;

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
        editoldpass = getView().findViewById(R.id.oldpass);
        editnewpass = getView().findViewById(R.id.newpass);
        editconfpass = getView().findViewById(R.id.confpass);
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

        populateFields();

        catUtils = new CategoryUtils();
    }

    private void changeEstab() {
        initialize();
        if (validateChangesEstab()) {
            category_id = catUtils.nameToID(category);
            if(TextUtils.isEmpty(newpass)) {
                estab = new Establishment(name, category_id, address, zone, city, email,
                        contact, 0, schedule1, schedule2, null, oldpass, delivery);
            } else {
                estab = new Establishment(name, category_id, address, zone, city, email,
                        contact, 0, schedule1, schedule2, null, newpass, delivery);
            }
            verifyOldPassword(estab, oldpass);
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
        oldpass = editoldpass.getText().toString();
        newpass = editnewpass.getText().toString();
        confpass = editconfpass.getText().toString();
        delivery = editdelivery.isChecked();
    }

    private boolean validateChangesEstab() {

        // name must have something that not exceeds 32 characters
        if (name.length() > 64) {
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

    private void populateFields() {
        try {
            dataServices = retrofit.create(EstablishmentEndpoints.class);
            Call<Establishment> call = dataServices.getMyEstablishment();

            call.enqueue(new Callback<Establishment>() {
                @Override
                public void onResponse(Call<Establishment> call, Response<Establishment> response) {
                    if(response.isSuccessful()) {
                        estab = response.body();
                        editname.setText(estab.getName());
                        editaddress.setText(estab.getAddress());
                        editzone.setText(estab.getZone());
                        editcity.setText(estab.getCity());
                        editemail.setText(estab.getEmail());
                        editcontact.setText(estab.getContact());
                        editschedule1.setText(estab.getSchedule1());
                        editschedule2.setText(estab.getSchedule2());
                        if(estab.getDelivery()) editdelivery.setChecked(true);
                        catUtils.populateSpinner(
                                getActivity(),
                                editcategory,
                                estab.getCategory_id()
                        );
                    } else {
                        isBad(getActivity(), response);
                    }
                }

                @Override
                public void onFailure(Call<Establishment> call, Throwable t) {
                    isFailure(getActivity(), t);
                }
            });

        } catch(Exception e) {
            isException(getActivity(), e);
        }

    }

    private void verifyOldPassword(final Establishment estab, final String password) {
        try {
            credentialServices = retrofit.create(CredentialsEndpoints.class);
            //TODO this doesn't verify old password, it just uses Basic Auth normally
            Call<ResponseBody> credentialCall = credentialServices.verifyEstablishmentCredentials();

            credentialCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful()) {
                        changeEstabOnServer(estab, password);
                    } else {
                        editoldpass.setError("Wrong password");
                        isBad(getActivity(), response);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    isFailure(getActivity(), t);
                }
            });
        } catch(Exception e) {
            isException(getActivity(), e);
        }
    }

    private void changeEstabOnServer(Establishment estab, final String password) {
        try {
            Call<ResponseBody> dataCall = dataServices.editTmpEstablishment(estab);


            dataCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(getActivity(),
                                "Establishment updated successfully.",
                                Toast.LENGTH_SHORT).show();
                        if(!TextUtils.isEmpty(newpass)) {
                            changeCredentials(password);
                        } else {
                            //TODO change activity or "remove"/"exit" fragment
                        }
                    } else {
                        isBad(getActivity(), response);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    isFailure(getActivity(), t);
                }
            });
        } catch(Exception e) {
            isException(getActivity(), e);
        }
    }

    private void changeCredentials(final String password) {
        try {
            Call<ResponseBody> credentialCall = credentialServices
                    .updateCredentials(null, password);

            credentialCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(getActivity(),
                                "Credentials updated successfully.",
                                Toast.LENGTH_SHORT).show();
                        setCredentials(null, password);
                        //TODO change activity or "remove"/"exit" fragment
                    } else {
                        isBad(getActivity(), response);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    isFailure(getActivity(), t);
                }
            });
        } catch(Exception e) {
            isException(getActivity(), e);
        }
    }

}
