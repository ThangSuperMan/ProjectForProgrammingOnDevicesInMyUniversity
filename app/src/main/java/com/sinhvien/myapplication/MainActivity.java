package com.sinhvien.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sinhvien.myapplication.authentication.Auth;
import com.sinhvien.myapplication.schemas.Tour;
import com.sinhvien.myapplication.sqlite.TourDAO;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Variables
    ArrayList<Tour> tours = new ArrayList<Tour>();

    // UI Components
    BottomNavigationView bottomNavigationView;
    ListView tourListView;

    // DB
    TourDAO tourDao;

    // Life Cycles
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Hidden action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tourDao = new TourDAO(MainActivity.this);
        tours = tourDao.getAll();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.menu_item_explore);

        bottomNavigationView.setOnItemSelectedListener((item) -> {
            switch (item.getItemId()) {
                case R.id.menu_item_wishlists:
                    startActivity(new Intent(getApplicationContext(), WishlistsActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.menu_item_admin:
                    startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.menu_item_events:
                    startActivity(new Intent(getApplicationContext(), EventActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.menu_item_login:
                    if (Auth.isUser) {
                        // New
                        Intent intentProfile = new Intent(getApplicationContext(), ProfileActivity.class);
                        intentProfile.putExtra("username", Auth.user.getUsername());
                        startActivity(intentProfile);

                        // Old
                        // Go to user's profile
//                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
//                        overridePendingTransition(0, 0);
                    } else {
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    }
            }
           return false;
        });

        setUpList();
        setUpOnClickListener();
    }

    // Functions
    private void setUpList() {
        tourListView = (ListView) findViewById(R.id.toursListView);
        TourAdapter tourAdapter = new TourAdapter(getApplicationContext(), 0, tours);
        tourListView.setAdapter(tourAdapter);
    }

    private void setUpOnClickListener() {
        tourListView.setOnItemClickListener((adapterView, view, position, l) -> {
            Tour selectedTour = (Tour) (tourListView.getItemAtPosition(position));
            Intent showDetailIntent = new Intent(getApplicationContext(), DetailTourActivity.class);
            showDetailIntent.putExtra("id", selectedTour.getId());
            startActivity(showDetailIntent);
            overridePendingTransition(0, 0);
        });
    }
}