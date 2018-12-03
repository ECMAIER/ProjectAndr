package com.example.admin.projectandr;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SurvivorActivity extends AppCompatActivity {

    SharedPreferences sharedPref = MainActivity.sharedPref;
    SharedPreferences.Editor editor = sharedPref.edit();
    float start = 0;
    float end = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survivor);

    }

    @Override
    protected void onStart(){
        super.onStart();
        start = System.currentTimeMillis();
    }
    @Override
    protected void onStop() {
        super.onStop();
        end = System.currentTimeMillis() - start;
        int playtime = (int)(end + sharedPref.getInt(getString(R.string.total_time_played),0));
        editor.putInt(getString(R.string.total_time_played), playtime );
        editor.apply();
    }
}
