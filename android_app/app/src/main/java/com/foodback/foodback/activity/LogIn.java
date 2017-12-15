package com.foodback.foodback.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.foodback.foodback.config.RetrofitClient;
import com.foodback.foodback.config.FoodbackInterface;
import com.foodback.foodback.R;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogIn extends AppCompatActivity {

    protected EditText editusername;
    protected EditText editpassword;
    protected Button btnLogin;
    RetrofitClient retrofitClient;
    FoodbackInterface services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        editusername = (EditText) findViewById(R.id.username);
        editpassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.loginButton);

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
            retrofitClient = new RetrofitClient();
            retrofitClient.setCredentials(username, password);
            retrofitClient.startup();

            services = retrofitClient.retrofit.create(FoodbackInterface.class);
            Call<ResponseBody> call = services.verifyUserCredentials();

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(LogIn.this, "Login successful!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LogIn.this, "Error! Please try again!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(LogIn.this, "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
            });
        } catch(Exception e) {
            e.printStackTrace();
            Toast.makeText(LogIn.this, "An error occured.", Toast.LENGTH_SHORT).show();
        }

    }

}
