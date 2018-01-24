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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.foodback.foodback.R;
import com.foodback.foodback.config.CredentialsEndpoints;
import com.foodback.foodback.config.UserEndpoints;
import com.foodback.foodback.logic.User;

import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.foodback.foodback.config.FoodbackClient.retrofit;
import static com.foodback.foodback.config.FoodbackClient.setCredentials;
import static com.foodback.foodback.utils.ErrorDisplay.isBad;
import static com.foodback.foodback.utils.ErrorDisplay.isException;
import static com.foodback.foodback.utils.ErrorDisplay.isFailure;

public class UserChangeInfo extends Fragment {

    protected EditText editname, editaddress, editzone, editcity,
            editemail, editnewpass, editconfpass;
    protected DatePicker editbirth;
    protected CheckBox editpremium;

    protected Button buttonChangeUser;

    protected String name, address, zone, city, email, username, newpass, confpass;
    protected Date birth;
    protected Boolean premium;

    protected User user;
    UserEndpoints services;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_change_info, container, false);
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
        editnewpass = getView().findViewById(R.id.newpass);
        editconfpass = getView().findViewById(R.id.confpass);
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

        populateFields();
    }

    private void populateFields() {
        try {
            services = retrofit.create(UserEndpoints.class);
            Call<User> call = services.getAuthUser();

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.isSuccessful()) {
                        user = response.body();
                        editname.setText(user.getName());
                        editaddress.setText(user.getAddress());
                        editzone.setText(user.getZone());
                        editcity.setText(user.getCity());
                        editemail.setText(user.getEmail());
                        editbirth.updateDate(
                                user.getBirth().getYear()+1900,
                                user.getBirth().getMonth(),
                                user.getBirth().getDay()
                        );
                        if(user.getPremium())
                            editpremium.setChecked(true);
                    } else {
                        isBad(getActivity(), response);
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    isFailure(getActivity(), t);
                }
            });

        } catch(Exception e) {
            isException(getActivity(), e);
        }
    }

    private void changeUser() {
        initialize();
        if (validateChangesUser()) {
            birth = new Date(editbirth.getYear()-1900, editbirth.getMonth(), editbirth.getDayOfMonth());
            if(TextUtils.isEmpty(newpass)) {
                user = new User(user.getId(), null, null, name, email, address, birth,
                        premium, zone, city);
                onChangeUserSuccess(user, null);
            } else {
                user = new User(user.getId(), null, newpass, name, email, address, birth,
                        premium, zone, city);
                onChangeUserSuccess(user, newpass);
            }
        }
    }

    private void initialize() {
        name = editname.getText().toString();
        address = editaddress.getText().toString();
        zone = editzone.getText().toString();
        city = editcity.getText().toString();
        email = editemail.getText().toString();
        newpass = editnewpass.getText().toString();
        confpass = editconfpass.getText().toString();
        premium = ((CheckBox) getView().findViewById(R.id.premium)).isChecked();
    }

    private boolean validateChangesUser() {
        boolean valid = true;

        // username must have something that not exceeds 32 characters
        if (name.length() == 0 || name.length() > 32) {
            editname.setError("Please enter a valid name (max size of 32 characters)");
            valid = false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editemail.setError("Please enter a valid email");
            valid = false;
        }
        if (!newpass.equals(confpass)) {
            editnewpass.setError("Passwords don't match");
            editconfpass.setError("Passwords don't match");
            return false;
        }

        return valid;
    }


    private void onChangeUserSuccess(User user, final String password) {
        try {
            services = retrofit.create(UserEndpoints.class);
            Call<ResponseBody> call = services.changeUser(user);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(getActivity(), "User info updated successfully.", Toast.LENGTH_SHORT).show();
                        if(!TextUtils.isEmpty(newpass)) {
                            changeCredentials(password);
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
            CredentialsEndpoints services = retrofit.create(CredentialsEndpoints.class);
            Call<ResponseBody> call = services.updateCredentials(null, password);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(getActivity(),
                                "Credentials updated successfully.",
                                Toast.LENGTH_SHORT).show();
                        setCredentials(null, password);
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
