package com.lifeistech.android.network.client;

import android.location.Location;

import com.lifeistech.android.network.entity.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rild on 16/02/15.
 */
public class JSONWeatherTask {
    protected Weather doInBackground(String... params) {
        Weather weather = new Weather();
        String data = ( (new WeatherHttpClient()).getWeatherData(params[0]));

        try {
            weather = JSONWeatherParser.getWeather(data);

            // Let's retrieve the icon
            weather.iconData = ( (new WeatherHttpClient()).getImage(weather.currentCondition.getIcon()));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weather;

    }

}
