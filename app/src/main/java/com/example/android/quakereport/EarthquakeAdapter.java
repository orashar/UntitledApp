
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
//
//import android.content.Context;
//import android.graphics.drawable.GradientDrawable;
//import android.support.v4.content.ContextCompat;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.squareup.picasso.Picasso;
//
//import java.text.DecimalFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
///**
// * An {@link EarthquakeAdapter} knows how to create a list item layout for each earthquake
// * in the data source (a list of {@link Earthquake} objects).
// *
// * These list item layouts will be provided to an adapter view like ListViewl
// * to be displayed to the user.
// */
//public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
//    /**
//     * The part of the location string from the USGS service that we use to determine
//     * whether or not there is a location offset present ("5km N of Cairo, Egypt").
//     */
//    private static final String LOCATION_SEPARATOR = " of ";
//
//    /**
//     * Constructs a new {@link EarthquakeAdapter}.
//     *
//     * @param context     of the app
//     * @param earthquakes is the list of earthquakes, which is the data source of the adapter
//     */
//    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
//        super(context, 0, earthquakes);
//    }
//
//    /**
//     * Returns a list item view that displays information about the earthquake at the given position
//     * in the list of earthquakes.
//     */
//    @Override
//
//    public View getView(int position, View convertView, ViewGroup parent) {
//        // Check if there is an existing list item view (called convertView) that we can reuse,
//        // otherwise, if convertView is null, then inflate a new list item layout.
//        View listItemView = convertView;
//        if (listItemView == null) {
//            listItemView = LayoutInflater.from(getContext()).inflate(
//                    R.layout.earthquake_list_item, parent, false);
//        }
//
//        // Find the earthquake at the given position in the list of earthquakes
//        Earthquake currentEarthquake = getItem(position);
//        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
//        String  news = currentEarthquake.getMagnitude() ;
//        String image   = currentEarthquake.imageUrl() ;
//        magnitudeView.setText(news);
//
//        ImageView pic = (ImageView)listItemView.findViewById(R.id.weather_icon) ;
//
//        Picasso.with(getContext()).load(image).into(pic);
//
//        // Return the list item view that is now showing the appropriate data
//        return listItemView;
//    }
//
//
//}


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class EarthquakeAdapter extends FragmentStatePagerAdapter {

    List<Earthquake> earthquakes;

    public EarthquakeAdapter(FragmentManager fm, List<Earthquake> earthquakes) {
        super(fm);
        this.earthquakes = earthquakes;
    }

    @Override
    public Fragment getItem(int position) {


        return EarthQuakeFragment.newInstance(earthquakes.get(position).imageUrl(), earthquakes.get(position).descriptions());


    }

    @Override
    public int getCount() {
        return earthquakes.size();
    }
}
