package com.example.leonardgomez.uwavefinal.schedule;


/**
 * Created by leonardgomez on 3/22/18.
 */

public class CalendarEvent {
    private String summary;
    private String description;
    private String dtStart;
    private String dtEnd;
    private String freq;
    private String byDay;
    private String until = "";


    public CalendarEvent(String summary, String description, String dtStart, String dtEnd) {
        this.summary = summary;
        this.description = description;
        this.dtStart = dtStart;
        this.dtEnd = dtEnd;
    }

    public CalendarEvent(String summary, String description, String dtStart, String dtEnd, String until) {
        this.summary = summary;
        this.description = description;
        this.dtStart = dtStart;
        this.dtEnd = dtEnd;
        this.until = until;
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
    public String getTimeStartHour() {
        return dtStart.substring(9,11);
    }
    public String getTimeStartMin() {
        return dtStart.substring(11,13);
    }
    public String getTimeEndHour() {
        return dtEnd.substring(9,11);
    }
    public String getTimeEndMin() {
        return dtEnd.substring(11,13);
    }
    public String getFreq() { return freq; }
    public String getByday() { return byDay; }
    public String getUntil() { return until; }
    public String getRrule() { return freq + " " + byDay + " " + " " + until;}
}