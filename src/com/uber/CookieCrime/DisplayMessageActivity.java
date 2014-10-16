package com.uber.CookieCrime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONArray;

/**
 * Created by nm911_000 on 14/10/2014.
 */
public class DisplayMessageActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Blocks the main thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final String URL = "http://data.police.uk/api/crimes-at-location?date=2012-02&lat=52.629729&lng=-1.131593";

        // Get the message from the intent
        Intent intent = getIntent();
        String url = intent.getStringExtra(Main.URL);
        String input = "";
        String result ="";

        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(URL);
            ResponseHandler<String> handler = new BasicResponseHandler();
            Log.d("Connection", "Connection established!");
            try {
                input = client.execute(get, handler);
                Log.d("input", "Input client created!");
            }
            catch (Exception e) {
                Log.d("SmallCatch", e.toString());
            }
        }
        catch (Exception e) {
            Log.d("BigCatch", e.toString());
        }

        try {
            String category;
            String locationType;
            Double latitude;
            Double longitude;
            JSONObject location;
            JSONObject street;
            String streetName;

            JSONArray array = new JSONArray(input);
            for (int i = 0; i < array.length(); i++) {
                JSONObject row = array.getJSONObject(i);
                category = row.getString("category");
                locationType = row.getString("location_type");
                location = row.getJSONObject("location");
                latitude = location.getDouble("latitude");
                longitude = location.getDouble("longitude");
                street = location.getJSONObject("street");
                streetName = street.getString("name");

                result += "Category: " + category.toUpperCase() + ";\n" + "Location: " + locationType + ";\n" +
                        "Street: " + streetName + "\n" + "Lat: " + latitude + ";\n" + "Long: " + longitude + ".\n\n";

                Log.d("Location", location.toString());
            }

        }
        catch (Exception e) {
            Log.d("JSONParsingException", e.toString());
        }


        // Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(20);
        if (result != "") {
            textView.setText(result);
        } else {
            textView.setText("The string is empty. Is not JSON, boss!");
        }

        // Set the text view as the activity layout
        setContentView(textView);
    }

}