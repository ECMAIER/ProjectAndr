package com.example.admin.projectandr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity
{
    //Buttons declared to be used
    private ImageButton Game;
    private ImageButton Coop;
    private ImageButton Time;
    private ImageButton Endless;
    private ImageButton Survivor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //Finds button by id on activity_start.xml
        ImageButton Game = findViewById(R.id.Game);
        ImageButton Coop = findViewById(R.id.COOP);
        ImageButton Time = findViewById(R.id.time);
        ImageButton Endless = findViewById(R.id.endless);
        ImageButton Survivor = findViewById(R.id.survivor);

        //All buttons start at StartActivity and goes to said Activity
        Game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        Coop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(StartActivity.this, CoopActivity.class);
                startActivity(intent2);
            }
        });

        Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> left = new ArrayList<Integer>(0);
                ArrayList<Integer> right = new ArrayList<Integer>(0);
                left.add(2);
                left.add(10);
                left.add(16);
                right.add(2);
                right.add(10);
                right.add(16);
                Intent intent3 = new Intent(StartActivity.this, TimedActivity.class);
                intent3.putIntegerArrayListExtra("left_values", left);
                intent3.putIntegerArrayListExtra("right_values", right);
                startActivity(intent3);
            }
        });

        Endless.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(StartActivity.this, EndlessActivity.class);
                startActivity(intent4);
            }
        });

        Survivor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(StartActivity.this, SurvivorActivity.class);
                startActivity(intent5);
            }
        });
    }
}


