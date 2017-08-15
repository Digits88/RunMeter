package com.example.hafiz.runningmeter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ActivityTiming extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timing);
        Bundle bundle = getIntent().getExtras();
        float distance = bundle.getFloat("distance");
        int seconds  = bundle.getInt("seconds");

        int hour = seconds/3600;
        int min = (seconds%3600)/60;
        int sec = seconds % 60;

        String time = String.format("%02d:%02d:%02d",hour,min,sec);
        String speed = String.format("%.2f km/h",(distance/seconds)*3.6);

        TextView tex = (TextView) findViewById(R.id.ttime);
        TextView text = (TextView) findViewById(R.id.avgspeed);
        TextView te = (TextView) findViewById(R.id.tedis);
        TextView ca = (TextView) findViewById(R.id.cal);

        tex.setText(time+"");
        text.setText(speed);
        te.setText(distance+" m");

        double u = (distance*60)/seconds;
        double p = ((3.5+(u*.2)+(u*.02*.9))*70)/3.5;

        double z = (p/3600)*seconds;

        ca.setText(z+" cals");

    }







}
