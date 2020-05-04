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

import android.content.ContentValues;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility functions to handle OpenWeatherMap JSON data.
 */
public final class OpenWeatherJsonUtils {



    /**
     * This method parses JSON from a web response and returns an array of Strings
     * describing the weather over various days from the forecast.
     * <p/>
     * Later on, we'll be parsing the JSON into structured data within the
     * getFullWeatherDataFromJson function, leveraging the data we have stored in the JSON. For
     * now, we just convert the JSON into human-readable strings.
     *
     * @param forecastJsonStr JSON response from server
     * @return Array of Strings describing weather data
     * @throws JSONException If JSON data cannot be properly parsed
     */
    public static Map<String, ArrayList<String>> getSimpleWeatherStringsFromJson(Context context, String forecastJsonStr)
            throws JSONException {



        /* Weather information. Each day's forecast info is an element of the "list" array */
//        final String OWM_LIST = "list";
//
//        /* All temperatures are children of the "temp" object */
//        final String OWM_TEMPERATURE = "temp";
//
//        /* Max temperature for the day */
//        final String OWM_MAX = "max";
//        final String OWM_MIN = "min";
//
//        final String OWM_WEATHER = "weather";
//        final String OWM_DESCRIPTION = "main";
//
//        final String OWM_MESSAGE_CODE = "cod";

        /* String array to hold each day's weather String */
        //String[] parsedWeatherData = null;

        JSONObject forecastJsons = new JSONObject(forecastJsonStr);


        JSONObject current = forecastJsons.getJSONObject("currently") ;

        String currentTemp = current.getString("temperature") ;
        String weatherObjectcurrent = current.getString("icon");

        JSONObject forecastJson = forecastJsons.getJSONObject("daily");
        /* Is there an error? */
//        if (forecastJson.has(OWM_MESSAGE_CODE)) {
//            int errorCode = forecastJson.getInt(OWM_MESSAGE_CODE);
//
//            switch (errorCode) {
//                case HttpURLConnection.HTTP_OK:
//                    break;
//                case HttpURLConnection.HTTP_NOT_FOUND:
//                    /* Location invalid */
//                    return null;
//                default:
//                    /* Server probably down */
//                    return null;
//            }
//        }

        JSONArray weatherArray = forecastJson.getJSONArray("data");

        Map<String, ArrayList<String>> parsedWeatherData = new HashMap<>();

        long localDate = System.currentTimeMillis();
        long utcDate = SunshineDateUtils.getUTCDateFromLocal(localDate);
        long startDay = SunshineDateUtils.normalizeDate(utcDate);

        for (int i = 0; i < weatherArray.length(); i++) {
            String date;



            /* These are the values that will be collected */
            long dateTimeMillis;
//            double high;
//            double low;
//            String description;

            /* Get the JSON object representing the day */
            JSONObject dayForecast = weatherArray.getJSONObject(i);

            /*
             * We ignore all the datetime values embedded in the JSON and assume that
             * the values are returned in-order by day (which is not guaranteed to be correct).
             */
            dateTimeMillis = startDay + SunshineDateUtils.DAY_IN_MILLIS * i;
            date = SunshineDateUtils.getDayName(context, dateTimeMillis);
            String weatherObject = dayForecast.getString("icon");



            /*
             * Description is in a child array called "weather", which is 1 element long.
             * That element also contains a weather code.
             */


            String temperatureMin = dayForecast.getString("temperatureMin");
            String humidity = dayForecast.getString("humidity");
            String windSpeed = dayForecast.getString("windSpeed");
            String uvIndex = dayForecast.getString("uvIndex");
            String pressure = dayForecast.getString("pressure");
            String temperatureMax = dayForecast.getString("temperatureMax");


            ArrayList<String> dailyForecast = new ArrayList<>();

            dailyForecast.add(date);
            dailyForecast.add(weatherObject);
            dailyForecast.add(temperatureMax);
            dailyForecast.add(temperatureMin);
            dailyForecast.add(humidity);
            dailyForecast.add(windSpeed);
            dailyForecast.add(uvIndex);
            dailyForecast.add(pressure);
            dailyForecast.add(currentTemp) ;
            dailyForecast.add(weatherObjectcurrent) ;


            parsedWeatherData.put(String.valueOf(i), dailyForecast);



            /*
             * Temperatures are sent by Open Weather Map in a child object called "temp".
             *
             * Editor's Note: Try not to name variables "temp" when working with temperature.
             * It confuses everybody. Temp could easily mean any number of things, including
             * temperature, temporary and is just a bad variable name.
             */
//            JSONObject temperatureObject = dayForecast.getJSONObject(OWM_TEMPERATURE);
//            high = temperatureObject.getDouble(OWM_MAX);

//            highAndLow = SunshineWeatherUtils.formatHighLows(context, high, low);


        }

        return parsedWeatherData;
    }

    /**
     * Parse the JSON and convert it into ContentValues that can be inserted into our database.
     *
     * @param context         An application context, such as a service or activity context.
     * @param forecastJsonStr The JSON to parse into ContentValues.
     * @return An array of ContentValues parsed from the JSON.
     */
    public static ContentValues[] getFullWeatherDataFromJson(Context context, String forecastJsonStr) {
        /** This will be implemented in a future lesson **/


        return null;
    }
}