package com.foodback.foodback.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.foodback.foodback.R;
import com.foodback.foodback.config.UserEndpoints;
import com.foodback.foodback.fragment.Homepage;
import com.foodback.foodback.fragment.UserChangeInfo;
import com.foodback.foodback.fragment.UserDelivery;
import com.foodback.foodback.logic.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.foodback.foodback.config.FoodbackClient.retrofit;
import static com.foodback.foodback.config.FoodbackClient.setCredentials;
import static com.foodback.foodback.utils.ErrorDisplay.isBad;
import static com.foodback.foodback.utils.ErrorDisplay.isException;
import static com.foodback.foodback.utils.ErrorDisplay.isFailure;

public class UserMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.user_menu, new Homepage()).commit();

        fillUserInfo();
    }

    private void fillUserInfo() {
        try {
            UserEndpoints services = retrofit.create(UserEndpoints.class);
            Call<User> call = services.getAuthUser();

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.isSuccessful()) {
                        User tmp = response.body();
                        TextView edituser_name = findViewById(R.id.user_name);
                        TextView edituser_email = findViewById(R.id.user_email);

                        edituser_name.setText(tmp.getName());
                        edituser_email.setText(tmp.getEmail());
                    } else {
                        isBad(UserMenu.this, response);
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    isFailure(UserMenu.this, t);
                }
            });
        } catch(Exception e) {
            isException(UserMenu.this, e);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_homepage) {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.user_menu, new Homepage()).commit();
        } else if (id == R.id.nav_feed) {

        } else if (id == R.id.nav_delivery) {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.user_menu, new UserDelivery()).commit();

        } else if (id == R.id.nav_settings) {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.user_menu, new UserChangeInfo()).commit();

        } else if (id == R.id.nav_logout) {
            Intent i = new Intent();
            i.setClass(UserMenu.this, LogIn.class);
            setCredentials(null, null);
            startActivity(i);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //TODO if user is premium do display none to id advert
}
