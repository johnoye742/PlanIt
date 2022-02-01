package com.johnoye742.planit;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.*;

import java.util.Random;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent i1) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context, "new Random().nextInt(300)", Toast.LENGTH_SHORT).show();


        String task = i1.getStringExtra("task");
        String desc = i1.getStringExtra("descr");

        Notification.Builder nb = new Notification.Builder(context);
        nb.setContentTitle("Hey! It's time to do : " + task);
        nb.setContentText(desc);
        nb.setPriority(Notification.PRIORITY_MAX);
        nb.setStyle(new Notification.BigTextStyle().bigText(desc));
        nb.setColor(Color.rgb(33, 150, 243));

        nb.setSmallIcon(R.drawable.ic_baseline_access_time_24);
        Intent i = new Intent(context, DeleteService.class);
        i.putExtra("title", task);
        i.putExtra("descr", desc);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        nb.addAction(new Notification.Action(R.drawable.ic_times, "Delete Task", pi));

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(742, nb.build());
        
        MediaPlayer mp = MediaPlayer.create(context, R.raw.sound);
        mp.start();

    }
}