package com.foodback.foodback.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.foodback.foodback.config.CredentialsEndpoints;
//import com.foodback.foodback.utils.APIError;
//import com.foodback.foodback.utils.ErrorUtils;

import com.foodback.foodback.config.FoodbackClient;
import com.foodback.foodback.R;
import com.foodback.foodback.utils.APIError;
import com.foodback.foodback.utils.ErrorUtils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.foodback.foodback.config.FoodbackClient.retrofit;
import static com.foodback.foodback.config.FoodbackClient.setCredentials;

public class LogIn extends AppCompatActivity {

    protected EditText editusername;
    protected EditText editpassword;
    protected Button btnLogin;
    protected TextView linksignup;

    protected CredentialsEndpoints services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        editusername = findViewById(R.id.username);
        editpassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.loginButton);
        linksignup = findViewById(R.id.signup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editusername.getText().toString();
                String password = editpassword.getText().toString();

                //validate inserted values
                if(validateLogin(username, password)){
                    tryLogin(username, password);
                }
            }

        });

        linksignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(LogIn.this, UserRegister.class);
                startActivity(i);
            }

        });
    }

    private boolean validateLogin (String username, String password) {
        if (username == null || username.trim().length() == 0) {
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password == null || password.trim().length() == 0) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void tryLogin(String username, String password) {
        try {
            setCredentials(username, password);
            new FoodbackClient();

            services = retrofit.create(CredentialsEndpoints.class);
            Call<ResponseBody> call = services.verifyUserCredentials();

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(LogIn.this, "Login successful!", Toast.LENGTH_SHORT).show();
                        //change activity
                    } else {
                        APIError apiError = ErrorUtils.parseError(response);
                        Toast.makeText(LogIn.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
//                        Toast.makeText(LogIn.this, response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("DEBUG",Log.getStackTraceString(t));
                    Toast.makeText(LogIn.this, "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
            });
        } catch(Exception e) {
            Log.e("DEBUG",Log.getStackTraceString(e));
            Toast.makeText(LogIn.this, "An error occurred.", Toast.LENGTH_SHORT).show();
        }
    }

}