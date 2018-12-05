package com.example.admin.projectandr;

import android.content.Intent;
import android.content.SharedPreferences;
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


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //private button variable used to go to new activities
    private ImageButton Start;
    private ImageButton Stats;
    private ImageButton Tutorial;


    public static SharedPreferences sharedPref; //global var for all activities

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //start of toolbar, toolbar object made to be passed to setSupportActionBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPref = getSharedPreferences(getString(R.string.total_time_played) ,MODE_PRIVATE);


        //Buttons set to certain ids,
        ImageButton Start = findViewById(R.id.start);
        ImageButton Stats = findViewById(R.id.stats);
        ImageButton Tutorial = findViewById(R.id.tutorial);

        //Start button used new intent to go from MainActivity to StartActivity
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });

        //Stats button used new intent to go from MainActivity to StatActivity
        Stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, StatsActivity.class);
                startActivity(intent2);
            }
        });

        //Tutorial button used new intent to go from MainActivity to TutorialActivity
        Tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(MainActivity.this, TutorialActivity.class);
                startActivity(intent3);
            }
        });

        //start of drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //start of NavigationView
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    //if back button is pressed, close drawer
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here. (Hamburger menu)
        int id = item.getItemId();

        //To go from Main to StartActivity (Game modes page)
        if (id == R.id.nav_Start) {
            Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);
        }

        //To go from Main to Stats page
        else if (id == R.id.nav_Stats) {
            Intent intent2 = new Intent(this, StatsActivity.class);
            startActivity(intent2);
        }

        //To go from Main to Tutorial page
        else if (id == R.id.nav_Tutorial){
            Intent intent3 = new Intent(this, TutorialActivity.class);
            startActivity(intent3);
        }

        //To go from Main to Game
        else if (id == R.id.nav_Game){
            Intent intent4 = new Intent(this, GameActivity.class);
            startActivity(intent4);
        }

        //To go from Main to COOP
        else if (id == R.id.nav_COOP){
            Intent intent5 = new Intent(this, CoopActivity.class);
            startActivity(intent5);
        }

        //To go from Main to Time
        else if (id == R.id.nav_Time){
            Intent intent6 = new Intent(this, TimedActivity.class);
            startActivity(intent6);
        }

        //To go from Main to endless
        else if (id == R.id.nav_endless){
            Intent intent7 = new Intent (this, EndlessActivity.class);
            startActivity(intent7);
        }

        //To go from Main to Survivor
        else if (id == R.id.nav_Survivor){
            Intent intent8 = new Intent(this, SurvivorActivity.class);
            startActivity(intent8);
        }

        //Close drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
