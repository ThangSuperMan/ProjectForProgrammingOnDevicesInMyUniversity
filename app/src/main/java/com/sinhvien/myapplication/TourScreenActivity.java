package com.sinhvien.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class TourScreenActivity extends AppCompatActivity {
    String tourTitles[] = { "Tour 1", "Tour 2"};
    int tourImages[] = { R.drawable.tour1, R.drawable.tour2,R.drawable.tour1, R.drawable.tour2, R.drawable.tour2 };
    public static ArrayList<Tour> tours = new ArrayList<Tour>();
    ListView tourListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_screen);

        // Hidden action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setupDate();
        setUpList();
        setUpOnClickListener();
    }

    private void setupDate() {
//        Tour tour1 = new Tour("0", "Tour one", R.drawable.tour1);
//        tours.add(tour1);
//
//        Tour tour2 = new Tour("1", "Tour two", R.drawable.tour2);
//        tours.add(tour2);

        for (int i = 0 ; i < tourTitles.length; i++) {
            String id = Integer.toString(i);
            Tour tour = new Tour(id, tourTitles[i], tourImages[i]);
            tours.add(tour);
        }
    }

    private void setUpList() {
        tourListView = (ListView) findViewById(R.id.toursListView);

        TourAdapter tourAdapter = new TourAdapter(getApplicationContext(), 0, tours);
        tourListView.setAdapter(tourAdapter);
    }

    private void setUpOnClickListener() {
        tourListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Tour selectedTour = (Tour) (tourListView.getItemAtPosition(position));
                Intent showDetailIntent = new Intent(getApplicationContext(), DetailTourActivity.class);
                showDetailIntent.putExtra("id", selectedTour.getId());
                startActivity(showDetailIntent);
            }
        });
    }


}