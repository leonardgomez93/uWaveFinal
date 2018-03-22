package com.example.leonardgomez.uwavefinal;

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
            s = s + temp.getSummary() + " " + temp.getDescription() + " " + temp.getDtStart() + " " + temp.getDtEnd() + "\n" + "\n";
        }
        Schedule.data.setText(s);
    }


}
