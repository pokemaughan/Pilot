package com.example.kaylee.pilot;

import android.content.Intent;
import android.os.AsyncTask;
import android.provider.*;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Airfare extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airfare);

        Button backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent mainIntent = new Intent(Airfare.this, CarSettings.class);
                startActivity(mainIntent);
            }
        });

        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        String APIkey = "AIzaSyCOHYhed9jaSFYkuRLCMm_7YbQmWPQj0m0";

                        URL airfareEndpoint = null; //google maps api
                        try {
                            airfareEndpoint = new URL("https://www.googleapis.com/qpxExpress/v1/trips/search");

                            HttpsURLConnection myConnection = (HttpsURLConnection) airfareEndpoint.openConnection();
                            myConnection.setRequestMethod("POST");
                            myConnection.setRequestProperty("key", APIkey);
                            myConnection.setDoOutput(true);
                            myConnection.connect();

                            OutputStream requestBody = myConnection.getOutputStream();
                            OutputStreamWriter osWriter = new OutputStreamWriter(requestBody);

                            osWriter.write(getFlightRequest()); //where the body goes
                            requestBody.close();

                            if (myConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                                InputStream responseBody = myConnection.getInputStream();
                                StringBuilder sb = new StringBuilder();
                                InputStreamReader isReader = new InputStreamReader(responseBody);
                                char[] buff = new char[1024];
                                int len;
                                while((len = isReader.read(buff))>0){
                                    sb.append(buff,0,len);
                                }
                                System.out.println(sb.toString());
                                JSONObject jObj = new JSONObject(sb.toString());
                                //JsonReader reader = new JsonReader(isReader);
                                parseJSON(jObj);
                            }else {
                                System.err.println("bad responds " + myConnection.getResponseMessage());
                            }
                        } catch (MalformedURLException e) {
                            Toast.makeText(Airfare.this, "I screwed up the URL lulz", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        } catch (IOException e) {
                            Toast.makeText(Airfare.this, "Invalid Airport Name", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        } catch (JSONException e) {
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                final Intent mainIntent = new Intent(Airfare.this, Calculating.class);
                startActivity(mainIntent);
            }
        });
    }

    private void parseJSON(JSONObject jsonO){
//        try {
//            jsonReader.beginObject(); // Start processing the JSON object
//            while (jsonReader.hasNext()) { // Loop through all keys
//                String key = jsonReader.nextName(); // Fetch the next key
//                if (key.equals("organization_url")) { // Check if desired key
//                    JSONObject empObj = jsonReader
//                    jsonReader.close();
//                } else {
//                    jsonReader.skipValue(); // Skip values of other keys
//                }
//            }
        try{
            String saleTotal = jsonO.getJSONObject("trips").getJSONArray("tripOption").getJSONObject(0).getString("saleTotal");
            String[] s = saleTotal.split("D");

            DataStore.getInstance().setTicketPrice(Double.parseDouble(s[1]));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //trips.tripOption.saleTotal
    }

    private String getFlightRequest(){
        String port1 = ((EditText)findViewById(R.id.port1)).getText().toString();
        String port2 = ((EditText)findViewById(R.id.port2)).getText().toString();
        String year = String.valueOf(((DatePicker)findViewById(R.id.date_picker)).getYear());
        String month = String.valueOf(((DatePicker)findViewById(R.id.date_picker)).getMonth());
        String day = String.valueOf(((DatePicker)findViewById(R.id.date_picker)).getDayOfMonth());

        StringBuilder sb = new StringBuilder();
        sb.append("{");
            sb.append("\"request\": {");
            sb.append("\"passengers\": {");
                sb.append("\"adultCount\": 1");
            sb.append("},");
            sb.append("\"slice\": [");
            sb.append("{");
                sb.append("\"origin\": \""+ port1 +"\",");
                sb.append("\"destination\": \""+ port2 +"\",");
                sb.append("\"date\": \""+ year + "-" + month + "-" + day +"\"");
            sb.append("}");
            sb.append("]");
        sb.append("}\";");

        return sb.toString();
    }
}
