package com.example.leonardgomez.uwavefinal.schedule;

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
public class BasicActivity extends Schedule implements MonthLoader.MonthChangeListener {


    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();


        Calendar startTime = Calendar.getInstance();
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DATE, Integer.parseInt(ces.getAtIndex(0).getDayStart()));
        startTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ces.getAtIndex(0).getTimeStartHour()));
        startTime.set(Calendar.MINUTE, Integer.parseInt(ces.getAtIndex(0).getTimeStartMin()));
        startTime.set(Calendar.MONTH, Integer.parseInt(ces.getAtIndex(0).getMonthStart()) - 1);
        startTime.set(Calendar.YEAR, Integer.parseInt(ces.getAtIndex(0).getYearStart()));
        Calendar endTime = (Calendar) startTime.clone();
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ces.getAtIndex(0).getTimeEndHour()));
        endTime.set(Calendar.MINUTE, Integer.parseInt(ces.getAtIndex(0).getTimeEndMin()));
        WeekViewEvent event = new WeekViewEvent(0 + 1, ces.getAtIndex(0).getSummary(), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_01));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.DATE, Integer.parseInt(ces.getAtIndex(2).getDayStart()));
        startTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ces.getAtIndex(2).getTimeStartHour()));
        startTime.set(Calendar.MINUTE, Integer.parseInt(ces.getAtIndex(2).getTimeStartMin()));
        startTime.set(Calendar.MONTH, Integer.parseInt(ces.getAtIndex(2).getMonthStart()) - 1);
        startTime.set(Calendar.YEAR, Integer.parseInt(ces.getAtIndex(2).getYearStart()));
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ces.getAtIndex(2).getTimeEndHour()));
        endTime.set(Calendar.MINUTE, Integer.parseInt(ces.getAtIndex(2).getTimeEndMin()));
        event = new WeekViewEvent(0 + 2, ces.getAtIndex(2).getSummary(), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_01));
        events.add(event);

        for(int i = 0; i < ces.getSize() - 2; i++)  {
            startTime = Calendar.getInstance();
            startTime.set(Calendar.DATE, Integer.parseInt(ces.getAtIndex(i + 1).getDayStart()));
            startTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ces.getAtIndex(i + 1).getTimeStartHour()));
            startTime.set(Calendar.MINUTE, Integer.parseInt(ces.getAtIndex(i + 1).getTimeStartMin()));
            startTime.set(Calendar.MONTH, Integer.parseInt(ces.getAtIndex(i + 1).getMonthStart()) - 1);
            startTime.set(Calendar.YEAR, Integer.parseInt(ces.getAtIndex(i + 1).getYearStart()));
            endTime = (Calendar) startTime.clone();
            endTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ces.getAtIndex(i + 1).getTimeEndHour()));
            endTime.set(Calendar.MINUTE, Integer.parseInt(ces.getAtIndex(i + 1).getTimeEndMin()));
            event = new WeekViewEvent(i + 2, ces.getAtIndex(i + 1).getSummary(), startTime, endTime);
            event.setColor(getResources().getColor(R.color.event_color_02));
            events.add(event);

        }


        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 4);
        endTime.set(Calendar.MINUTE, 30);
        endTime.set(Calendar.MONTH, newMonth-1);
        event = new WeekViewEvent(10, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_02));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 4);
        startTime.set(Calendar.MINUTE, 20);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 5);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(10, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_03));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 5);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 2);
        endTime.set(Calendar.MONTH, newMonth-1);
        event = new WeekViewEvent(2, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_02));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 5);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth - 1);
        startTime.set(Calendar.YEAR, newYear);
        startTime.add(Calendar.DATE, 1);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        endTime.set(Calendar.MONTH, newMonth - 1);
        event = new WeekViewEvent(3, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_03));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 15);
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        event = new WeekViewEvent(4, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_04));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 1);
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        event = new WeekViewEvent(5, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_01));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, startTime.getActualMaximum(Calendar.DAY_OF_MONTH));
        startTime.set(Calendar.HOUR_OF_DAY, 15);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        event = new WeekViewEvent(5, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_02));
        events.add(event);

        return events;
    }

}