package com.uber.CookieCrime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Main extends Activity {
    //public final static String EXTRA_MESSAGE = "com.uber.CookieCrime.MESSAGE";
    public final static String URL = "http://data.police.uk/api/crimes-at-location?date=2012-02&location_id=54312";
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(URL, message);
        startActivity(intent);
    }

}


