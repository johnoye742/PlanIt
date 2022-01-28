package com.johnoye742.planit;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

import java.util.Random;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent i1) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context, new Random().nextInt(300), Toast.LENGTH_SHORT).show();
        String task = i1.getStringExtra("task");
        String desc = i1.getStringExtra("descr");

        Notification.Builder nb = new Notification.Builder(context);
        nb.setContentTitle("Hey! It's time to do : " + task);
        nb.setContentText(desc);
        nb.setPriority(Notification.PRIORITY_MAX);
        nb.setStyle(new Notification.BigTextStyle().bigText(desc));
        nb.setSmallIcon(R.drawable.ic_baseline_access_time_24);
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(new Random().nextInt(300), nb.build());


    }
}