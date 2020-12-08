package com.example.pocketgarden;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Calendar;

import plant.*;

public class Notifications extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    Button button;
    Toast toast;
    private CheckBox sunday, monday, tuesday, wednesday, thursday, friday, saturday;
    public static ArrayList<String> frequencyResult = new ArrayList<String>();
    private TextView textView;
    Calendar calendar;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    public static final String SHARED_PREFS = "com.pocketgarden.settings";

    PlantObject plant;

    NotificationReceiver notificationReceiver = new NotificationReceiver();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_settings);

        plant = new PlantObject("Sample Plant", 1, 1, null, true, true, "https://bs.floristic.org/image/o/473e2ed33e13f12e5424fff21996c7476520dc4d");

        notificationReceiver.buildNotification(getApplicationContext());

        sunday = findViewById(R.id.sunday);
        monday = findViewById(R.id.monday);
        tuesday = findViewById(R.id.tuesday);
        wednesday = findViewById(R.id.wednesday);
        thursday = findViewById(R.id.thursday);
        friday = findViewById(R.id.friday);
        saturday = findViewById(R.id.saturday);

        textView = findViewById(R.id.plant);


//
//        never.setOnClickListener(this);
//        every1day.setOnClickListener(this);
//        every2days.setOnClickListener(this);
//        every3days.setOnClickListener(this);
//        every4days.setOnClickListener(this);
//        every5days.setOnClickListener(this);
//        every6days.setOnClickListener(this);
//        every7days.setOnClickListener(this);

        preferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        editor = preferences.edit();

        sunday.setChecked(preferences.contains("sunday") && preferences.getBoolean("sunday", false));
        monday.setChecked(preferences.contains("monday") && preferences.getBoolean("monday", false));
        tuesday.setChecked(preferences.contains("tuesday") && preferences.getBoolean("tuesday", false));
        wednesday.setChecked(preferences.contains("wednesday") && preferences.getBoolean("wednesday", false));
        thursday.setChecked(preferences.contains("thursday") && preferences.getBoolean("thursday", false));
        friday.setChecked(preferences.contains("friday") && preferences.getBoolean("friday", false));
        saturday.setChecked(preferences.contains("saturday") && preferences.getBoolean("saturday", false));

        sunday.setOnCheckedChangeListener(this);
        monday.setOnCheckedChangeListener(this);
        tuesday.setOnCheckedChangeListener(this);
        wednesday.setOnCheckedChangeListener(this);
        thursday.setOnCheckedChangeListener(this);
        friday.setOnCheckedChangeListener(this);
        saturday.setOnCheckedChangeListener(this);

        button = findViewById(R.id.saveButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), editor.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        // frequencyResult = new ArrayList<>();
        switch (buttonView.getId()) {
            case R.id.sunday:
                editor.putBoolean("sunday", sunday.isChecked());
                String value = preferences.toString();
                //    Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
                break;
            case R.id.monday:
                editor.putBoolean("monday", monday.isChecked());
                break;
            case R.id.tuesday:
                editor.putBoolean("tuesday", tuesday.isChecked());
                break;
            case R.id.wednesday:
                editor.putBoolean("wednesday", wednesday.isChecked());
                break;
            case R.id.thursday:
                editor.putBoolean("thursday", thursday.isChecked());
                break;
            case R.id.friday:
                editor.putBoolean("friday", friday.isChecked());
                break;
            case R.id.saturday:
                editor.putBoolean("saturday", saturday.isChecked());
                break;
        }
        editor.apply();
        createAlarm();
        notificationReceiver.displayNotification(getApplicationContext());
    }

    public void loadPlantObjects() {
        PlantList plantList = new PlantList();
        PlantObject[] plantListArray = plantList.getPlantList();

        for (int i = 0; i < plantListArray.length; i++) {
            //create a card view for each Plant object
            createCardView();

        }

        TextView plantName = (TextView) findViewById(R.id.plant);
        plantName.setText(plant.getName());

    }

    public void createCardView() {
    }

    public void createAlarm() {
        calendar = Calendar.getInstance();
        // If it is Sunday and user checked "every sunday" then set calendar to build notification
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY && preferences.getBoolean("sunday", true)) {
            setCalendar(1);
        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY && preferences.getBoolean("monday", true)) {
            setCalendar(2);
        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY && preferences.getBoolean("tuesday", true)) {
            setCalendar(3);
        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY && preferences.getBoolean("wednesday", true)) {
            setCalendar(4);
        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY && preferences.getBoolean("thursday", true)) {
            setCalendar(5);
        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY && preferences.getBoolean("friday", true)) {
            setCalendar(6);
        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY && preferences.getBoolean("saturday", true)) {
            setCalendar(7);
        }

        Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent);

    }

    public void setCalendar(int weekNumber) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, weekNumber);
        calendar.set(Calendar.HOUR_OF_DAY, 7);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
    }
}

