package com.example.pocketgarden;

import android.annotation.SuppressLint;
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
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.ArrayList;
import plant.*;

public class Notifications extends AppCompatActivity implements View.OnClickListener {

    Context context;
    private static final String CHANNEL_ID = "pocket_garden";
    private static final String CHANNEL_NAME = "Pocket Garden";
    private static final String CHANNEL_DESC = "Pocket Garden Notifications";

    private CheckBox never, every1day, every2days, every3days, every4days, every5days, every6days, every7days;
    private ArrayList<String> frequencyResult = new ArrayList<String>();
    private TextView textView;
//    private boolean checkedOnOff;
//    private Button saveButton;
//
//    public static final String SHARED_PREFS = "sharedPrefs";
//    public static final String CHECKBOX = "checkbox";
//    SharedPreferences sharedPref;
    PlantObject plant;

    @SuppressLint("SetTextI18n")
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
//
//        sharedPref = Notifications.this.getPreferences(Context.MODE_PRIVATE);
//        boolean isMyValueChecked = sharedPref.getBoolean("checkbox", false);

        never = findViewById(R.id.never);
        every1day = findViewById(R.id.every1day);
        every2days = findViewById(R.id.every2days);
        every3days = findViewById(R.id.every3days);
        every4days = findViewById(R.id.every4days);
        every5days = findViewById(R.id.every5days);
        every6days = findViewById(R.id.every6days);
        every7days = findViewById(R.id.every7days);
        textView  = findViewById(R.id.plant);

//        never.setChecked(isMyValueChecked);
//        every1day.setChecked(isMyValueChecked);
//        every2days.setChecked(isMyValueChecked);
//        every3days.setChecked(isMyValueChecked);
//        every4days.setChecked(isMyValueChecked);
//        every5days.setChecked(isMyValueChecked);
//        every6days.setChecked(isMyValueChecked);
//        every7days.setChecked(isMyValueChecked);

        never.setOnClickListener(this);
        every1day.setOnClickListener(this);
        every2days.setOnClickListener(this);
        every3days.setOnClickListener(this);
        every4days.setOnClickListener(this);
        every5days.setOnClickListener(this);
        every6days.setOnClickListener(this);
        every7days.setOnClickListener(this);

  //      updateText();

    }

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
        displayNotification();

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

//    @Override
//    protected void onResume() {
//        super.onResume();
//        never.setChecked(never.isChecked());
//    }

    public void createCardView() {
    }


//    @SuppressLint("SetTextI18n")
//    public void updateText () {
//        PlantCare plant = new PlantCare();
//        if (plant.id.equals("p1")) {
//            textView.setText("Sample Plant 1");
//        }
//        else if (plant.id.equals("p2")) {
//            textView.setText("Sample Plant 2");
//        }
//        else if (plant.id.equals("p3")) {
//            textView.setText("Sample Plant 3");
//        }
//        else if (plant.id.equals("p4")) {
//            textView.setText("Sample Plant 4");
//        }
//
//    }
}


