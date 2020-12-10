package com.example.pocketgarden.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.pocketgarden.R;

public class NotificationReceiver extends BroadcastReceiver {

    private static final String CHANNEL_ID = "pocket_garden";
    private static final String CHANNEL_NAME = "Pocket Garden";
    private static final String CHANNEL_DESC = "Pocket Garden Notifications";

    @Override
    public void onReceive(Context context, Intent intent) {
        buildNotification(context);
        displayNotification(context);
    }

    public void buildNotification (Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
    public void displayNotification(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID);
        builder.setContentTitle("Pocket Garden")
                .setContentText("Water your Plant!")
                .setSmallIcon(R.mipmap.logo_round)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.logo_round))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(1, builder.build());
    }
}
