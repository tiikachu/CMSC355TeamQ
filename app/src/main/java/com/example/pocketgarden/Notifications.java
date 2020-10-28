package com.example.pocketgarden;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.ArrayList;

public class Notifications extends AppCompatActivity {

    private static final String CHANNEL_ID = "pocket_garden";
    private static final String CHANNEL_NAME = "Pocket Garden";
    private static final String CHANNEL_DESC = "Pocket Garden Notifications";
    private Button notificationButton;
    CheckBox never, every1day, every2days, every3days, every4days, every5days, every6days, every7days;
    private ArrayList<String> frequencyResult;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

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

        frequencyResult = new ArrayList<>();
        // use switch cases instead for each
        never = findViewById(R.id.never);
        never.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (never.isChecked()) {
                    frequencyResult.add("Never");
                    displayNotification();
                } else {
                    frequencyResult.remove("Never");
                }
            }
        });

        every1day = findViewById(R.id.every1day);
        every1day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (every1day.isChecked()) {
                    frequencyResult.add("Every 1 day");
                    displayNotification();
                } else {
                    frequencyResult.remove("Every 1 day");
                }
            }
        });

        every2days = findViewById(R.id.every2days);
        every2days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (every2days.isChecked()) {
                    frequencyResult.add("Every 2 days");
                    displayNotification();
                } else {
                    frequencyResult.remove("Every 2 days");
                }
            }
        });

        every3days = findViewById(R.id.every3days);
        every3days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (every3days.isChecked()) {
                    frequencyResult.add("Every 3 days");
                    displayNotification();
                } else {
                    frequencyResult.remove("Every 3 days");
                }
            }
        });

        every4days = findViewById(R.id.every4days);
        every4days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (every4days.isChecked()) {
                    frequencyResult.add("Every 4 days");
                    displayNotification();
                } else {
                    frequencyResult.remove("Every 4 days");
                }
            }
        });

        every5days = findViewById(R.id.every5days);
        every5days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (every5days.isChecked()) {
                    frequencyResult.add("Every 5 days");
                    displayNotification();
                } else {
                    frequencyResult.remove("Every 5 days");
                }
            }
        });

        every6days = findViewById(R.id.every6days);
        every6days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (every6days.isChecked()) {
                    frequencyResult.add("Every 6 days");
                    displayNotification();
                } else {
                    frequencyResult.remove("Every 6 days");
                }
            }
        });

        every7days = findViewById(R.id.every7days);
        every7days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (every7days.isChecked()) {
                    frequencyResult.add("Every 7 days");
                    displayNotification();
                } else {
                    frequencyResult.remove("Every 7 days");
                }
            }
        });
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

    public void saveText() {
        preferences = getPreferences(MODE_PRIVATE);

    }

}

