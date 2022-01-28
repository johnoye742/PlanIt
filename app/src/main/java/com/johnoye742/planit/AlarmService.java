package com.johnoye742.planit;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.app.NotificationManager;

public class AlarmService extends Service {
    IBinder ib;
    Intent i1;
    public AlarmService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return ib;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.i1 = intent;


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();



    }
}