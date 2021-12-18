package com.johnoye742.planit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

public class Splash extends Activity {
TextView tv1;
TextView tv2;
Typeface tf;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        tv1 = findViewById(R.id.text);
        tv2 = findViewById(R.id.text1);
        tf = Typeface.createFromAsset(getAssets(), "fonts/MavenPro.ttf");
        tv1.setTypeface(tf, Typeface.BOLD);
        tv2.setTypeface(tf, Typeface.BOLD);

        CountDownTimer cdt = new CountDownTimer(2700, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                finish();
                startActivity(new Intent(Splash.this, MainActivity.class));
            }
        };
        cdt.start();
    }
}