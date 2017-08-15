package com.example.hafiz.runningmeter;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {

    private int seconds=0;
    private boolean running;
    GPSTracker gps;
    double x1,y1,x2,y2;
    float distance=0;
    private boolean run;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        runTimer();
    }



    public void onClickSt(View view){

        running = true;
        run= true;
        distance = 0;
        update();


    }

    public void onClickStop(View view){

        running = false;
        run = false;

        Log.w("finish", seconds+"");

        Toast.makeText(getApplicationContext(), "distance: "+distance+" Speed: "+distance/seconds, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(Dashboard.this, ActivityTiming.class);
        intent.putExtra("distance", distance);
        intent.putExtra("seconds", seconds);
        startActivity(intent);

        seconds=0;
        distance=0;
    }


    public void runTimer(){

        final TextView textView = (TextView) findViewById(R.id.textView2);

        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {

                int hour = seconds/3600;
                int min = (seconds%3600)/60;
                int sec = seconds % 60;

                String time = String.format("%02d:%02d:%02d",hour,min,sec);

                textView.setText(time);
                //Log.w("running", seconds+"");

                if(running){
                    seconds++;
                }

                handler.postDelayed(this,1000);
            }
        });

    }



    public void update(){

        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {

                gps = new GPSTracker(Dashboard.this);

                // check if GPS enabled
                if(gps.canGetLocation() && run==true){
                    x1=x2;
                    y1=y2;
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    x2=latitude;
                    y2=longitude;

                    loc(x1,y1,x2,y2); //calculate distace
                    // \n is for new line
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    //gps.showSettingsAlert();
                    //gps.stopUsingGPS();
                }

                handler.postDelayed(this,20000);
            }
        });

    }


    void loc(double x1,double y1,double x2,double y2){

        Location locationA = new Location("point A");

        locationA.setLatitude(x1);
        locationA.setLongitude(y1);

        Location locationB = new Location("point B");

        locationB.setLatitude(x2);
        locationB.setLongitude(y2);

        float t =   locationA.distanceTo(locationB);

        if(t>700) t = 6;

        distance = distance + t;

    }


}
