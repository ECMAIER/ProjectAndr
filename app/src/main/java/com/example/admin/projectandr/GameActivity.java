package com.example.admin.projectandr;

//Game activity ... blank text view, update as needed

import android.content.Intent;

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
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;



public class GameActivity extends AppCompatActivity {

    CheckBox left_binary, left_decimal, left_hex, right_binary, right_decimal, right_hex;
    Button enter;
    ArrayList<Integer> left = new ArrayList<Integer>(0);
    ArrayList<Integer> right = new ArrayList<Integer>(0);

    SharedPreferences sharedPref = MainActivity.sharedPref;
    SharedPreferences.Editor editor = sharedPref.edit();

    float start = 0;
    float end = 0;
    private Button Mode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        //Get instances of CheckBoxes and the button from activity_game.xml
        left_binary = findViewById(R.id.left_binary);
        left_decimal = findViewById(R.id.left_decimal);
        left_hex =  findViewById(R.id.left_hex);
        right_binary =  findViewById(R.id.right_binary);
        right_decimal =  findViewById(R.id.right_decimal);
        right_hex =  findViewById(R.id.right_hex);

        enter = findViewById(R.id.enter_button);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (left_decimal.isChecked()){
                    left.add(10);
                }

                if (left_binary.isChecked()){
                    left.add(2);
                }

                if (left_hex.isChecked()){
                    left.add(16);
                }

                if (right_decimal.isChecked()){
                    right.add(10);
                }

                if (right_binary.isChecked()){
                    right.add(2);
                }

                if (right_hex.isChecked()){
                    right.add(16);
                }

                if (left.size() == 0 || right.size() == 0){
                    enter.setError("Must select at least one from each side");
                }else {
                    Intent intent = new Intent(GameActivity.this, QuizActivity.class);

                    intent.putIntegerArrayListExtra("left_values", left);
                    intent.putIntegerArrayListExtra("right_values", right);

                    startActivity(intent);
                }
            }
        });

    }
/*
    @Override
    protected void onStart() {
        super.onStart();
        //changed
        //setContentView(R.layout.activity_timed);
        start = System.currentTimeMillis();
        setContentView(R.layout.activity_game);

    }
//2.1845334

    @Override
    protected void onStop() {
        super.onStop();
        end = System.currentTimeMillis() - start;
        int playtime = (int)(end + sharedPref.getInt(getString(R.string.total_time_played),0));
        editor.putInt(getString(R.string.total_time_played), playtime );
        //editor.putInt(getString(R.string.total_time_played), 0 ); //to reset
        editor.apply();
    }
    */
}

