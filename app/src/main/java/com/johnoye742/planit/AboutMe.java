package com.johnoye742.planit;

import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;


public class AboutMe extends Activity {
    TextView tv1;
    TextView tv2;
    TextView tv3;
    Typeface tf;
    ImageView imv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a3);
        Toolbar tb1 = findViewById(R.id.tb1);
        setActionBar(tb1);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        tb1.setNavigationOnClickListener((v) -> finish());
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        int screenSize = screenWidth * screenHeight;
          tv1 = findViewById(R.id.tv1);
          tv2 = findViewById(R.id.tv2);
          tv3 = findViewById(R.id.tv3);
          imv = findViewById(R.id.imv);

          imv.setMaxWidth(screenSize/8000);
          imv.setMaxHeight(screenSize/8000);
          tf = Typeface.createFromAsset(getAssets(), "fonts/MavenPro.ttf");
          tv1.setTypeface(tf);
          tv2.setTypeface(tf);
          tv3.setTypeface(tf);
        ((TextView) findViewById(R.id.tv4)).setTypeface(tf);
    }
}