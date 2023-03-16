package com.projectopel.attendanceautomation.BackgroundService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import static android.content.ContentValues.TAG;

public class GeofenceBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        GeofencingEvent event = GeofencingEvent.fromIntent(intent);
        if (event.hasError()) {
            Log.e(TAG, "GeofencingEvent error: " + event.getErrorCode());
            return;
        }

        int transitionType = event.getGeofenceTransition();
        if (transitionType == Geofence.GEOFENCE_TRANSITION_ENTER) {
            // The device has entered the geofence
            // Do something, like display a notification
            Log.d("Geofence Transition ++++","Geofence Enter");
        } else if (transitionType == Geofence.GEOFENCE_TRANSITION_EXIT) {
            // The device has exited the geofence
            // Do something else, like stop displaying the notification
            Log.d("Geofence Transition ++++","Geofence Exit");
        }else if(transitionType== Geofence.GEOFENCE_TRANSITION_DWELL){
            Log.d("Geofence Transition ++++","Geofence Dwell");

        }
    }
}
