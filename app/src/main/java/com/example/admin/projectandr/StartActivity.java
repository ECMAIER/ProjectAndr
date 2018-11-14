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

public class StartActivity extends AppCompatActivity
{
    private Button Game;
    private Button Coop;
    private Button Time;
    //Evan Edit
    private Button Calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        Button Game = findViewById(R.id.Game);
        Button Coop = findViewById(R.id.COOP);
        Button Time = findViewById(R.id.time);
        Button Calculator = findViewById(R.id.calculator);

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
                Intent intent3 = new Intent(StartActivity.this, TimedActivity.class);
                startActivity(intent3);
            }
        });

        Calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(StartActivity.this, CalculatorActivity.class);
                startActivity(intent4);
            }
        });

    }
}


