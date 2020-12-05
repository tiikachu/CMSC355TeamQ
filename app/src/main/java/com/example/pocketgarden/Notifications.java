package com.example.pocketgarden;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.ArrayList;
import java.util.Calendar;

import plant.*;

public class Notifications extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener
{

    Context context;
    private static final String CHANNEL_ID = "pocket_garden";
    private static final String CHANNEL_NAME = "Pocket Garden";
    private static final String CHANNEL_DESC = "Pocket Garden Notifications";

    private CheckBox never, every1day, every2days, every3days, every4days, every5days, every6days, every7days;
    public static ArrayList<String> frequencyResult = new ArrayList<String>();
    private TextView textView;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    public static final String SHARED_PREFS = "com.pocketgarden.settings";

    PlantObject plant;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_settings);

        plant = new PlantObject("Sample Plant", 1, 1, null, true, true,"https://bs.floristic.org/image/o/473e2ed33e13f12e5424fff21996c7476520dc4d");

        buildNotification();

        never = findViewById(R.id.never);
        every1day = findViewById(R.id.every1day);
        every2days = findViewById(R.id.every2days);
        every3days = findViewById(R.id.every3days);
        every4days = findViewById(R.id.every4days);
        every5days = findViewById(R.id.every5days);
        every6days = findViewById(R.id.every6days);
        every7days = findViewById(R.id.every7days);
        textView  = findViewById(R.id.plant);

        never.setOnClickListener(this);
        every1day.setOnClickListener(this);
        every2days.setOnClickListener(this);
        every3days.setOnClickListener(this);
        every4days.setOnClickListener(this);
        every5days.setOnClickListener(this);
        every6days.setOnClickListener(this);
        every7days.setOnClickListener(this);

        preferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        editor = preferences.edit();

        never.setChecked(preferences.contains("neverChecked") && preferences.getBoolean("neverChecked", false));
        every1day.setChecked(preferences.contains("1checked") && preferences.getBoolean("1checked", false));
        every2days.setChecked(preferences.contains("2checked") && preferences.getBoolean("2checked", false));
        every3days.setChecked(preferences.contains("3checked") && preferences.getBoolean("3checked", false));
        every4days.setChecked(preferences.contains("4checked") && preferences.getBoolean("4checked", false));
        every5days.setChecked(preferences.contains("5checked") && preferences.getBoolean("5checked", false));
        every6days.setChecked(preferences.contains("6checked") && preferences.getBoolean("6checked", false));
        every7days.setChecked(preferences.contains("7checked") && preferences.getBoolean("7checked", false));

        never.setOnCheckedChangeListener(this);
        every1day.setOnCheckedChangeListener(this);
        every2days.setOnCheckedChangeListener(this);
        every3days.setOnCheckedChangeListener(this);
        every4days.setOnCheckedChangeListener(this);
        every5days.setOnCheckedChangeListener(this);
        every6days.setOnCheckedChangeListener(this);
        every7days.setOnCheckedChangeListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch (buttonView.getId()) {
            case R.id.never:
                editor.putBoolean("neverChecked", never.isChecked());
                break;
            case R.id.every1day:
                editor.putBoolean("1checked", every1day.isChecked());
                break;
            case R.id.every2days:
                editor.putBoolean("2checked", every2days.isChecked());
                break;
            case R.id.every3days:
                editor.putBoolean("3checked", every3days.isChecked());
                break;
            case R.id.every4days:
                editor.putBoolean("4checked", every4days.isChecked());
                break;
            case R.id.every5days:
                editor.putBoolean("5checked", every5days.isChecked());
                break;
            case R.id.every6days:
                editor.putBoolean("6checked", every6days.isChecked());
                break;
            case R.id.every7days:
                editor.putBoolean("7checked", every7days.isChecked());
                break;
        }
        editor.apply();
        displayNotification();
    }

    // Could I merge this method with onCheckedChange?
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        frequencyResult = new ArrayList<>();
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.never:
                if (checked) {
                    frequencyResult.add("Never");
                } else {
                    frequencyResult.remove("Never");
                }
                break;
            case R.id.every1day:
                if (checked) {
                    frequencyResult.add("Every 1 day");
                } else {
                    frequencyResult.remove("Every 1 day");
                }
                break;
            case R.id.every2days:
                if (checked) {
                    frequencyResult.add("Every 2 days");
                } else {
                    frequencyResult.remove("Every 2 days");
                }
                break;
            case R.id.every3days:
                if (checked) {
                    frequencyResult.add("Every 3 days");
                } else {
                    frequencyResult.remove("Every 3 days");
                }
                break;
            case R.id.every4days:
                if (checked) {
                    frequencyResult.add("Every 4 days");
                } else {
                    frequencyResult.remove("Every 4 days");
                }
                break;
            case R.id.every5days:
                if (checked) {
                    frequencyResult.add("Every 5 days");

                } else {
                    frequencyResult.remove("Every 5 days");
                }
                break;
            case R.id.every6days:
                if (checked) {
                    frequencyResult.add("Every 6 days");

                } else {
                    frequencyResult.remove("Every 6 days");
                }
                break;
            case R.id.every7days:
                if (checked) {
                    frequencyResult.add("Every 7 days");

                } else {
                    frequencyResult.remove("Every 7 days");
                }
                break;
        }
      //  displayNotification();

    }

    private void buildNotification () {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
    private void displayNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setContentTitle("Pocket Garden")
                .setContentText("Water your Plant!")
                .setSmallIcon(R.mipmap.logo_round)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.logo_round))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(Notifications.this);
        notificationManagerCompat.notify(1, builder.build());
    }

    public void loadPlantObjects () {
        PlantList plantList = new PlantList();
        PlantObject [] plantListArray = plantList.getPlantList();

        for (int i=0; i < plantListArray.length; i++) {
            //create a card view for each Plant object
            createCardView();

        }

        TextView plantName = (TextView) findViewById(R.id.plant);
        plantName.setText(plant.getName());

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        never.setChecked(never.isChecked());
//    }

    public void createCardView() {
    }

    public void createAlarm () {
        Intent intent = new Intent (getApplicationContext(), NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 19);
        calendar.set(Calendar.SECOND, 30);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }



//    @SuppressLint("SetTextI18n")
//    public void updateText () {
//        PlantCare Plant = new PlantCare();
//        if (Plant.id.equals("p1")) {
//            textView.setText("Sample Plant 1");
//        }
//        else if (Plant.id.equals("p2")) {
//            textView.setText("Sample Plant 2");
//        }
//        else if (Plant.id.equals("p3")) {
//            textView.setText("Sample Plant 3");
//        }
//        else if (Plant.id.equals("p4")) {
//            textView.setText("Sample Plant 4");
//        }
//
//    }
}


