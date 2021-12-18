package com.johnoye742.planit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class MainActivity extends Activity {
    Toolbar tb;
    ListView lv;
    FrameLayout fl;
    PlanDatabase pd;
    SQLiteDatabase sld;
    ArrayList<PlanDataModel> al;
    PlanListAdapter pla;
    View view;
    TextView tv;
    Typeface tf;
    RelativeLayout rl;
   
    @SuppressLint({"NonConstantResourceId", "InflateParams"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            tf = Typeface.createFromAsset(getAssets(), "fonts/MavenPro.ttf");
            tb = findViewById(R.id.tb);
            lv = findViewById(R.id.lv);
            fl = findViewById(R.id.button);
            tv = findViewById(R.id.subtitle);
            rl = findViewById(R.id.rl);
            tv.setTypeface(tf);
            ((TextView) findViewById(R.id.title)).setTypeface(tf);
            pd = PlanDatabase.getInstance(this);
            fl.setOnClickListener((v) -> {
             Toast.makeText(this, Long.toString(pd.add("he", "kd", "ew")), Toast.LENGTH_SHORT).show();
            });
            SQLiteDatabase sld = pd.getReadableDatabase();
            Cursor c = sld.rawQuery("SELECT * FROM plans", null);

                if(c != null && c.getCount() > 0) {
                    while (c.moveToNext()) {
                        Toast.makeText(this, c.getString(2), Toast.LENGTH_SHORT).show();
                        tv.setText(c.getString(2));
                    }
                    c.close();
                }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                Intent i = new Intent(this, AboutMe.class);
                startActivity(i);
                break;
            case R.id.exit:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}