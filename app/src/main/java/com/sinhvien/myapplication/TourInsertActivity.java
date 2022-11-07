package com.sinhvien.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class TourInsertActivity extends AppCompatActivity {
    // Variables

    // UI Components
    EditText title;
    EditText body;
    EditText timeline;
    EditText price;
    EditText image;
    Button buttonAddTour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_insert);

        addEventListeners();
    }

    public void addEventListeners() {
        title = (EditText) findViewById(R.id.title_edit_text);
        body = (EditText) findViewById(R.id.content_edit_text);
        timeline = (EditText) findViewById(R.id.timeline_edit_text);
        image = (EditText) findViewById(R.id.image_edit_text);
        price = (EditText) findViewById(R.id.price_edit_text);
        buttonAddTour = (Button) findViewById(R.id.add_tour_button);
    }
}