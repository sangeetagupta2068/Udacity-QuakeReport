package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sangeetagupta1998 on 11/23/18.
 */

public class EarthQuakeLoader extends AsyncTaskLoader<List<EarthQuake>> {
    String url;
    ArrayList<EarthQuake> earthquakes;
    private static final String LOG_TAG = EarthQuakeLoader.class.getName();

    EarthQuakeLoader(Context context, String url){
        super(context);
        this.url = url;
    }
    @Override
    public List<EarthQuake> loadInBackground() {
        if(url == null){
            return null;
        }
        earthquakes = QueryUtils.extractEarthquakes(url);
        return earthquakes;
    }
    @Override
    protected void onStartLoading(){
        forceLoad();
    }
}
