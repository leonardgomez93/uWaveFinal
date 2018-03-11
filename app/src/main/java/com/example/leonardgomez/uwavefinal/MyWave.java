package com.example.leonardgomez.uwavefinal;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebSettings;


public class MyWave extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wave);
        WebView myWave = (WebView) findViewById(R.id.webview);

        WebSettings webSettings = myWave.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWave.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSfh8DdB22l7kjb_akwjjs8pPaW6XcwPccCK3FcRmcGrkVWBsA/viewform");


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
