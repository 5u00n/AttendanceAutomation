package com.projectopel.attendanceautomation.BackgroundService;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;

import com.projectopel.attendanceautomation.MainActivity;
import com.projectopel.attendanceautomation.R;

import androidx.core.app.NotificationManagerCompat;

public class TimerNewIntentService extends IntentService {
    private static final int NOTIFICATION_ID = 3;

    public TimerNewIntentService() {
        super("MyNewIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startForeground(1,new Notification());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("My Title");
        builder.setContentText("This is the Body");
        builder.setSmallIcon(R.drawable.logo);
        Intent notifyIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //to be able to launch your activity from the notification
        builder.setContentIntent(pendingIntent);
        Notification notificationCompat = builder.build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(NOTIFICATION_ID, notificationCompat);
    }
}