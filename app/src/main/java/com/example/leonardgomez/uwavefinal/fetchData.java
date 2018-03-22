package com.example.leonardgomez.uwavefinal;

/**
 * Created by leonardgomez on 3/19/18.
 */
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import com.google.common.base.Splitter;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.google.common.base.Splitter;


public class fetchData extends AsyncTask<Void,Void,Void> {

    String data = "";
    String dataParsed = "";
    String singleParsed = "";
    String s = "";
    CalendarEvents ces = new CalendarEvents();

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://ical-to-json.herokuapp.com/convert.json?url=https%3A%2F%2Fcalendar.google.com%2Fcalendar%2Fical%2Fvfgkklo6vnqh4j7iu4b9ffqgvs%2540group.calendar.google.com%2Fpublic%2Fbasic.ics");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null) {
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
                for (Object jSon : jList) {
                    String temp = jSon.toString();
                    String temp2[] = temp.split("\\}\\,\\{");
                    for (int i = 0; i < temp2.length; i++) {
                        String s = temp2[i];
                        s = s.replace("[","");
                        s = s.replace("]","");
                        s = s.replace("{","");
                        s = s.replace("}","");
                        s.trim();
                        String[] parts = s.split("(?<!\\\\)(, )");
                        Map<String, String>  map = new HashMap<String,String>();
                        for(int j = 0; j < parts.length; j++) {
                            String parts2[] = parts[j].split("=");
                            for(int k = 0; k < parts2.length - 1; k++) {
                                map.put(parts2[k],parts2[k+1]);
                            }
                        }
                        if(map.containsKey("summary") && map.containsKey("description") && map.containsKey("dtstart") & map.containsKey("dtend")) {
                            CalendarEvent ceTemp = new CalendarEvent(map.get("summary"), map.get("description"), map.get("dtstart"), map.get("dtend"));
                            CalendarEvents.addCalendarEvent(ceTemp);
                        }



                    }

                }
            } catch (JSONException e)  {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        ces.printContents();
    }
}
