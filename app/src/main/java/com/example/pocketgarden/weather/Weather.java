package com.example.pocketgarden.weather;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.example.pocketgarden.R;

public class Weather extends AppCompatActivity {

    private TextView temperature, location, weatherCondition;
    private ProgressBar weatherProgressBar;
    private View weatherContainer;
    private ImageView weatherConditionIcon;
    private EditText locationField;
    private FloatingActionButton floatingActionButton;

    private String currentLocation = "Richmond";
    private int textCount = 0;

    private CurrentWeatherService currentWeatherService;
    private boolean gettingWeather = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        weatherContainer = findViewById(R.id.weatherContainer);
        weatherProgressBar = findViewById(R.id.weatherProgressBar);
        temperature = findViewById(R.id.temperature);
        location = findViewById(R.id.location);
        weatherCondition = findViewById(R.id.weatherCondition);
        weatherConditionIcon = findViewById(R.id.weatherConditionIcon);
        locationField = findViewById(R.id.locationField);
        floatingActionButton = findViewById(R.id.fab);

        currentWeatherService = new CurrentWeatherService(this);

        locationField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            // When text is changed, refresh icon changes to search icon
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                count = s.toString().trim().length();
                if (count == 0) {
                    floatingActionButton.setImageResource(R.drawable.refresh_foreground);
                }
                else {
                    floatingActionButton.setImageResource(R.drawable.ic_baseline_search_24);
                }
                textCount = count;
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textCount == 0) {
                    refreshWeather();
                }
                else {
                    searchForWeather(locationField.getText().toString());
                    locationField.setText("");
                }
            }
        });
        searchForWeather(currentLocation);
    }

    @Override
    protected void onDestroy () {
        super.onDestroy();
        currentWeatherService.cancel();
    }

    private void refreshWeather () {
        if (gettingWeather) {
            return;
        }
        searchForWeather(currentLocation);
    }

    private void searchForWeather (@NonNull final String location) {
        toggleProgress(true);
        gettingWeather = true;
        currentWeatherService.getCurrentWeather(location, currentWeatherCallback);
    }

    private void toggleProgress (final boolean showProgress) {
        if (showProgress) {
            weatherContainer.setVisibility(View.GONE);
            weatherProgressBar.setVisibility(View.VISIBLE);
        }
        else {
            weatherContainer.setVisibility(View.VISIBLE);
            weatherProgressBar.setVisibility(View.GONE);
        }
    }

    private final CurrentWeatherCallback currentWeatherCallback = new CurrentWeatherCallback() {

        // Take properties of CurrentWeather and populate with UI
        @Override
        public void onCurrentWeather(@NonNull CurrentWeather currentWeather) {
            currentLocation = currentWeather.location;
            temperature.setText(String.valueOf(currentWeather.convertToFahrenheit()));
            location.setText(currentWeather.location);
            weatherCondition.setText(currentWeather.weatherCondition);
            weatherConditionIcon.setImageResource(CurrentWeatherUtils.getWeatherIconResId
                    (currentWeather.conditionId));
            toggleProgress(false);
            gettingWeather = false;
        }

        @Override
        public void onError(@Nullable Exception exception) {
            toggleProgress(false);
            gettingWeather = false;
            Toast.makeText(Weather.this, "There was an error getting weather data. Try again", Toast.LENGTH_SHORT).show();
        }
    };


}