package com.example.kaylee.pilot;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class EnterLocation extends AppCompatActivity {

    private Integer miles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_location);

        Button backButton = (Button)findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent mainIntent = new Intent(EnterLocation.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });

        Button nextButton = (Button)findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataStore.getInstance().setDistance(miles = Integer.parseInt(((EditText)findViewById(R.id.distance)).getText().toString()));

                if (miles.equals("")) {
                    Toast.makeText(EnterLocation.this, "You left one of the fields blank", Toast.LENGTH_SHORT).show();
                    return;
                }
                final Intent mainIntent = new Intent(EnterLocation.this, CarSettings.class);
                startActivity(mainIntent);
            }
        });

//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                // All your networking logic
//                // should be here
//
//                // Create URL
//                URL githubEndpoint = null; //google maps api
//                try {
//                    githubEndpoint = new URL("");
//
//                    HttpsURLConnection myConnection = (HttpsURLConnection) githubEndpoint.openConnection();
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e){
//
//                }
//
//            }
//        });
    }

    //TODO hook in google API to get locations
}
