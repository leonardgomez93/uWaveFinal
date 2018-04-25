package com.example.leonardgomez.uwavefinal;

/**
 * Created by leonardgomez on 3/22/18.
 */

public class CalendarEvent {
    private String summary;
    private String description;
    private String dtStart;
    private String dtEnd;


    public CalendarEvent(String summary, String description, String dtStart, String dtEnd) {
        this.summary = summary;
        this.description = description;
        this.dtStart = dtStart;
        this.dtEnd = dtEnd;
    }
    public String getSummary() {

        return summary;
    }
    public String getDescription() {
        return description;
    }

    public String getYearStart() {
        return dtStart.substring(0,4);
    }
    public String getYearEnd() {
        return dtEnd.substring(0,4);
    }
    public String getMonthStart() {
        return dtStart.substring(4,6);
    }
    public String getMonthEnd() {
        return dtEnd.substring(4,6);
    }
    public String getDayStart() {
        return dtStart.substring(6,8);
    }
    public String getDayEnd() {
        return dtEnd.substring(6,8);
    }
    public String getTimeStart() {
        return dtStart.substring(9,15);
    }
    public String getTimeEnd() {
        return dtEnd.substring(9,15);
    }
}
