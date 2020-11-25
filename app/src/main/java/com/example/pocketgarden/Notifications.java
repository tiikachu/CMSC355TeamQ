package com.example.pocketgarden;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.ArrayList;

import plant.*;
public class Notifications extends AppCompatActivity implements View.OnClickListener{

    private static final String CHANNEL_ID = "pocket_garden";
    private static final String CHANNEL_NAME = "Pocket Garden";
    private static final String CHANNEL_DESC = "Pocket Garden Notifications";

    private CheckBox never, every1day, every2days, every3days, every4days, every5days, every6days, every7days;
    private ArrayList<String> frequencyResult = new ArrayList<String>();
    private Button notificationButton;
    private boolean checked;
SharedPreferences sharedPref;
    PlantObject plant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_settings);

        plant = new PlantObject("Sample Plant", 1, 1, null, true, true,"https://bs.floristic.org/image/o/473e2ed33e13f12e5424fff21996c7476520dc4d");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        notificationButton = findViewById(R.id.notification_button);
        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayNotification();
            }
        });

        sharedPref = Notifications.this.getPreferences(Context.MODE_PRIVATE);
        boolean isMyValueChecked = sharedPref.getBoolean("checkbox", false);

        every1day = findViewById(R.id.every1day);
        every2days = findViewById(R.id.every2days);
        every3days = findViewById(R.id.every3days);
        every4days = findViewById(R.id.every4days);
        every5days = findViewById(R.id.every5days);
        every6days = findViewById(R.id.every6days);
        every7days = findViewById(R.id.every7days);
        never = findViewById(R.id.never);

//        every1day.setChecked(isMyValueChecked);
//        every2days.setChecked(isMyValueChecked);
//        every3days.setChecked(isMyValueChecked);
//        every4days.setChecked(isMyValueChecked);
//        every5days.setChecked(isMyValueChecked);
//        every6days.setChecked(isMyValueChecked);
//        every7days.setChecked(isMyValueChecked);
//        never.setChecked(isMyValueChecked);

        every1day.setOnClickListener(this);
        every2days.setOnClickListener(this);
        every3days.setOnClickListener(this);
        every4days.setOnClickListener(this);
        every5days.setOnClickListener(this);
        every6days.setOnClickListener(this);
        every7days.setOnClickListener(this);
        never.setOnClickListener(this);

        loadPlantObjects();
    }

    @Override
    public void onClick(View v) {

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("checkbox", ((CheckBox) v).isChecked());
        editor.apply();

        frequencyResult = new ArrayList<>();
        boolean checked = ((CheckBox) v).isChecked();

        switch (v.getId()) {
            case R.id.never:
                if (checked) {
                    frequencyResult.add("Never");
                    displayNotification();
                    Toast.makeText(Notifications.this, frequencyResult.toString(), Toast.LENGTH_SHORT).show();
                    editor.putBoolean("checkbox", ((CheckBox) v).isChecked());
                    editor.apply();
                } else {
                    frequencyResult.remove("Never");
                }
                break;
            case R.id.every1day:
                if (checked) {
                    frequencyResult.add("Every 1 day");
                    displayNotification();
                    Toast.makeText(Notifications.this, frequencyResult.toString(), Toast.LENGTH_SHORT).show();
                } else {
                    frequencyResult.remove("Every 1 day");
                }
                break;
            case R.id.every2days:
                if (checked) {
                    frequencyResult.add("Every 2 days");
                    displayNotification();
                } else {
                    frequencyResult.remove("Every 2 days");
                }
                break;
            case R.id.every3days:
                if (checked) {
                    frequencyResult.add("Every 3 days");
                    displayNotification();
                } else {
                    frequencyResult.remove("Every 3 days");
                }
                break;
            case R.id.every4days:
                if (checked) {
                    frequencyResult.add("Every 4 days");
                    displayNotification();
                } else {
                    frequencyResult.remove("Every 4 days");
                }
                break;
            case R.id.every5days:
                if (checked) {
                    frequencyResult.add("Every 5 days");
                    displayNotification();
                } else {
                    frequencyResult.remove("Every 5 days");
                }
                break;
            case R.id.every6days:
                if (checked) {
                    frequencyResult.add("Every 6 days");
                    displayNotification();
                } else {
                    frequencyResult.remove("Every 6 days");
                }
                break;
            case R.id.every7days:
                if (checked) {
                    frequencyResult.add("Every 7 days");
                    displayNotification();
                } else {
                    frequencyResult.remove("Every 7 days");
                }
                break;
        }
    }



    private void displayNotification() {


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setContentTitle("Pocket Garden")
                .setContentText("Water your plant!")
                .setSmallIcon(R.mipmap.logo_round)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.logo_round))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(Notifications.this);
        notificationManagerCompat.notify(1, builder.build());

    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        every1day.setChecked(every1day.isChecked());
//        every2days.setChecked(every2days.isChecked());
//        every3days.setChecked(every3days.isChecked());
//        every4days.setChecked(every4days.isChecked());
//        every5days.setChecked(every5days.isChecked());
//        every6days.setChecked(every6days.isChecked());
//        every7days.setChecked(every7days.isChecked());
//        never.setChecked(never.isChecked());
//    }

    public void loadPlantObjects () {
        PlantList plantList = new PlantList();
        PlantObject [] plantListArray = plantList.getPlantList();

        for (int i=0; i < plantListArray.length; i++) {
            //create a card view for each plant object
            createCardView();

        }

        TextView plantName = (TextView) findViewById(R.id.plant);
        plantName.setText(plant.getName());

    }

    public void createCardView() {

    }


}

