package com.example.leonardgomez.uwavefinal;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
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
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Schedule extends MainActivity {
    public static TextView data;
    private ImageView img;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);


        data = findViewById(R.id.stext);
        fetchData process = new fetchData();
        process.execute();

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



    class fetchData extends AsyncTask<Void, Integer, Void> {
        String data = "";
        String dataParsed = "";
        String singleParsed = "";
        String s = "";
        CalendarEvents ces = new CalendarEvents();
        int max = 100;


        @Override
        protected void onPreExecute()
        {
            progressBar = (ProgressBar) findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);

        }
        @Override
        protected void onProgressUpdate(Integer... values){
            progressBar.setProgress(values[0]);
        }


        @Override
        protected Void doInBackground(Void... values) {

            try {
                URL url = new URL("https://ical-to-json.herokuapp.com/convert.json?url=https%3A%2F%2Fcalendar.google.com%2Fcalendar%2Fical%2Fvfgkklo6vnqh4j7iu4b9ffqgvs%2540group.calendar.google.com%2Fpublic%2Fbasic.ics");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while (line != null) {
                    line = bufferedReader.readLine();
                    if (line != null) {
                        data = data + line;
                    }
                }
                try {
                    JSONObject jObj = new JSONObject(data);
                    JSONArray vCalendar = jObj.getJSONArray("vcalendar");
                    JSONObject vCalendar2 = vCalendar.getJSONObject(0);
                    JSONArray vEvent = vCalendar2.getJSONArray("vevent");

                    JsonHelper jh = new JsonHelper();
                    List jList = jh.toList(vEvent);
                    for (Object jSon : jList) {
                        String temp = jSon.toString();
                        String temp2[] = temp.split("\\}\\,\\{");
                        for (int i = 0; i < temp2.length; i++) {
                            String s = temp2[i];
                            s = s.replace("[", "");
                            s = s.replace("]", "");
                            s = s.replace("{", "");
                            s = s.replace("}", "");
                            s.trim();
                            String[] parts = s.split("(?<!\\\\)(, )");
                            Map<String, String> map = new HashMap<String, String>();
                            for (int j = 0; j < parts.length; j++) {
                                String parts2[] = parts[j].split("=");
                                for (int k = 0; k < parts2.length - 1; k++) {
                                    map.put(parts2[k], parts2[k + 1]);
                                }
                            }
                            if (map.containsKey("summary") && map.containsKey("description") && map.containsKey("dtstart") & map.containsKey("dtend")) {
                                CalendarEvent ceTemp = new CalendarEvent(map.get("summary"), map.get("description"), map.get("dtstart"), map.get("dtend"));
                                CalendarEvents.addCalendarEvent(ceTemp);
                            }


                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.INVISIBLE);
            ces.printContents();

            }
        }
    }


