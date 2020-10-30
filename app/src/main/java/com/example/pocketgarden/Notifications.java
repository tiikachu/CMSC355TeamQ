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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.ArrayList;

public class Notifications extends AppCompatActivity {

    private static final String CHANNEL_ID = "pocket_garden";
    private static final String CHANNEL_NAME = "Pocket Garden";
    private static final String CHANNEL_DESC = "Pocket Garden Notifications";
    private final String NOTIF_PREF = "Notification References";
    CheckBox never, every1day, every2days, every3days, every4days, every5days, every6days, every7days;
    private ArrayList<String> frequencyResult;
    private Button notificationButton, saveButton;
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

        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        loadData();
        updateViews();

    }

    public void onCheckboxClicked(View view) {

        frequencyResult = new ArrayList<>();
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.never:
                if (checked) {
                    frequencyResult.add("Never");
                    displayNotification();
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

    public void saveData() {
        SharedPreferences preferences = getSharedPreferences(NOTIF_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("never", never.isChecked());
        editor.apply();
        Toast.makeText(this, "Settings saved", Toast.LENGTH_SHORT).show();

    }

    public void loadData() {
        SharedPreferences preferences = getSharedPreferences(NOTIF_PREF, MODE_PRIVATE);
        checked = preferences.getBoolean("checked", false);
    }

    //could just pass in checkbox as a parameter
    public void updateViews() {
        never.setChecked(checked);
        every1day.setChecked(checked);
        every2days.setChecked(checked);
        every3days.setChecked(checked);
        every4days.setChecked(checked);
        every5days.setChecked(checked);
        every6days.setChecked(checked);
        every7days.setChecked(checked);
    }
}

