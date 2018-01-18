package com.foodback.foodback.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.foodback.foodback.R;
import com.foodback.foodback.config.CredentialsEndpoints;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.foodback.foodback.config.FoodbackClient.retrofit;
import static com.foodback.foodback.utils.ErrorDisplay.isException;
import static com.foodback.foodback.utils.ErrorDisplay.isFailure;

public class ConnectServer extends AppCompatActivity {

    public CredentialsEndpoints services;
    Button connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_server);

        try {
            services = retrofit.create(CredentialsEndpoints.class);
            testConnection();

            connect = findViewById(R.id.btnConnect);
            connect.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    testConnection();
                    connect.setVisibility(View.INVISIBLE);
                }
            });
        } catch(Exception e) {
            Log.e("DEBUG",Log.getStackTraceString(e));
            Toast.makeText(ConnectServer.this, "Unexpected error.", Toast.LENGTH_SHORT).show();
            connect.setVisibility(View.VISIBLE);
        }

    }

    public void testConnection() {
        try{
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
//                    Log.e("DEBUG",Log.getStackTraceString(t));
//                    Toast.makeText(ConnectServer.this, R.string.error_server_response, Toast.LENGTH_SHORT).show();
                    isFailure(ConnectServer.this, t);
                    connect.setVisibility(View.VISIBLE);
                }
            });

        } catch(Exception e) {
//            Log.e("DEBUG",Log.getStackTraceString(e));
//            Toast.makeText(ConnectServer.this, R.string.error_unexpected, Toast.LENGTH_SHORT).show();
            isException(ConnectServer.this, e);
            connect.setVisibility(View.VISIBLE);
        }
    }
}