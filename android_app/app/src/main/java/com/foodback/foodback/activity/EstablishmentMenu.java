package com.foodback.foodback.activity;

import android.content.Intent;
import android.os.Bundle;
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
import com.foodback.foodback.config.EstablishmentEndpoints;
import com.foodback.foodback.config.UserEndpoints;
import com.foodback.foodback.fragment.EstablishmentChangeInfo;
import com.foodback.foodback.fragment.EstablishmentDelivery;
import com.foodback.foodback.fragment.EstablishmentPage;
import com.foodback.foodback.fragment.Homepage;
import com.foodback.foodback.logic.Establishment;
import com.foodback.foodback.logic.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.foodback.foodback.config.FoodbackClient.retrofit;
import static com.foodback.foodback.utils.ErrorDisplay.isBad;
import static com.foodback.foodback.utils.ErrorDisplay.isException;
import static com.foodback.foodback.utils.ErrorDisplay.isFailure;

public class EstablishmentMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establishment_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fillEstablishmentMenu();
    }

    private void fillEstablishmentMenu() {
        try {
            EstablishmentEndpoints services = retrofit.create(EstablishmentEndpoints.class);
            Call<Establishment> call = services.getMyEstablishment();

            call.enqueue(new Callback<Establishment>() {
                @Override
                public void onResponse(Call<Establishment> call, Response<Establishment> response) {
                    if(response.isSuccessful()) {
                        Establishment tmp = response.body();
                        TextView editestab_name = findViewById(R.id.establishment_name);
                        TextView editestab_email = findViewById(R.id.establishment_email);

                        editestab_name.setText(tmp.getName());
                        editestab_email.setText(tmp.getEmail());
                    } else {
                        isBad(EstablishmentMenu.this, response);
                    }
                }

                @Override
                public void onFailure(Call<Establishment> call, Throwable t) {
                    isFailure(EstablishmentMenu.this, t);
                }
            });
        } catch(Exception e) {
            isException(EstablishmentMenu.this, e);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.establishment_menu, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_homepage) {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.establishment_menu, new Homepage()).commit();
        } else if (id == R.id.nav_estabpage) {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.establishment_menu, new EstablishmentPage()).commit();
        } else if (id == R.id.nav_delivery) {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.establishment_menu, new EstablishmentDelivery()).commit();
        } else if (id == R.id.nav_settings) {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.establishment_menu, new EstablishmentChangeInfo()).commit();
        } else if (id == R.id.nav_logout) {
            Intent i = new Intent();
            i.setClass(EstablishmentMenu.this, LogIn.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
