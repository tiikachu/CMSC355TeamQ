/**
 * Fetch data from API and return it asynchronously in a CurrentWeather data model object
 */
package com.example.pocketgarden.weather;

import android.app.Activity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CurrentWeatherService {

    private static final String TAG = CurrentWeatherService.class.getSimpleName();
    private static final String URL = "http://api.openweathermap.org/data/2.5/weather";
    private static final String CURRENT_WEATHER_TAG = "CURRENT_WEATHER";
    private static final String API_KEY = "569274716925f5275ac09238dfc3d811";

    private RequestQueue queue;

    public CurrentWeatherService (final Activity activity) {
        queue = Volley.newRequestQueue(activity.getApplicationContext());
    }

    // Callback - resolve network call
    public void getCurrentWeather (@NonNull final String locationName, @NonNull final CurrentWeatherCallback callback) {
        final String url = String.format("%s?q=%s&appId=%s", URL, locationName, API_KEY);

        // request of GET type for HTTP to get data back
        StringRequest stringRequest = new StringRequest (Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // Parsing JSON object
                            final JSONObject currentWeatherJSONObject = new JSONObject(response);
                            final JSONArray weather = currentWeatherJSONObject.getJSONArray("weather");
                            final JSONObject weatherCondition = weather.getJSONObject(0);
                            final String locationName = currentWeatherJSONObject.getString("name");
                            final int conditionId = weatherCondition.getInt("id");
                            final String conditionName = weatherCondition.getString("main");
                            final double tempKel = currentWeatherJSONObject.getJSONObject("main").getDouble("temp");
                            final CurrentWeather currentWeather = new CurrentWeather(locationName, conditionId, conditionName, tempKel);
                            // construct CurrentWeather object
                            callback.onCurrentWeather(currentWeather);
                        } catch (JSONException e) {
                            callback.onError(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError(error);
            }
        });
           stringRequest.setTag(CURRENT_WEATHER_TAG);
           queue.add(stringRequest);
    }

    // Cancel pending requests
    public void cancel() {
        queue.cancelAll(CURRENT_WEATHER_TAG);
    }

}
