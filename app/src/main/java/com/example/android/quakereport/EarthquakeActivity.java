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
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class EarthquakeActivity extends AppCompatActivity
        implements LoaderCallbacks<List<Earthquake>> {


    private static final String LOG_TAG = EarthquakeActivity.class.getName();

    private String USGS_REQUEST_URL = "https://newsapi.org/v2/top-headlines?country=in&apiKey=2af5a6f18eca4769bec23c2657068fd1";

    private String baseUrl = "https://newsapi.org/v2/everything?q=";
    private String parameterUrl = "";
    private String apikeyUrl = "&apiKey=2af5a6f18eca4769bec23c2657068fd1";

    private static final int EARTHQUAKE_LOADER_ID = 1;

    private TextView mEmptyStateTextView;

    public EarthquakeAdapter mAdapter;

    public List<Earthquake> earth;

    public Bundle bundle = null;

    private VerticalViewPager earthquakeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        try {
            parameterUrl = getIntent().getStringExtra("PARAMETER_URL");
        } catch (Exception e) {
            Log.v("EarthquakeActivity", e.getMessage());
        }
        USGS_REQUEST_URL = baseUrl + parameterUrl + apikeyUrl;
        earth = new ArrayList<>();
        // Find a reference to the {@link ListView} in the layout
        earthquakeListView = findViewById(R.id.verticleViewPager);


        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {

            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(EARTHQUAKE_LOADER_ID, bundle, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
//            View loadingIndicator = findViewById(R.id.loading_indicator);
//            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            //  mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }


    @Override
    public Loader<List<Earthquake>> onCreateLoader(int i, Bundle bundle) {


        Uri baseUri = Uri.parse(USGS_REQUEST_URL);
        Log.v("CreateLoaderParseUrl", USGS_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();


        return new EarthquakeLoader(this, uriBuilder.toString());
    }


    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakes) {
        // mAdapter.clear() ;


        if (earthquakes != null && !earthquakes.isEmpty()) {
            earth = earthquakes;
            mAdapter = new EarthquakeAdapter(getSupportFragmentManager(), earth);
            earthquakeListView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {


        // Loader reset, so we can clear out our existing data.
        // mAdapter.clear() ;
    }


}

