package com.example.admin.projectandr;

//Game activity ... blank text view, update as needed
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class GameActivity extends AppCompatActivity {

    SharedPreferences sharedPref = MainActivity.sharedPref;
    SharedPreferences.Editor editor = sharedPref.edit();

    long start = 0;
    int end = 0;
    private Button Mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button Mode = findViewById(R.id.Mode);

        /*Mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, ModeActivity.class);
                startActivity(intent);
            }
        });*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_timed);
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

