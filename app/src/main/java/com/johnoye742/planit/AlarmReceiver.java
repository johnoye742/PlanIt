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
String[] names = new String[] {"John Oye", "Tayo Omotola", "Jubilee Assam", "Williams Olatunji", "Emeka Stephen Michael", "Melvin Jones Repol", "Ese Curtis", "Bakare Oluwanifemi", "Trust Akalonu", "Abraham Dominic Newton"};
    @Override
    public void onReceive(Context context, Intent i1) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.



        String task = i1.getStringExtra("task");
        String desc = "Click on the notification to see the task";

        Notification.Builder nb = new Notification.Builder(context);
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nb.setContentTitle("New Task!");
        nb.setContentText(desc);
        nb.setPriority(Notification.PRIORITY_MAX);
        nb.setStyle(new Notification.BigTextStyle().bigText(desc));
        nb.setColor(Color.rgb(33, 150, 243));

        nb.setSmallIcon(R.drawable.ic_baseline_access_time_24);
        Intent i2 = new Intent(context, AboutTask.class);
        i2.putExtra("title", "Hey! "+ names[new Random().nextInt(names.length)] +" says it's time to " + task);
        i2.putExtra("msg", i1.getStringExtra("descr"));
        
        PendingIntent pi2 = PendingIntent.getActivity(context, 0, i2, PendingIntent.FLAG_UPDATE_CURRENT);
        nb.setContentIntent(pi2);
        nb.setAutoCancel(true);
        nb.setVisibility(Notification.VISIBILITY_PUBLIC);
        Intent i = new Intent(context, DeleteService.class);

        i.putExtra("title", task);
        i.putExtra("descr", desc);
        PendingIntent pi = PendingIntent.getService(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        nb.addAction(new Notification.Action(R.drawable.ic_times, "Delete Task", pi));

        nm.notify(0, nb.build());
        
        MediaPlayer mp = MediaPlayer.create(context, R.raw.sound);
        mp.start();

    }
}