package com.example.android.quakereport;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sangeetagupta1998 on 7/4/18.
 */

public class EarthQuake {

    static final String SEPARATOR  = "of";
    Double magnitude;
    String location;

    String date;
    String url;

    EarthQuake(Double magnitude , String location, String date,String url){

        this.magnitude = magnitude;
        this.url = url;
        this.location = location;
        this.date = date;

    }

    String getMagnitude(){
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        return decimalFormat.format(magnitude);
    }

    String getUrl(){
        return url;
    }

    String getLocation(){
        String localLocation;
        if(location.contains(SEPARATOR)) {
            localLocation = location.split(SEPARATOR)[1];
        }
        else {
            localLocation = location;
        }
        return localLocation;
    }

    String getDistance(){
        String localDistance;
        if(location.contains(SEPARATOR)) {
            localDistance = location.split(SEPARATOR)[0] + " " + SEPARATOR;
        }
        else {
            localDistance = "Near the ";
        }
        return localDistance;
    }

    String getDate(){
        Date dateObject = new Date(Long.parseLong(date));
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
        return dateFormatter.format(dateObject);

    }

    String getTime(){
        Date dateObject = new Date(Long.parseLong(date));
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}
