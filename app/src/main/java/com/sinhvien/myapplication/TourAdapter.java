package com.sinhvien.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sinhvien.myapplication.classes.Tour;

import java.util.List;

public class TourAdapter extends ArrayAdapter<Tour> {
    private static Context context;
    private String dbName;

    public TourAdapter(Context context, int resource, List<Tour> tours) {
        super(context, resource, tours);
        this.context = context;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Tour tour = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tour_cell, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.tour_name);
        TextView timeline = (TextView) convertView.findViewById(R.id.time_line_text_view);
        TextView price = (TextView) convertView.findViewById(R.id.price_text_view);
        ImageView image = (ImageView) convertView.findViewById(R.id.tour_image);

        name.setText(tour.getTitle());
        timeline.setText(tour.getTimeline());
        price.setText(String.valueOf(tour.getPrice()));
        // context -> MainActivity
        int resourceID = context.getResources().getIdentifier(tour.getImage(), "drawable",context.getPackageName());
        image.setImageResource(resourceID);

        return convertView;
    }
}
