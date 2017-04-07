package com.example.kaylee.pilot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CarSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_settings);

        Button backButton = (Button)findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent mainIntent = new Intent(CarSettings.this, EnterLocation.class);
                startActivity(mainIntent);
            }
        });

        Button nextButton = (Button)findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataStore.getInstance().setMPG(MPG = Integer.parseInt(((EditText)findViewById(R.id.mpg)).getText().toString()));
                DataStore.getInstance().setGasPrice(gasPrice = Double.parseDouble(((EditText)findViewById(R.id.gas_price)).getText().toString()));

                if (MPG == 0 ||gasPrice == 0.0) {
                    Toast.makeText(CarSettings.this, "You left one of the fields blank", Toast.LENGTH_SHORT).show();
                    return;
                }

                final Intent mainIntent = new Intent(CarSettings.this, Airfare.class);
                startActivity(mainIntent);
            }
        });
    }

    private Integer MPG = 0;
    private Double gasPrice = 0.0;
    //TODO save information about the car - calculate the money for gas -- does google API know the gas prices?
}
