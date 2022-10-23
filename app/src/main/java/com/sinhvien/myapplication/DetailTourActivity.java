package com.sinhvien.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailTourActivity extends AppCompatActivity {
    Tour selectedTour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tour);
        getSelectedTour();
        setValues();
    }

    private void getSelectedTour() {
        Intent previousIntent = getIntent();
        String parsedStringID = previousIntent.getStringExtra("id");
        selectedTour = TourActivity.tours.get(Integer.parseInt(parsedStringID));
    }

    private void setValues() {
        TextView tv = (TextView) findViewById(R.id.detail_tour_text_view);
        ImageView iv = (ImageView)findViewById(R.id.detail_tour_image_view);

        tv.setText(selectedTour.getName());
        iv.setImageResource(selectedTour.getImage());
    }
}