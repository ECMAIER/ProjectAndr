package com.example.admin.projectandr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TimedActivity extends AppCompatActivity {

    SharedPreferences sharedPref = MainActivity.sharedPref;
    SharedPreferences.Editor editor = sharedPref.edit();
    private TextView txtSecond;
    public long save = 5000;
    CountDownTimer timer = null;
    long start = 0;
    int end = 0;

    @Override
    protected void onStart(){
        super.onStart();
        setContentView(R.layout.activity_timed);
        start = SystemClock.currentThreadTimeMillis();

        txtSecond = findViewById(R.id.txtSecond);
        timer = new CountDownTimer(save, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtSecond.setText("seconds remaining: " + (millisUntilFinished / 1000));
                save = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(TimedActivity.this, MainActivity.class);
                startActivity(intent);
            }
        };
        timer.start();

    }

    @Override
    protected void onStop()
    {
        super.onStop();
        end = (int)(start - SystemClock.currentThreadTimeMillis());
        editor.putInt(getString(R.string.total_time_played), end + sharedPref.getInt(getString(R.string.total_time_played ) ,0));
        editor.apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timed);

    }

    @Override
    protected void onPause(){
        super.onPause();
        timer.cancel();
    }

    @Override
    protected void onResume(){
        super.onResume();
        timer.start();
    }

}
