package com.example.pocketgarden;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    public boolean raining = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button notificationButton = findViewById(R.id.button);
        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNotification();
            }
        });


    }

    /**
     * Opens journal notes editor
     *
     * @param view
     */
    public void journalClick(View view) {
        Intent intent = new Intent(this, ShowJournals.class);
        startActivity(intent);
    }

    private void addNotification () {
        // build notification

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        if (raining) {
            builder.setContentTitle("Pocket Garden")
                    .setContentText("It is raining today");
        }

        // create intent to show notification
        Intent        notificationIntent = new Intent (this, MainActivity.class);
        PendingIntent contentIntent      = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // access device and tell it there's a notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());

    }

}