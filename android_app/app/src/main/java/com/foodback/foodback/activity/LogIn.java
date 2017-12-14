package com.foodback.foodback.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.foodback.foodback.R;
import com.foodback.foodback.config.Controller;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        final Button testButton = (Button) findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try{
                    Controller controller = new Controller();
                    controller.start();
                } catch(Exception e) {
                    System.out.println("\nfocked, mate\n");
                }
            }
        });

    }
}
