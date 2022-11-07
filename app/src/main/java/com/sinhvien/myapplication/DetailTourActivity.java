package com.sinhvien.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinhvien.myapplication.classes.Tour;
import com.sinhvien.myapplication.sqlite.TourDAO;

import java.util.ArrayList;

public class DetailTourActivity extends AppCompatActivity {
    // Variables
    Tour selectedTour;
    String selectedTourId;

    String tourTitles[] = { "Vaala, Finland", "Enonkoski, Finland", "Calaca, Philippin", "Canada Dream"};
//    int tourImages[] = { R.drawable.tour1, R.drawable.tour2, R.drawable.tour3, R.drawable.tour4 };
    String tourImages[] = { "tour1", "tour2", "tour3", "tour4" };
    String timelines[] = { "22-26 Oct", "19-04 April", "20-04 May", "21-09 October" };
    int totalPrice[] = { 680, 230, 120, 340 };
    public static ArrayList<Tour> tours = new ArrayList<Tour>();

    // Databases
    TourDAO tourDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tour);

        // Hidden action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // Database
        tourDao  = new TourDAO(DetailTourActivity.this);

        getSelectedTour();
        setValues();
    }

    // Functions
    private void getSelectedTour() {
        Intent previousIntent = getIntent();
        String parsedStringID = previousIntent.getStringExtra("id");
        selectedTourId = parsedStringID;
    }

    private void setValues() {
        TextView titleTextView = (TextView) findViewById(R.id.detail_tour_text_view);
        ImageView imageView = (ImageView)findViewById(R.id.detail_tour_image_view);
        TextView descriptionTextView = (TextView) findViewById(R.id.bodyTextView);
        Tour tour = new Tour();
        tour = tourDao.getTourById(selectedTourId.toString());

        titleTextView.setText(tour.getTitle());
        descriptionTextView.setText(tour.getBody());
        // context -> MainActivity
        int resourceID = getApplication().getResources().getIdentifier(tour.getImage(), "drawable",getApplication().getPackageName());
        imageView.setImageResource(resourceID);
    }
}