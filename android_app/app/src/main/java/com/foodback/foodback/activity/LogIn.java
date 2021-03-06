package com.foodback.foodback.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.foodback.foodback.R;
import com.foodback.foodback.config.CredentialsEndpoints;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.foodback.foodback.config.FoodbackClient.retrofit;
import static com.foodback.foodback.config.FoodbackClient.setCredentials;
import static com.foodback.foodback.utils.ErrorDisplay.isBad;
import static com.foodback.foodback.utils.ErrorDisplay.isException;
import static com.foodback.foodback.utils.ErrorDisplay.isFailure;

public class LogIn extends AppCompatActivity {

    protected EditText editusername;
    protected EditText editpassword;
    protected Button btnLogin;
    protected TextView linksignup, linkvisitormenu;

    protected CredentialsEndpoints services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        editusername = findViewById(R.id.username);
        editpassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.loginButton);
        linksignup = findViewById(R.id.signup);
        linkvisitormenu = findViewById(R.id.jump_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editusername.getText().toString();
                String password = editpassword.getText().toString();

                //validate inserted values
                if (validateLogin(username, password)) {
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

        linkvisitormenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(LogIn.this, VisitorMenu.class);
                startActivity(i);
            }

        });

    }

    private boolean validateLogin(String username, String password) {
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
        setCredentials(username, password);
        services = retrofit.create(CredentialsEndpoints.class);
        checkIfUser();
    }

    private void checkIfUser() {
        try {
            Call<ResponseBody> call = services.verifyUserCredentials();

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(LogIn.this, R.string.login_success, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent();
                        i.setClass(LogIn.this, UserMenu.class);
                        startActivity(i);
                    } else {
                        checkIfEstablishment();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    isFailure(LogIn.this, t);
                }
            });
        } catch (Exception e) {
            isException(LogIn.this, e);
        }
    }

    private void checkIfEstablishment() {
        try {
            Call<ResponseBody> call = services.verifyEstablishmentCredentials();

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(LogIn.this, R.string.login_success, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent();
                        i.setClass(LogIn.this, EstablishmentMenu.class);
                        startActivity(i);
                    } else {
                        checkIfAdmin();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    isFailure(LogIn.this, t);
                }
            });
        } catch (Exception e) {
            isException(LogIn.this, e);
        }
    }

    private void checkIfAdmin() {
        try {
            Call<ResponseBody> call = services.verifyAdminCredentials();

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(LogIn.this, R.string.login_success, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent();
                        i.setClass(LogIn.this, AdminMenu.class);
                        startActivity(i);
                    } else {
                        isBad(LogIn.this, response);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    isFailure(LogIn.this, t);
                }
            });
        } catch (Exception e) {
            isException(LogIn.this, e);
        }
    }
}