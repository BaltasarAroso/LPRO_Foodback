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
import com.foodback.foodback.activity.UserRegister;
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

public class UserChangeInfo extends Fragment {

    protected EditText editname, editaddress, editzone, editcity, editemail, editpassword;
    protected DatePicker editbirth;

    protected Button buttonChangeUser;

    protected String name, address, zone, city, email, username, password;
    protected Date birth;
    protected Boolean premium;

    protected User user;

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
        editpassword = getView().findViewById(R.id.password);
        editbirth = getView().findViewById(R.id.birth);

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
            birth = new Date(editbirth.getYear()-1900, editbirth.getMonth(), editbirth.getDayOfMonth());
            user = new User(name, email, address, birth, premium, zone, city, password);
            onChangeUserSuccess(user);
        }
    }

    private void initialize() {
        name = editname.getText().toString();
        address = editaddress.getText().toString();
        zone = editzone.getText().toString();
        city = editcity.getText().toString();
        email = editemail.getText().toString();
        password = editpassword.getText().toString();
        premium = ((CheckBox) getView().findViewById(R.id.premium)).isChecked();
    }

    private boolean validateChangesUser() {
        boolean valid = true;

        // username must have something that not exceeds 32 characters
        if (name.length() > 64) {
            editname.setError("Please enter a valid name (max size of 32 characters)");
            valid = false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editemail.setError("Please enter a valid email");
            valid = false;
        }

        return valid;
    }


    private void onChangeUserSuccess(User user) {
        try {
            UserEndpoints services = retrofit.create(UserEndpoints.class);
            Call<ResponseBody> call = services.changeUser(user);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(getActivity(), "Registered Successfully.", Toast.LENGTH_SHORT).show();

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
