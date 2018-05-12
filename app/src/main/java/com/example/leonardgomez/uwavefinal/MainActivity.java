package com.example.leonardgomez.uwavefinal;

import com.example.leonardgomez.uwavefinal.serviceforms.*;
import com.example.leonardgomez.uwavefinal.uwavechat.*;
import com.example.leonardgomez.uwavefinal.archive.*;
import com.example.leonardgomez.uwavefinal.schedule.*;

import com.firebase.client.Firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void openLivePlayer(View view) {
        Intent activity_live = new Intent(this, LivePlayer.class);
        startActivity(activity_live);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent activity_home = new Intent(this, MainActivity.class);
            startActivity(activity_home);
        } else if (id == R.id.nav_chat) {
            Intent activity_chat = new Intent(this, FirebaseAuthChecker.class);
            startActivity(activity_chat);
        } else if (id == R.id.nav_archive) {
            Intent activity_archive = new Intent(this, MixCloud.class);
            startActivity(activity_archive);
        } else if (id == R.id.nav_forum) {
            Intent activity_archive = new Intent(this, Forum.class);
            startActivity(activity_archive);
        } else if (id == R.id.nav_schedule) {
            Intent activity_schedule = new Intent(this, Schedule.class);
            startActivity(activity_schedule);
        } else if (id == R.id.nav_psa_form) {
            Intent activity_psa_form = new Intent(this, PsaApplicationForm.class);
            startActivity(activity_psa_form);
        } else if (id == R.id.nav_radio_app_form) {
            Intent activity_radio_form = new Intent(this, RadioApplicationForm.class);
            startActivity(activity_radio_form);
        } else if(id == R.id.nav_about) {
            Intent activity_about_us = new Intent(this, AboutUs.class);
            startActivity(activity_about_us);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
