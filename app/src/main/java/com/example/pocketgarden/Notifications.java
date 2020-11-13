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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.ArrayList;

public class Notifications extends AppCompatActivity {

    private static final String CHANNEL_ID = "pocket_garden";
    private static final String CHANNEL_NAME = "Pocket Garden";
    private static final String CHANNEL_DESC = "Pocket Garden Notifications";

    private CheckBox never, every1day, every2days, every3days, every4days, every5days, every6days, every7days;
    private ArrayList<String> frequencyResult = new ArrayList<String>();
    private Button notificationButton;
    private boolean checked;

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

        SharedPreferences sharedPref = Notifications.this.getPreferences(Context.MODE_PRIVATE);
        boolean isMyValueChecked = sharedPref.getBoolean("checkbox", false);
        never = findViewById(R.id.never);
        never.setChecked(isMyValueChecked);
        never.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("never", ((CheckBox) view).isChecked());
                editor.apply();
            }
        });

        every1day = findViewById(R.id.every1day);
        every1day.setChecked(isMyValueChecked);
        every1day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("every 1 day", ((CheckBox) view).isChecked());
                editor.apply();
            }
        });

        every2days = findViewById(R.id.every2days);
        every2days.setChecked(isMyValueChecked);
        every2days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("every 2 days", ((CheckBox) view).isChecked());
                editor.apply();
            }
        });

        every3days = findViewById(R.id.every3days);
        every3days.setChecked(isMyValueChecked);
        every3days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("every 3 days", ((CheckBox) view).isChecked());
                editor.apply();
            }
        });

        every4days = findViewById(R.id.every4days);
        every4days.setChecked(isMyValueChecked);
        every4days.setOnClickListener(view -> {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean("every 4 days", ((CheckBox) view).isChecked());
            editor.commit();
        });

        every5days = findViewById(R.id.every5days);
        every5days.setChecked(isMyValueChecked);
        every5days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("every 5 days", ((CheckBox) view).isChecked());
                editor.commit();
            }
        });

        every6days = findViewById(R.id.every6days);
        every6days.setChecked(isMyValueChecked);
        every6days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("every 6 days", ((CheckBox) view).isChecked());
                editor.commit();
            }
        });

        every7days = findViewById(R.id.every7days);
        every7days.setChecked(isMyValueChecked);
        every7days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("every 7 days", ((CheckBox) view).isChecked());
                editor.commit();
            }
        });
    }

    public void onCheckboxClicked(View view) {

        frequencyResult = new ArrayList<>();
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.never:
                if (checked) {
                    frequencyResult.add("Never");
                    displayNotification();
                    Toast.makeText(Notifications.this, "never clicked", Toast.LENGTH_SHORT).show();
                } else {
                    frequencyResult.remove("Never");
                }
                break;
            case R.id.every1day:
                if (checked) {
                    frequencyResult.add("Every 1 day");
                    displayNotification();
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

}

