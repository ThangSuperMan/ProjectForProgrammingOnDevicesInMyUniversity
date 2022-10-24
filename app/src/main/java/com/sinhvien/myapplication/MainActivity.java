package com.sinhvien.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Variables
    String tourTitles[] = { "Vaala, Finland", "Enonkoski, Finland", "Calaca, Philippin", "Canada Dream"};
    int tourImages[] = { R.drawable.tour1, R.drawable.tour2, R.drawable.tour3, R.drawable.tour4 };
    String timelines[] = { "22-26 Oct", "19-04 April", "20-04 May", "21-09 October" };
    int totalPrice[] = { 680, 230, 120, 340 };
    ArrayList<Tour> tours = new ArrayList<Tour>();
    ListView tourListView;

    // UI Components
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Hidden action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.menu_item_explore);

        bottomNavigationView.setOnItemSelectedListener((item) -> {
            switch (item.getItemId()) {
                case R.id.menu_item_wishlists:
                    startActivity(new Intent(getApplicationContext(), WishlistsActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.menu_item_login:
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
           return false;
        });

        setupData();
        setUpList();
        setUpOnClickListener();
    }

    // Functions
    private void setupData() {
        for (int i = 0 ; i < tourTitles.length; i++) {
            String id = Integer.toString(i);
            //    public Tour(String id, String name, int image, String timeline, int totalPrice) {
            Tour tour = new Tour(id, tourTitles[i], tourImages[i], timelines[i], totalPrice[i]);
            tours.add(tour);
        }
    }

    private void setUpList() {
        tourListView = (ListView) findViewById(R.id.toursListView);
        TourAdapter tourAdapter = new TourAdapter(getApplicationContext(), 0, tours);
        tourListView.setAdapter(tourAdapter);
    }

    private void setUpOnClickListener() {
        tourListView.setOnItemClickListener((adapterView, view, position, l) -> {
            Toast.makeText(MainActivity.this, "onItemClick", Toast.LENGTH_SHORT).show();
            Tour selectedTour = (Tour) (tourListView.getItemAtPosition(position));
            Intent showDetailIntent = new Intent(getApplicationContext(), DetailTourActivity.class);
            showDetailIntent.putExtra("id", selectedTour.getId());
            startActivity(showDetailIntent);
            overridePendingTransition(0, 0);
        });
    }
}