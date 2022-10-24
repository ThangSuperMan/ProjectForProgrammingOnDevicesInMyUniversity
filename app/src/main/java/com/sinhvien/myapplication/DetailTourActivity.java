package com.sinhvien.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailTourActivity extends AppCompatActivity {
    // Variables
    Tour selectedTour;
    String tourTitles[] = { "Vaala, Finland", "Enonkoski, Finland", "Calaca, Philippin", "Canada Dream"};
    int tourImages[] = { R.drawable.tour1, R.drawable.tour2, R.drawable.tour3, R.drawable.tour4 };
    String timelines[] = { "22-26 Oct", "19-04 April", "20-04 May", "21-09 October" };
    int totalPrice[] = { 680, 230, 120, 340 };
    public static ArrayList<Tour> tours = new ArrayList<Tour>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tour);

        setupData();
        getSelectedTour();
        setValues();
    }

    // Functions

    private void getSelectedTour() {
        System.out.println("getSelectedTour");
        Toast.makeText(this, "Here !", Toast.LENGTH_SHORT).show();
        Intent previousIntent = getIntent();
        String parsedStringID = previousIntent.getStringExtra("id");
        Toast.makeText(this, "parsedStringID: " + Integer.parseInt(parsedStringID), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "parseStringID: " + parsedStringID + selectedTour.getName(), Toast.LENGTH_SHORT).show();
//        selectedTour = TourActivity.tours.get(Integer.parseInt(parsedStringID));

        selectedTour = tours.get(Integer.parseInt(parsedStringID));
//        selectedTour = TourActivity.tours.get(0);
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

    private void setValues() {
        TextView tv = (TextView) findViewById(R.id.detail_tour_text_view);
        ImageView iv = (ImageView)findViewById(R.id.detail_tour_image_view);

//        tv.setText("Name");
//        iv.setImageResource(R.drawable.tour1);

        tv.setText(selectedTour.getName());
        iv.setImageResource(selectedTour.getImage());
    }
}