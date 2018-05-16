package com.example.leonardgomez.uwavefinal.schedule;

import android.graphics.RectF;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.example.leonardgomez.uwavefinal.MainActivity;
import com.example.leonardgomez.uwavefinal.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;


public class Schedule extends MainActivity implements WeekView.EventClickListener, MonthLoader.MonthChangeListener, WeekView.EventLongPressListener, WeekView.EmptyViewLongPressListener {

    private static final int TYPE_DAY_VIEW = 1;
    private static final int TYPE_THREE_DAY_VIEW = 2;
    private static final int TYPE_WEEK_VIEW = 3;
    private int mWeekViewType = TYPE_THREE_DAY_VIEW;
    private WeekView mWeekView;
    private int monthStart = 0;
    private int dayStart = 0;
    private int hourStart = 0;
    private int minuteStart = 0;
    private int yearStart = 0;
    private int hourEnd = 0;
    private int minuteEnd = 0;
    private String summary = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        mWeekView = (WeekView) findViewById(R.id.weekView);
        mWeekView.setOnEventClickListener(this);
        mWeekView.setMonthChangeListener(this);
        mWeekView.setEventLongPressListener(this);
        mWeekView.setEmptyViewLongPressListener(this);
        setupDateTimeInterpreter(false);

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
    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
        Random r = new Random();
        Calendar startTime = Calendar.getInstance();
        Calendar endTime = (Calendar) startTime.clone();
        Calendar startTime2 = Calendar.getInstance();
        Calendar endTime2 = (Calendar) startTime.clone();
        WeekViewEvent event = new WeekViewEvent(0 + 1, cesForever.getAtIndex(0).getSummary(), startTime, endTime);
        WeekViewEvent event2 = new WeekViewEvent(0 + 1, cesForever.getAtIndex(0).getSummary(), startTime, endTime);

