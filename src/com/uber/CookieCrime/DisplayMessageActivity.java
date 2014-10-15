package com.uber.CookieCrime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

        // Get the message from the intent
        Intent intent = getIntent();
        String url = intent.getStringExtra(Main.URL);
        String input = "";

        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(url);
            HttpResponse response = client.execute(get);
            ResponseHandler<String> handler = new BasicResponseHandler();

            try {
                input = client.execute(get, handler);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String category;
            String locationType;

            JSONArray array = new JSONArray(input);
            for (int i = 0; i <array.length(); i++) {
                JSONObject row = array.getJSONObject(i);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        //Create the URL for the HTTP request


        // Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        //textView.setText(message);

        // Set the text view as the activity layout
        setContentView(textView);
    }

}