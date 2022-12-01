package com.sinhvien.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sinhvien.myapplication.authentication.Auth;
import com.sinhvien.myapplication.schemas.Tour;
import com.sinhvien.myapplication.sqlite.TourDAO;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Variables
    // Hard code
//    String tourTitles[] = { "Vaala, Finland", "Enonkoski, Finland", "Calaca, Philippin", "Canada Dream"};
//    int tourImages[] = { R.drawable.tour1, R.drawable.tour2, R.drawable.tour3, R.drawable.tour4 };
//    String timelines[] = { "22-26 Oct", "19-04 April", "20-04 May", "21-09 October" };
//    int totalPrice[] = { 680, 230, 120, 340 };
    ArrayList<Tour> tours = new ArrayList<Tour>();
    ListView tourListView;

    // Dynamic code
    private static SQLiteDatabase db;
    public static final String dbName = "TravelAppDatabase.db";
    private String dbPath = "/databases/";

    // UI Components
    BottomNavigationView bottomNavigationView;

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
                case R.id.menu_item_login:
                    Toast.makeText(this, "Login item clicked", Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "Aut.isUser" + Auth.isUser, Toast.LENGTH_SHORT).show();
                    if (Auth.isUser) {
                        // Go to user's profile
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0, 0);
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

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    // Functions
    private void setupData() {
        tours = tourDao.getAll();
    }

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