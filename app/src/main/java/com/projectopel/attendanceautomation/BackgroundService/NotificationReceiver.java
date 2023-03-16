package com.projectopel.attendanceautomation.BackgroundService;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;

public class NotificationReceiver extends BroadcastReceiver {
    public NotificationReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.bindService(intent, new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    //retrieve an instance of the service here from the IBinder returned
                    //from the onBind method to communicate with
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                }
            }, Context.BIND_AUTO_CREATE);
        } else {
            context.startService(new Intent(context, TimerNewIntentService.class));
        }

    }
}