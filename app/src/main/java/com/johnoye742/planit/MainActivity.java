package com.johnoye742.planit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
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
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            tf = Typeface.createFromAsset(getAssets(), "fonts/MavenPro.ttf");
            tb = findViewById(R.id.tb);
            lv = findViewById(R.id.lv);
            fl = findViewById(R.id.button);
            tv = findViewById(R.id.subtitle);
            rl = findViewById(R.id.rl);
            tb.setTitle(R.string.plan_it_by_john_oye);
            setActionBar(tb);
            
            tv.setTypeface(tf);
            ((TextView) findViewById(R.id.title)).setTypeface(tf);
            pd = new PlanDatabase(this);
            fl.setOnClickListener((v) -> {
              Intent i = new Intent(MainActivity.this, AddTask.class);
              startActivity(i);
            });
            SQLiteDatabase sld = pd.getReadableDatabase();
            al = new ArrayList<>();
            pla = new PlanListAdapter(this, al);
            Cursor c = sld.rawQuery("SELECT * FROM plans", null);

                if(c != null && c.getCount() > 0) {

                    while(c.moveToNext()) {
                        al.add(new PlanDataModel(c.getString(1), c.getString(2), c.getString(3)));
                    }
                    c.close();
                } else {
                    Toast.makeText(this, "No plans have been added yet.\nClick the + button to add an item!", Toast.LENGTH_SHORT).show();
                }

                if(al.size() != 0) {
                    tv.setVisibility(View.GONE);
                    lv.setVisibility(View.VISIBLE);
                    lv.setAdapter(pla);
                }



            
        } catch (Exception ignored) {

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
            case R.id.reload:
                Toast.makeText(this, "Reloading...", Toast.LENGTH_SHORT).show();
                recreate();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}