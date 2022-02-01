package com.johnoye742.planit;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class AddTask extends Activity {
    String AM_PM;
    Calendar cal;
    int ap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Toolbar tb = findViewById(R.id.tb2);
        tb.setTitle("Add Task");

        setActionBar(tb);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        tb.setNavigationOnClickListener((v) -> finish());
        TextView task = findViewById(R.id.txt1);
        TextView time = findViewById(R.id.txt2);
        TextView description = findViewById(R.id.txt3);
        Button save = findViewById(R.id.save);
        FrameLayout frl = findViewById(R.id.frl);
        Button done = findViewById(R.id.done);
        cal = Calendar.getInstance();
        TimePicker tp = findViewById(R.id.tp);
         tp.setIs24HourView(true);
        tp.setHour(cal.get(Calendar.HOUR_OF_DAY));
        tp.setMinute(cal.get(Calendar.MINUTE));
        time.setFocusable(false);


        time.setOnClickListener((v2) -> {
            frl.setVisibility(View.VISIBLE);
            tp.setOnTimeChangedListener((view, hourOfDay, minute) -> {
                String a = "0";
                if(hourOfDay < 12) {
                    AM_PM = "AM";
                    ap = 0;
                    a = "0";
                } else {

                    AM_PM = "PM";
                    ap = 1;
                    a = "";
                }
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                cal.set(Calendar.AM_PM, ap);
                String s = cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + " " + AM_PM;
                time.setText(s);
            });
        }  );
        done.setOnClickListener((v4) -> {
            frl.setVisibility(View.GONE);
        });
        save.setOnClickListener((v3) -> {
            PlanDatabase pd = new PlanDatabase(AddTask.this);
            if (!task.getText().toString().isEmpty() && !description.getText().toString().isEmpty() && !time.getText().toString().isEmpty()) {
                long l = pd.add(task.getText().toString(), description.getText().toString(), time.getText().toString());
                Toast.makeText(AddTask.this, Long.toString(l), Toast.LENGTH_SHORT).show();
                AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent i2 = new Intent(AddTask.this, AlarmReceiver.class);
                i2.putExtra("task", task.getText().toString());
                i2.putExtra("descr", description.getText().toString());

                PendingIntent pendingIntent = PendingIntent.getBroadcast(AddTask.this, (int) System.currentTimeMillis(), i2, PendingIntent.FLAG_UPDATE_CURRENT);
                am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);

                Intent i = new Intent(AddTask.this, MainActivity.class);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(AddTask.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }
}