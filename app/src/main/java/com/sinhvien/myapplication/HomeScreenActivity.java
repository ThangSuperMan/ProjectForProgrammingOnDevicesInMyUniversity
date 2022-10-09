package com.sinhvien.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreenActivity extends AppCompatActivity {

    // UI Components
    Button buttonExploreNearby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        // Hidden action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // Add event listener
        buttonExploreNearby = (Button)findViewById(R.id.button_explore_nearby_stays);
        buttonExploreNearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentTour = new Intent(getApplicationContext(), TourScreenActivity.class);
                startActivity(intentTour);
            }
        });
    }
}