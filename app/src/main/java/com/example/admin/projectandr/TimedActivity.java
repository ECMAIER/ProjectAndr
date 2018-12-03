package com.example.admin.projectandr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TimedActivity extends AppCompatActivity {


    private TextView txtSecond;
    public long save = 5000;
    CountDownTimer timer = null;
    SharedPreferences sharedPref = MainActivity.sharedPref;
    SharedPreferences.Editor editor = sharedPref.edit();
    float start = 0;
    float end = 0;

    @Override
    protected void onStart(){
        super.onStart();
        setContentView(R.layout.activity_timed);
        start = System.currentTimeMillis();

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
        end = System.currentTimeMillis() - start;
        int playtime = (int)(end + sharedPref.getInt(getString(R.string.total_time_played),0));
        editor.putInt(getString(R.string.total_time_played), playtime );
        editor.apply();
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