        for (int i = 0; i < cesForever.getSize(); i++) {
            startTime = Calendar.getInstance();
            monthStart = Integer.parseInt(cesForever.getAtIndex(i).getMonthStart()) - 1;
            dayStart = Integer.parseInt(cesForever.getAtIndex(i).getDayStart());
            hourStart = Integer.parseInt(cesForever.getAtIndex(i).getTimeStartHour());
            minuteStart = Integer.parseInt(cesForever.getAtIndex(i).getTimeStartMin());
            yearStart = Integer.parseInt(cesForever.getAtIndex(i).getYearStart());
            hourEnd = Integer.parseInt(cesForever.getAtIndex(i).getTimeEndHour());
            minuteEnd = Integer.parseInt(cesForever.getAtIndex(i).getTimeEndMin());
            startTime.set(Calendar.DATE, dayStart);
            if (startTime.get(Calendar.MONTH) == newMonth && startTime.get(Calendar.YEAR) == newYear) {
                startTime.set(Calendar.HOUR_OF_DAY, hourStart);
                startTime.set(Calendar.MINUTE, minuteStart);
                startTime.set(Calendar.MONTH, monthStart);
                startTime.set(Calendar.YEAR, yearStart);
                endTime = (Calendar) startTime.clone();
                endTime.set(Calendar.HOUR_OF_DAY, hourEnd);
                endTime.set(Calendar.MINUTE, minuteEnd);
                summary = cesForever.getAtIndex(i).getSummary();
                int a = r.nextInt(4);
                if (a == 0) {
                    event.setColor(getResources().getColor(R.color.event_color_01));
                } else if (a == 1) {
                    event.setColor(getResources().getColor(R.color.event_color_02));
                } else if (a == 2) {
                    event.setColor(getResources().getColor(R.color.event_color_03));
                } else {
                    event.setColor(getResources().getColor(R.color.event_color_04));
                }
                event = new WeekViewEvent(i + 2, cesForever.getAtIndex(i).getSummary(), startTime, endTime);
                GregorianCalendar gc = new GregorianCalendar();
                gc.set(Calendar.DAY_OF_MONTH, dayStart);
                gc.set(Calendar.MONTH, monthStart);
                gc.set(Calendar.YEAR, yearStart);
                int numberofDaysPassed = gc.get(Calendar.DAY_OF_YEAR);
                int count = 0;
                int count2 = 0;
                for (int j = 7; j <= 1400; j = j + 7) {
                    startTime2 = Calendar.getInstance();
                    int daysInYear = 0;
                    if (gc.isLeapYear(startTime.get(Calendar.YEAR) + count2)) {
                        daysInYear = 366;
                    } else {
                        daysInYear = 365;
                    }
                    if (numberofDaysPassed + j > daysInYear) {
                        numberofDaysPassed = 0 + ((numberofDaysPassed + j) - count) % daysInYear;
                        count = j - 7;
                        count2++;
                    }
                    //numberofDaysPassed = numberofDaysPassed + j - count;
                    startTime2.set(Calendar.DAY_OF_YEAR, numberofDaysPassed + j - count);
                    startTime2.set(Calendar.YEAR, yearStart + count2);
                    if (startTime2.get(Calendar.MONTH) == newMonth && startTime2.get(Calendar.YEAR) == newYear) {
                        startTime2.set(Calendar.HOUR_OF_DAY, hourStart);
                        startTime2.set(Calendar.MINUTE, minuteStart);
                        //startTime2.set(Calendar.MONTH, Integer.parseInt(cesForever.getAtIndex(i).getMonthStart()) - 1);

                        endTime2 = (Calendar) startTime2.clone();
                        endTime2.set(Calendar.HOUR_OF_DAY, hourEnd);
                        endTime2.set(Calendar.MINUTE, minuteEnd);
                        event2 = new WeekViewEvent((10000 * i) + j, summary, startTime2, endTime2);
                        a = r.nextInt(4);
                        if (a == 0) {
                            event2.setColor(getResources().getColor(R.color.event_color_01));
                        } else if (a == 1) {
                            event2.setColor(getResources().getColor(R.color.event_color_02));
                        } else if (a == 2) {
                            event2.setColor(getResources().getColor(R.color.event_color_03));
                        } else {
                            event2.setColor(getResources().getColor(R.color.event_color_04));
                        }
                        events.add(event2);
                    }
                }
            }
        }
        return events;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        setupDateTimeInterpreter(id == R.id.action_week_view);
        switch (id){
            case R.id.action_today:
                mWeekView.goToToday();
                return true;
            case R.id.action_day_view:
                if (mWeekViewType != TYPE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(1);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;
            case R.id.action_three_day_view:
                if (mWeekViewType != TYPE_THREE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_THREE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(3);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;
            case R.id.action_week_view:
                if (mWeekViewType != TYPE_WEEK_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_WEEK_VIEW;
                    mWeekView.setNumberOfVisibleDays(7);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Set up a date time interpreter which will show short date values when in week view and long
     * date values otherwise.
     * @param shortDate True if the date values should be short.
     */
    private void setupDateTimeInterpreter(final boolean shortDate) {
        mWeekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(Calendar date) {
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
                String weekday = weekdayNameFormat.format(date.getTime());
                SimpleDateFormat format = new SimpleDateFormat(" M/d", Locale.getDefault());

                // All android api level do not have a standard way of getting the first letter of
                // the week day name. Hence we get the first char programmatically.
                // Details: http://stackoverflow.com/questions/16959502/get-one-letter-abbreviation-of-week-day-of-a-date-in-java#answer-16959657
                if (shortDate)
                    weekday = String.valueOf(weekday.charAt(0));
                return weekday.toUpperCase() + format.format(date.getTime());
            }

            @Override
            public String interpretTime(int hour) {
                return hour > 11 ? (hour - 12) + " PM" : (hour == 0 ? "12 AM" : hour + " AM");
            }
        });
    }
    protected String getEventTitle(Calendar time) {
        return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH)+1, time.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(this, ces.getDescription(event.getName()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(this, "Long pressed event: " + event.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmptyViewLongPress(Calendar time) {
        Toast.makeText(this, "Empty view long pressed: " + getEventTitle(time), Toast.LENGTH_SHORT).show();
    }

    public WeekView getWeekView() {
        return mWeekView;
    }
}
