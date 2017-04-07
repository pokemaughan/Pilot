package com.example.kaylee.pilot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newTripButton = (Button)findViewById(R.id.new_trip);
        newTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent mainIntent = new Intent(MainActivity.this, EnterLocation.class);
                startActivity(mainIntent);
            }
        });

        Button signInButton = (Button)findViewById(R.id.sign_in);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent mainIntent = new Intent(MainActivity.this, SignIn.class);
                startActivity(mainIntent);
            }
        });

        Button registerButton = (Button)findViewById(R.id.register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent mainIntent = new Intent(MainActivity.this, Register.class);
                startActivity(mainIntent);
            }
        });

        Button settingsButton = (Button)findViewById(R.id.settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Disabled For this Phase", Toast.LENGTH_LONG).show();
//                final Intent mainIntent = new Intent(MainActivity.this, Settings.class);
//                startActivity(mainIntent);
                //TODO enable this
            }
        });
    }
}
