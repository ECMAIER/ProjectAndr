package com.example.admin.projectandr;

//Coop activity... text view only, update as needed

import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CoopActivity extends AppCompatActivity {
    SharedPreferences sharedPref = MainActivity.sharedPref;
    SharedPreferences.Editor editor = sharedPref.edit();
    long start = 0;
    int end = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coop);
    }
    @Override
    protected void onStart(){
        super.onStart();
        start = SystemClock.currentThreadTimeMillis();

    }
    @Override
    protected void onStop()
    {
        super.onStop();
        end = (int)(start - SystemClock.currentThreadTimeMillis());
        editor.putInt(getString(R.string.total_time_played), end + sharedPref.getInt(getString(R.string.total_time_played ) ,0));
        editor.apply();
    }
}
