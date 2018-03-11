package com.example.leonardgomez.uwavefinal;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;



public class Schedule extends MainActivity implements NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);


        WebView webview = (WebView)findViewById(R.id.webview);

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Pulls web page of UWave's calendar and displays within the WebView
        webview.loadUrl("https://calendar.google.com/calendar/embed?src=vfgkklo6vnqh4j7iu4b9ffqgvs%40group.calendar.google.com&ctz=America%2FLos_Angeles");
        webview.setVerticalScrollBarEnabled(true);
        webview.setHorizontalScrollBarEnabled(true);

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



}
