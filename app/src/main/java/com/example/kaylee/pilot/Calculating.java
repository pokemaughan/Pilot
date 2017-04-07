package com.example.kaylee.pilot;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Calculating extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculating);


        new Handler().postDelayed(new Runnable() { // go to Main Activity after 5 seconds
            @Override
            public void run() {

                if(DataStore.getInstance().calculate().equals("Car")) {
                    final Intent mainIntent = new Intent(Calculating.this, DriveResults.class);
                    startActivity(mainIntent);
                }
                else{
                    final Intent mainIntent = new Intent(Calculating.this, FlyResults.class);
                    startActivity(mainIntent);
                }
                finish();
            }
        }, 5000);
    }
}
