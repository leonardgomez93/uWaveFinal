package com.example.leonardgomez.uwavefinal.schedule;

import com.example.leonardgomez.uwavefinal.MainActivity;

import java.util.ArrayList;

/**
 * Created by leonardgomez on 3/22/18.
 */

public class CalendarEvents {
    private static ArrayList<CalendarEvent> ce = new ArrayList<>();
    private static ArrayList<CalendarEvent> ce2 = new ArrayList<>();

    public CalendarEvents() {

    }
    public static void addCalendarEvent(CalendarEvent calEvt){
        ce.add(calEvt);
    }

    public void printContents() {
        String s = "";
        String s2 = "";
        for(int i = 0; i < ce.size(); i++) {
            CalendarEvent temp = ce.get(i);
            s = s + temp.getRrule() + " " + temp.getSummary() + " " + temp.getDescription() + " " + temp.getTimeStartHour() + " " + temp.getTimeEndHour() + " " + temp.getMonthStart() + " " + temp.getDayStart() + " " + temp.getYearStart() + " " + ce.size() + "\n" + "\n";
            int t = Integer.parseInt(temp.getYearStart());
            if (t >= 2017) {
                ce2.add(temp);
            }

        }
        //s2 = ce2.size() + "";
        MainActivity.data.setText(s);
    }
    public CalendarEvent getAtIndex(int index) {
        return ce.get(index);
    }

    public int getSize() {
        return ce.size();
    }

    public String getDescription(String name) {
        String s = "";
        for(int i = 0; i < ce.size(); i++) {
            if(ce.get(i).getSummary().equals(name)) {
                s = ce.get(i).getDescription();
            }
        }
        return s;
    }

}