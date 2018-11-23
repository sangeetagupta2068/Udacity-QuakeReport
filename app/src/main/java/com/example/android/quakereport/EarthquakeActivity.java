/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<EarthQuake>>{

    EarthQuakeAdapter earthQuakeAdapter;
    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static final String SAMPLE_JSON_QUERY = " https://earthquake.usgs.gov/fdsnws/event/1/query?" +
            "format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    ArrayList<EarthQuake> earthquakes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);


        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        earthQuakeAdapter = new EarthQuakeAdapter(this, new ArrayList<EarthQuake>());
        earthquakeListView.setAdapter(earthQuakeAdapter);
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                EarthQuake earthQuake = earthQuakeAdapter.getItem(i);
                Uri url = Uri.parse(earthQuake.getUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, url);
                startActivity(websiteIntent);
            }
        });

        earthquakeListView.setEmptyView(findViewById(R.id.textView));


        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

//        EarthQuakeAsyncTask earthQuakeAsyncTask = new EarthQuakeAsyncTask();
//        earthQuakeAsyncTask.execute(SAMPLE_JSON_QUERY);

        if(!isConnected){
            View spinner = findViewById(R.id.loading_spinner);
            spinner.setVisibility(View.GONE);
            TextView textView = findViewById(R.id.textView);
            textView.setText("No internet connection.");

        } else {

            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(1,null,this);

        }


    }

    @Override
    public Loader<List<EarthQuake>> onCreateLoader(int i, Bundle bundle) {
        return new EarthQuakeLoader(this,SAMPLE_JSON_QUERY);
    }

    @Override
    public void onLoadFinished(Loader<List<EarthQuake>> loader, List<EarthQuake> earthQuakes) {
        View spinner = findViewById(R.id.loading_spinner);
        spinner.setVisibility(View.GONE);
        TextView textView = findViewById(R.id.textView);
        textView.setText("No earthquakes found.");
        earthQuakeAdapter.clear();
        if(earthQuakes!=null){
            earthQuakeAdapter.addAll(earthQuakes);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<EarthQuake>> loader) {
        earthQuakeAdapter.clear();

    }

//    private class EarthQuakeAsyncTask extends AsyncTask<String,Void,List<EarthQuake>>{
//
//        @Override
//        protected List<EarthQuake> doInBackground(String... urls) {
//
//            if(urls==null){
//                return null;
//            }
//
//            earthquakes = QueryUtils.extractEarthquakes(urls[0]);
//            return earthquakes;
//        }
//
//        @Override
//        protected void onPostExecute(List<EarthQuake> earthQuakes) {
//            super.onPostExecute(earthQuakes);
//            earthQuakeAdapter.clear();
//            if(earthQuakes!=null){
//                earthQuakeAdapter.addAll(earthQuakes);
//            }
//        }
//    }
}
