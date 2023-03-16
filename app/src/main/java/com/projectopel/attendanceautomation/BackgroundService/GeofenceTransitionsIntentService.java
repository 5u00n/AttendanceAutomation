package com.projectopel.attendanceautomation.BackgroundService;

import android.app.IntentService;
import android.content.Intent;

import com.google.android.gms.location.GeofencingEvent;

public class GeofenceTransitionsIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public GeofenceTransitionsIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        GeofencingEvent event = GeofencingEvent.fromIntent(intent);
        // Handle the geofence transition event using the GeofenceBroadcastReceiver
        // as described above
    }
}