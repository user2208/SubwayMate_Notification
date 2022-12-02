package com.example.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {
    public static final String channel1Id = "channelID";
    public static final String channel1Name = "channel1";

    private NotificationManager manager;

    public NotificationHelper(Context base) {
        super(base);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannels();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createChannels() {

        NotificationChannel channel1 = new NotificationChannel(channel1Id, channel1Name, NotificationManager.IMPORTANCE_DEFAULT);
        channel1.enableLights(true);
        channel1.enableVibration(true);
        channel1.setLightColor(com.google.android.material.R.color.design_default_color_primary);
        channel1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(channel1);
    }

    public NotificationManager getManager() {
        if(manager == null) {
            manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return manager;
    }

    public NotificationCompat.Builder getChannel1Notification(String start, String end, String time, String station) {
        return new NotificationCompat.Builder(getApplicationContext(), channel1Id)
                .setContentTitle(start)
                .setContentText(end)
                //.setContentText(message + "\n" + time + "\n" + station)
                .setStyle(new NotificationCompat.BigTextStyle()
                    .bigText(time + "\n" + station))
                .setSmallIcon(R.drawable.ic_launcher_background);
    }
}
