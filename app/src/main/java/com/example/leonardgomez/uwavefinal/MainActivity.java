// Need to bypass defaulting MainActivity to all pages, and move livestream button up
// to task bar, solely in main.xml

package com.example.leonardgomez.uwavefinal;

import android.media.MediaPlayer;
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
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.content.Intent;


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
        } else if (id == R.id.nav_about) {
            Intent activity_about = new Intent(this, AboutUs.class);
            startActivity(activity_about);
        } else if (id == R.id.nav_schedule) {
            Intent activity_schedule = new Intent(this, Schedule.class);
            startActivity(activity_schedule);
        } else if (id == R.id.nav_mywave) {
            Intent activity_mywave = new Intent(this, MyWave.class);
            startActivity(activity_mywave);
        } else if (id == R.id.nav_community) {
            Intent activity_community = new Intent(this, Community.class);
            startActivity(activity_community);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
