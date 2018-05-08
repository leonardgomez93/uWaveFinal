package com.example.leonardgomez.uwavefinal.schedule;

import com.example.leonardgomez.uwavefinal.MainActivity;

import java.util.ArrayList;

/**
 * Created by leonardgomez on 3/22/18.
 */

public class CalendarEvents {
    private static ArrayList<CalendarEvent> ce = new ArrayList<>();

    public CalendarEvents() {

    }
    public static void addCalendarEvent(CalendarEvent calEvt){
        ce.add(calEvt);
    }

    public void printContents() {
        String s = "";
        for(int i = 0; i < ce.size(); i++) {
            CalendarEvent temp = ce.get(i);
            s = s + temp.getSummary() + " " + temp.getDescription() + " " + temp.getYearStart() + " " + temp.getYearEnd() + "\n" + "\n";
        }
        MainActivity.data.setText(s);
    }


}