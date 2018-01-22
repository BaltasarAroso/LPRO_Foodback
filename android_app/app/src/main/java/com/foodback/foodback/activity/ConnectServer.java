package com.foodback.foodback.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.foodback.foodback.R;
import com.foodback.foodback.config.CredentialsEndpoints;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.foodback.foodback.config.FoodbackClient.retrofit;
import static com.foodback.foodback.config.FoodbackClient.setBaseUrl;
import static com.foodback.foodback.utils.ErrorDisplay.isException;
import static com.foodback.foodback.utils.ErrorDisplay.isFailure;

public class ConnectServer extends AppCompatActivity {

    public CredentialsEndpoints services;
    Button connect;
    EditText edit_ipConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_server);

        try {
            testConnection();

            edit_ipConnect = findViewById(R.id.ipConnect);
            connect = findViewById(R.id.btnConnect);
            connect.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    setBaseUrl(edit_ipConnect.getText().toString());
                    testConnection();
                    connect.setVisibility(View.INVISIBLE);
                    edit_ipConnect.setVisibility(View.INVISIBLE);
                }
            });
        } catch(Exception e) {
            isException(ConnectServer.this, e);
            connect.setVisibility(View.VISIBLE);
            edit_ipConnect.setVisibility(View.VISIBLE);
        }

    }

    public void testConnection() {
        try{
            services = retrofit.create(CredentialsEndpoints.class);
            Call<ResponseBody> call = services.checkConnection();

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Intent i = new Intent();
                    i.setClass(ConnectServer.this, LogIn.class);
                    startActivity(i);
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    isFailure(ConnectServer.this, t);
                    connect.setVisibility(View.VISIBLE);
                    edit_ipConnect.setVisibility(View.VISIBLE);
                }
            });

        } catch(Exception e) {
            isException(ConnectServer.this, e);
            connect.setVisibility(View.VISIBLE);
            edit_ipConnect.setVisibility(View.VISIBLE);
        }
    }
}