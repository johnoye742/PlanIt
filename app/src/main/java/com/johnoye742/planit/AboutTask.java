package com.johnoye742.planit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class AboutTask extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_task);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/MavenPro.ttf");
        TextView jt1 = findViewById(R.id.jt1);
        TextView jt2 = findViewById(R.id.jt2);
        jt1.setTypeface(tf);
        jt2.setTypeface(tf);
        Toolbar jtb = findViewById(R.id.jtb);
        setActionBar(jtb);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        jtb.setNavigationIcon(R.drawable.ic_times);
        jtb.setNavigationOnClickListener((v) -> {
            finish();
        });
        Intent i = getIntent();
        jt1.setText(i.getStringExtra("title"));
        jt2.setText(i.getStringExtra("msg"));
        Button btn = findViewById(R.id.del);
        btn.setOnClickListener((v) -> {
            PlanDatabase pd = new PlanDatabase(AboutTask.this);
            pd.delete(i.getStringExtra("title"), i.getStringExtra("msg"));
            Toast.makeText(getApplicationContext(), "Task has been deleted", Toast.LENGTH_SHORT).show();
        });
    }
}