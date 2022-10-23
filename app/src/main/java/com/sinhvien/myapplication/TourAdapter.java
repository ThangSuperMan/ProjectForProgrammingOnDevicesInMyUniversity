package com.sinhvien.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TourAdapter extends ArrayAdapter<Tour> {

    public TourAdapter(Context context, int resource, List<Tour> tours) {
        super(context, resource, tours);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Tour tour = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tour_cell, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.tour_name);
        TextView timeline = (TextView) convertView.findViewById(R.id.time_line_text_view);
        TextView totalPrice = (TextView) convertView.findViewById(R.id.price_text_view);
        ImageView image = (ImageView) convertView.findViewById(R.id.tour_image);

        name.setText(tour.getName());
        timeline.setText(tour.getTimeline());
        totalPrice.setText(String.valueOf(tour.getTotalPrice()));
        image.setImageResource(tour.getImage());

        return convertView;
    }
}
