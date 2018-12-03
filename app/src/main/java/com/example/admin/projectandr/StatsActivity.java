package com.example.admin.projectandr;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class StatsActivity extends AppCompatActivity {
    SharedPreferences sharedPref = MainActivity.sharedPref;
    public TextView totalplaytime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        //DOES NOT PRINT SET.TEXT
        totalplaytime = findViewById(R.id.totalplaytime);
        totalplaytime.setText("3");//String.valueOf(sharedPref.getInt(getString(R.string.total_time_played) ,0)));
    }

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_stats);


    }
}
