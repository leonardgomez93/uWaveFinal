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
    public String getDtStart() {
        return dtStart;
    }
    public String getDtEnd() {
        return dtEnd;
    }

}
