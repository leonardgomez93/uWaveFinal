package com.example.leonardgomez.uwavefinal;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.leonardgomez.uwavefinal.archive.Announcements;
import com.example.leonardgomez.uwavefinal.archive.MixCloud;
import com.example.leonardgomez.uwavefinal.schedule.CalendarEvent;
import com.example.leonardgomez.uwavefinal.schedule.CalendarEvents;
import com.example.leonardgomez.uwavefinal.schedule.JsonHelper;
import com.example.leonardgomez.uwavefinal.schedule.Schedule;
import com.example.leonardgomez.uwavefinal.serviceforms.PsaApplicationForm;
import com.example.leonardgomez.uwavefinal.serviceforms.RadioApplicationForm;
import com.example.leonardgomez.uwavefinal.uwavechat.Login;

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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static TextView data;
    public ImageView imgView;
    public CalendarEvents ces = new CalendarEvents();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgView = (ImageView)findViewById(R.id.liveButton);
        imgView .setVisibility(View.INVISIBLE);
        data = findViewById(R.id.stext);

        fetchData process = new fetchData();
        process.execute();
    }

    class fetchData extends AsyncTask<Void, Integer, Void> {
        String data = "";
        String dataParsed = "";
        String singleParsed = "";
        String s = "";

        int prog = 0;
        int max = 0;
        public ProgressBar progressBar;

        @Override
        protected void onPreExecute()
        {
            progressBar = (ProgressBar) findViewById(R.id.progressBar2);
            progressBar.setVisibility(View.VISIBLE);

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
                    max = jList.size();
                    progressBar.setMax(max);
                    for (Object jSon : jList) {
                        prog  = prog + 1;
                        progressBar.setProgress(prog, true);
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
                                ces.addCalendarEvent(ceTemp);
                            }
                        }
                    }
                    prog = progressBar.getProgress();
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
            imgView .setVisibility(View.VISIBLE);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    MainActivity.this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(MainActivity.this);
        }
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
        } else if (id == R.id.nav_forum) {
            Intent activity_chat = new Intent(this, Announcements.class);
            startActivity(activity_chat);
        } else if (id == R.id.nav_archive) {
            Intent activity_archive = new Intent(this, MixCloud.class);
            startActivity(activity_archive);
        } else if (id == R.id.nav_chat) {
            Intent activity_archive = new Intent(this, Login.class);
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
