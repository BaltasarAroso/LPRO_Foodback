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

import com.foodback.foodback.R;
import com.foodback.foodback.fragment.AdminPromotions;
import com.foodback.foodback.fragment.Homepage;
import com.foodback.foodback.fragment.Notifications;
import com.foodback.foodback.fragment.Spotlight;
import com.foodback.foodback.fragment.UserChangeInfo;
import com.foodback.foodback.fragment.UserDelivery;
import com.foodback.foodback.fragment.UserPromotions;


public class AdminMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);
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
        fm.beginTransaction().replace(R.id.admin_menu, new Homepage()).commit();
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

        getMenuInflater().inflate(R.menu.admin_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        Intent i;

        switch (item.getItemId()) {

            case R.id.action_search:
                //TODO não está bem feito, tem que se criar um drawer (video)
                i = new Intent();
                i.setClass(AdminMenu.this, SearchField.class);
                startActivity(i);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

//    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.

        FragmentManager fm;

        switch (item.getItemId()){

            case R.id.nav_homepage:
                fm = getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.admin_menu, new Homepage()).commit();
                break;

            case R.id.nav_spotlights:
                fm = getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.admin_menu, new Spotlight()).commit();
                break;

            case R.id.nav_delivery:
                fm = getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.admin_menu, new UserDelivery()).commit();
                break;

            case R.id.nav_promotions:
                fm = getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.admin_menu, new AdminPromotions()).commit();
                break;

            case R.id.nav_notifications:
                fm = getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.admin_menu, new Notifications()).commit();
                break;

            case R.id.nav_settings:
                fm = getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.admin_menu, new UserChangeInfo()).commit();
                break;

            case R.id.nav_logout:
                Intent i = new Intent();
                i.setClass(AdminMenu.this, LogIn.class);
                startActivity(i);
                break;

            default:
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
