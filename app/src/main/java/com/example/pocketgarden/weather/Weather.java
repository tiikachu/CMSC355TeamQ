package com.example.pocketgarden.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pocketgarden.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Weather extends AppCompatActivity {
    private TextView temperature, location, weatherCondition;
    private ImageView weatherConditionIcon;
    private EditText locationField;
    private FloatingActionButton floatingActionButton;
    private int textCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        temperature = findViewById(R.id.temperature);
        location = findViewById(R.id.location);
        weatherCondition = findViewById(R.id.weatherCondition);
        weatherConditionIcon = findViewById(R.id.weatherConditionIcon);
        locationField = findViewById(R.id.locationField);
        floatingActionButton = findViewById(R.id.fab);

        locationField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

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
            public void afterTextChanged(Editable s) {

            }
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
    }

    private void refreshWeather () {
        Toast.makeText(this, "Refresh", Toast.LENGTH_SHORT).show();
    }

    private void searchForWeather (final String location) {
        Toast.makeText(this, "Search for: " + location, Toast.LENGTH_SHORT).show();
    }


}