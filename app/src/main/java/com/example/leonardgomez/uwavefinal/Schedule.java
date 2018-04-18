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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

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
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Schedule extends MainActivity {
    public static TextView data;
    private ImageView img;
    private ProgressBar progressBar;
    // *TextView
    private TextView textViewServiceApp;
    private TextView textViewWeekCalender;
    private TextView textViewPrevDate;
    private TextView textViewDate;
    private TextView textViewNextDate;
    private TextView textViewSun;
    private TextView textViewMon;
    private TextView textViewTue;
    private TextView textViewWed;
    private TextView textViewThu;
    private TextView textViewFri;
    private TextView textViewSat;
    private TextView textView12am;
    private TextView textView1am;
    private TextView textView2am;
    private TextView textView3am;
    private TextView textView4am;
    private TextView textView5am;
    private TextView textView6am;
    private TextView textView7am;
    private TextView textView8am;
    private TextView textView9am;
    private TextView textView10am;
    private TextView textView11am;
    private TextView textView12pm;
    private TextView textView1pm;
    private TextView textView2pm;
    private TextView textView3pm;
    private TextView textView4pm;
    private TextView textView5pm;
    private TextView textView6pm;
    private TextView textView7pm;
    private TextView textView8pm;
    private TextView textView9pm;
    private TextView textView10pm;
    private TextView textView11pm;

    // * Views
    private RelativeLayout relativeLayoutSunday;
    private RelativeLayout relativeLayoutMonDay;
    private RelativeLayout relativeLayoutTueDay;
    private RelativeLayout relativeLayoutWedDay;
    private RelativeLayout relativeLayoutThuDay;
    private RelativeLayout relativeLayoutFriDay;
    private RelativeLayout relativeLayoutSatDay;

    // *IMges
    private ImageView buttonBack;
    private ImageView buttonHome;

    private Typeface typface;

    public String dateFormat, logInID;
    public String[] weekDays;
    public String[] NextPreWeekday;
    public String dateFormate;
    public String firstDayOfWeek;
    public String lastDayOfWeek;

    public static ArrayList<Entity1> arrayListEntity = new ArrayList<Entity1>();
    public static ArrayList<Entity1> arrayListEButtonId = new ArrayList<Entity1>();

    public int weekDaysCount = 0;
    public ArrayList<WeekSets> weekDatas;
    String tapMargin ;
    String buttonHight;


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

        textViewPrevDate = (TextView) findViewById(R.id.textViewPrevDate);
        textViewDate = (TextView) findViewById(R.id.textViewDate);
        textViewNextDate = (TextView) findViewById(R.id.textViewNextDate);
        textViewSun = (TextView) findViewById(R.id.textViewSun);
        textViewMon = (TextView) findViewById(R.id.textViewMon);
        textViewTue = (TextView) findViewById(R.id.textViewTue);
        textViewWed = (TextView) findViewById(R.id.textViewWed);
        textViewThu = (TextView) findViewById(R.id.textViewThu);
        textViewFri = (TextView) findViewById(R.id.textViewFri);
        textViewSat = (TextView) findViewById(R.id.textViewSat);
        textView12am = (TextView) findViewById(R.id.textView12am);
        textView1am = (TextView) findViewById(R.id.textView1am);
        textView2am = (TextView) findViewById(R.id.textView2am);
        textView3am = (TextView) findViewById(R.id.textView3am);
        textView4am = (TextView) findViewById(R.id.textView4am);
        textView5am = (TextView) findViewById(R.id.textView5am);
        textView6am = (TextView) findViewById(R.id.textView6am);
        textView7am = (TextView) findViewById(R.id.textView7am);
        textView8am = (TextView) findViewById(R.id.textView8am);
        textView9am = (TextView) findViewById(R.id.textView9am);
        textView10am = (TextView) findViewById(R.id.textView10am);
        textView11am = (TextView) findViewById(R.id.textView11am);
        textView12pm = (TextView) findViewById(R.id.textView12pm);
        textView1pm = (TextView) findViewById(R.id.textView1pm);
        textView2pm = (TextView) findViewById(R.id.textView2pm);
        textView3pm = (TextView) findViewById(R.id.textView3pm);
        textView4pm = (TextView) findViewById(R.id.textView4pm);
        textView5pm = (TextView) findViewById(R.id.textView5pm);
        textView6pm = (TextView) findViewById(R.id.textView6pm);
        textView7pm = (TextView) findViewById(R.id.textView7pm);
        textView8pm = (TextView) findViewById(R.id.textView8pm);
        textView9pm = (TextView) findViewById(R.id.textView9pm);
        textView10pm = (TextView) findViewById(R.id.textView10pm);
        textView11pm = (TextView) findViewById(R.id.textView11pm);

        relativeLayoutSunday = (RelativeLayout) findViewById(R.id.relativeLayoutSunday);
        relativeLayoutMonDay = (RelativeLayout) findViewById(R.id.relativeLayoutMonDay);
        relativeLayoutTueDay = (RelativeLayout) findViewById(R.id.relativeLayoutTueDay);
        relativeLayoutWedDay = (RelativeLayout) findViewById(R.id.relativeLayoutWedDay);
        relativeLayoutThuDay = (RelativeLayout) findViewById(R.id.relativeLayoutThuDay);
        relativeLayoutFriDay = (RelativeLayout) findViewById(R.id.relativeLayoutFriDay);
        relativeLayoutSatDay = (RelativeLayout) findViewById(R.id.relativeLayoutSatDay);

        NextPreWeekday = getWeekDay();
        firstDayOfWeek = CommonMethod.convertWeekDays(NextPreWeekday[0]);
        lastDayOfWeek = CommonMethod.convertWeekDays(NextPreWeekday[6]);
        textViewDate.setText(CommonMethod.convertWeekDaysMouth(NextPreWeekday[6]));

        textViewSun.setText(CommonMethod.convertWeekDays(NextPreWeekday[0])
                + "\nSun");
        textViewMon.setText(CommonMethod.convertWeekDays(NextPreWeekday[1])
                + "\nMon");
        textViewTue.setText(CommonMethod.convertWeekDays(NextPreWeekday[2])
                + "\nTue");
        textViewWed.setText(CommonMethod.convertWeekDays(NextPreWeekday[3])
                + "\nWeb");
        textViewThu.setText(CommonMethod.convertWeekDays(NextPreWeekday[4])
                + "\nThu");
        textViewFri.setText(CommonMethod.convertWeekDays(NextPreWeekday[5])
                + "\nFri");
        textViewSat.setText(CommonMethod.convertWeekDays(NextPreWeekday[6])
                + "\nSat");

    }

    public String[] getWeekDay() {

        Calendar now = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String[] days = new String[7];
        int delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 1;
        now.add(Calendar.DAY_OF_MONTH, delta);
        for (int i = 0; i < 7; i++) {
            days[i] = format.format(now.getTime());
            now.add(Calendar.DAY_OF_MONTH, 1);
        }

        return days;

    }

    @SuppressLint("SimpleDateFormat")
    public String[] getWeekDayNext() {

        weekDaysCount++;
        Calendar now1 = Calendar.getInstance();
        Calendar now = (Calendar) now1.clone();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String[] days = new String[7];
        int delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 1;
        now.add(Calendar.WEEK_OF_YEAR, weekDaysCount);
        now.add(Calendar.DAY_OF_MONTH, delta);
        for (int i = 0; i < 7; i++) {
            days[i] = format.format(now.getTime());
            now.add(Calendar.DAY_OF_MONTH, 1);
        }

        return days;

    }

    @SuppressLint("SimpleDateFormat")
    public String[] getWeekDayPrev() {

        weekDaysCount--;
        Calendar now1 = Calendar.getInstance();
        Calendar now = (Calendar) now1.clone();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String[] days = new String[7];
        int delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 1;
        now.add(Calendar.WEEK_OF_YEAR, weekDaysCount);
        now.add(Calendar.DAY_OF_MONTH, delta);
        for (int i = 0; i < 7; i++) {
            days[i] = format.format(now.getTime());
            now.add(Calendar.DAY_OF_MONTH, 1);
        }

        return days;

    }

    public Button getButtonToLayout(int higth, int marginTop,
                                    String buttonText, String jobID, int buttonID) {

        @SuppressWarnings("deprecation")
        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.FILL_PARENT, higth);
        Button button = new Button(getApplicationContext());
        button.setLayoutParams(params);
        button.setBackgroundColor(Color.parseColor("#9ACC61"));
        button.setText(buttonText);
        button.setOnClickListener(buttonOnclckForAllWeekButton);
        button.setTextSize(9);
        button.setId(buttonID);
        params.setMargins(0, marginTop, 0, 0);
        arrayListEntity.add(getEntity(jobID, buttonText));

        return button;

    }

    public OnClickListener buttonOnclckForAllWeekButton = new OnClickListener() {

        @Override
        public void onClick(View v) {
            Button b = (Button) v;

            String buttonText = b.getText().toString();
            int position = 0;

            for (int j = 0; j < arrayListEntity.size(); j++) {
                Entity1 itemOne = arrayListEntity.get(j);

                String arryJobRefID = itemOne.JobRefID;
                if (arryJobRefID.equals(buttonText)) {
                    position = j;
                    break;
                }
            }

            Entity1 itemOne1 = arrayListEntity.get(position);
            Toast.makeText(getApplicationContext() , "  " + itemOne1.JobRefID , Toast.LENGTH_SHORT).show();

        }
    };

    public static Entity1 getEntity(String jobID, String jobRefID) {
        Entity1 E = new Entity1();
        E.JobIDForButton = jobID;
        E.JobRefID = jobRefID;
        return E;

    }

    public static Entity1 getButton(int layoutView, int buttonTag) {
        Entity1 E = new Entity1();
        E.layoutView = layoutView;
        E.buttonTag = buttonTag;
        return E;

    }

    public static WeekSets getWeekValues(String weekDay, String jobId,
                                         String jobRefId, String tapMargina, String buttonHighta) {
        WeekSets W = new WeekSets();
        W.day = weekDay;
        W.jobID = jobId;
        W.jobRefID = jobRefId;
        W.tapMargin = tapMargina;
        W.buttonHight = buttonHighta;

        return W;
    }

    public String getWithAndHigthToButton(int startTime) {

        int time;
        String size = "0";
        try {
            time = startTime;

            switch (time) {
                case 0:
                    size = "0";
                    break;
                case 1:
                    size = "60";

                    break;
                case 2:
                    size = "120";

                    break;
                case 3:
                    size = "180";

                    break;
                case 4:
                    size = "240";

                    break;
                case 5:
                    size = "300";

                    break;
                case 6:
                    size = "360";

                    break;
                case 7:
                    size = "420";

                    break;
                case 8:
                    size = "480";

                    break;
                case 9:
                    size = "540";

                    break;
                case 10:
                    size = "600";

                    break;
                case 11:
                    size = "660";

                    break;
                case 12:
                    size = "720";

                    break;
                case 13:
                    size = "780";

                    break;
                case 14:
                    size = "840";

                    break;
                case 15:
                    size = "900";

                    break;
                case 16:
                    size = "960";

                    break;
                case 17:
                    size = "1020";

                    break;
                case 18:
                    size = "1080";

                    break;
                case 19:
                    size = "1140";

                    break;
                case 20:
                    size = "1200";

                    break;
                case 21:
                    size = "1260";

                    break;
                case 22:
                    size = "1320";

                    break;
                case 23:
                    size = "1380";
                    break;

                default:
                    break;
            }

        } catch (Exception e) {
            Log.getStackTraceString(e);
        }

        return size;

    }

    public String getHightOfButton(int startTime, int endTime) {
        String hight = "0";
        try {
            int subHigth = endTime - startTime;

            hight = String.valueOf(subHigth * 60);

        } catch (Exception e) {
            Log.getStackTraceString(e);
        }

        return hight;

    }



    class fetchData extends AsyncTask<Void, Integer, Void> {
        String data = "";
        String dataParsed = "";
        String singleParsed = "";
        String s = "";
        CalendarEvents ces = new CalendarEvents();
        int prog = 0;
        int max = 0;

        @Override
        protected void onPreExecute()
        {
            progressBar = (ProgressBar) findViewById(R.id.progressBar);
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
                                CalendarEvents.addCalendarEvent(ceTemp);
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
            try {
                weekDatas = new ArrayList<WeekSets>();



                //** for sun day
                tapMargin = getWithAndHigthToButton(4);
                buttonHight = getHightOfButton(4, 9);
                weekDatas.add(getWeekValues(String.valueOf(0), "12", "ref",
                        tapMargin, buttonHight));



                //** for tue day
                tapMargin = getWithAndHigthToButton(8);
                buttonHight = getHightOfButton(8, 14);
                weekDatas.add(getWeekValues(String.valueOf(2), "12", "ref",
                        tapMargin, buttonHight));

                //** for tue day
                tapMargin = getWithAndHigthToButton(15);
                buttonHight = getHightOfButton(15, 19);
                weekDatas.add(getWeekValues(String.valueOf(2), "12", "ref",
                        tapMargin, buttonHight));



                //** for fr day
                tapMargin = getWithAndHigthToButton(2);
                buttonHight = getHightOfButton(2, 10);
                weekDatas.add(getWeekValues(String.valueOf(5), "12", "ref",
                        tapMargin, buttonHight));





            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.INVISIBLE);
            //ces.printContents();

            try {

                WeekSets weekToDay;
                int length = weekDatas.size();
                Log.i("length===>", String.valueOf(length));

                if (length != 0) {
                    for (int k = 0; k < weekDatas.size(); k++) {
                        weekToDay = weekDatas.get(k);

                        int day = Integer.parseInt(weekToDay.day);
                        switch (day) {
                            case 0:

                                int sunday = 100;
                                relativeLayoutSunday
                                        .addView(getButtonToLayout(
                                                Integer.parseInt(weekToDay.buttonHight),
                                                Integer.parseInt(weekToDay.tapMargin),
                                                weekToDay.jobRefID,
                                                weekToDay.jobID, sunday));
                                arrayListEButtonId.add(getButton(0, sunday));
                                sunday++;
                                break;

                            case 1:
                                int MonDay = 200;
                                relativeLayoutMonDay
                                        .addView(getButtonToLayout(
                                                Integer.parseInt(weekToDay.buttonHight),
                                                Integer.parseInt(weekToDay.tapMargin),
                                                weekToDay.jobRefID,
                                                weekToDay.jobID, MonDay));
                                arrayListEButtonId.add(getButton(1, MonDay));
                                MonDay++;
                                break;
                            case 2:
                                int TueDay = 200;
                                relativeLayoutTueDay
                                        .addView(getButtonToLayout(
                                                Integer.parseInt(weekToDay.buttonHight),
                                                Integer.parseInt(weekToDay.tapMargin),
                                                weekToDay.jobRefID,
                                                weekToDay.jobID, TueDay));
                                arrayListEButtonId.add(getButton(2, TueDay));
                                TueDay++;
                                break;
                            case 3:
                                int WedDay = 200;
                                relativeLayoutWedDay
                                        .addView(getButtonToLayout(
                                                Integer.parseInt(weekToDay.buttonHight),
                                                Integer.parseInt(weekToDay.tapMargin),
                                                weekToDay.jobRefID,
                                                weekToDay.jobID, WedDay));
                                arrayListEButtonId.add(getButton(3, WedDay));
                                WedDay++;
                                break;
                            case 4:
                                int ThuDay = 200;
                                relativeLayoutThuDay
                                        .addView(getButtonToLayout(
                                                Integer.parseInt(weekToDay.buttonHight),
                                                Integer.parseInt(weekToDay.tapMargin),
                                                weekToDay.jobRefID,
                                                weekToDay.jobID, ThuDay));
                                arrayListEButtonId.add(getButton(4, ThuDay));
                                ThuDay++;
                                break;
                            case 5:
                                int FriDay = 200;
                                relativeLayoutFriDay
                                        .addView(getButtonToLayout(
                                                Integer.parseInt(weekToDay.buttonHight),
                                                Integer.parseInt(weekToDay.tapMargin),
                                                weekToDay.jobRefID,
                                                weekToDay.jobID, FriDay));
                                arrayListEButtonId.add(getButton(5, FriDay));
                                FriDay++;
                                break;
                            case 6:
                                int SatDay = 200;
                                relativeLayoutSatDay
                                        .addView(getButtonToLayout(
                                                Integer.parseInt(weekToDay.buttonHight),
                                                Integer.parseInt(weekToDay.tapMargin),
                                                weekToDay.jobRefID,
                                                weekToDay.jobID, SatDay));
                                arrayListEButtonId.add(getButton(6, SatDay));
                                SatDay++;
                                break;

                            default:
                                break;
                        }

                    }

                }

            } catch (Exception e) {
                Log.getStackTraceString(e);
            }

            }
        }
    }


