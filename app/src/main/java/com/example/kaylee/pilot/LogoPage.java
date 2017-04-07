package com.example.kaylee.pilot;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class LogoPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo_page);

        //Remove title bar
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        new Handler().postDelayed(new Runnable() { // go to Main Activity after 5 seconds
            @Override
            public void run() {
                final Intent mainIntent = new Intent(LogoPage.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, 5000);
    }




}
