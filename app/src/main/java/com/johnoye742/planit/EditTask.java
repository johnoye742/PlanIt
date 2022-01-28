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

public class EditTask extends Activity {
Calendar cal;
String AM_PM;
int ap;
String title;
String descr;
String time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        Toolbar tb = findViewById(R.id.tb2);
        tb.setTitle("Edit Task");
        setActionBar(tb);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        tb.setNavigationOnClickListener((v) -> finish());
        EditText txt1 = findViewById(R.id.txt1);
        EditText txt2 = findViewById(R.id.txt2);
        EditText txt3 = findViewById(R.id.txt3);
        Button btn = findViewById(R.id.save);
        FrameLayout frl = findViewById(R.id.frl);
        Button done = findViewById(R.id.done);
        PlanDatabase pd = new PlanDatabase(this);
        cal = Calendar.getInstance();
        TimePicker tp = findViewById(R.id.tp);
        tp.setIs24HourView(true);
        tp.setHour(cal.get(Calendar.HOUR_OF_DAY));
        tp.setMinute(cal.get(Calendar.MINUTE));
        txt2.setFocusable(false);


        txt2.setOnClickListener((v2) -> {
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
                cal.set(Calendar.HOUR, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                cal.set(Calendar.AM_PM, ap);
                String s = a + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + " " + AM_PM;
                txt2.setText(s);
            });
        }  );
        done.setOnClickListener((v4) -> {
            frl.setVisibility(View.GONE);
        });
        Intent i = getIntent();
        if(i.getAction().equals("com.johnoye742.planit.edit")) {
            txt1.setText(i.getStringExtra("title"));
            txt2.setText(i.getStringExtra("time"));
            txt3.setText(i.getStringExtra("descr"));
            title = i.getStringExtra("title");
            descr = i.getStringExtra("descr");
            time = i.getStringExtra("time");


        } else {
            finish();
        }
        btn.setOnClickListener((v) -> {
            if(!txt1.getText().toString().isEmpty() && !txt2.getText().toString().isEmpty() && !txt3.getText().toString().isEmpty()) {
              pd.edit(title, descr, time, txt1.getText().toString(), txt3.getText().toString(), txt2.getText().toString());
              Toast.makeText(EditTask.this, "Edited", Toast.LENGTH_SHORT).show();

                AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent i2 = new Intent(EditTask.this, AlarmReceiver.class);
                i2.putExtra("task", txt1.getText().toString());
                i2.putExtra("descr", txt3.getText().toString());
                PendingIntent pendingIntent = PendingIntent.getBroadcast(EditTask.this, 0, i2, 0);
                am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
                Intent i3 = new Intent(EditTask.this, MainActivity.class);
                startActivity(i3);
            } else {
                Toast.makeText(EditTask.this, "Please make sure you fill all fields!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}