package com.example.leonardgomez.uwavefinal.schedule;

import com.example.leonardgomez.uwavefinal.schedule.*;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekViewEvent;
import com.example.leonardgomez.uwavefinal.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A basic example of how to use week view library.
 * Created by Raquib-ul-Alam Kanak on 1/3/2014.
 * Website: http://alamkanak.github.io
 */
public class BasicActivity extends BaseActivity implements MonthLoader.MonthChangeListener {


    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
        return events;
    }

}