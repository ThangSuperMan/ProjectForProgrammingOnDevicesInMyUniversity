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

        TextView tv = (TextView) convertView.findViewById(R.id.tour_name);
        ImageView iv = (ImageView) convertView.findViewById(R.id.tour_image);

        tv.setText(tour.getName());
        iv.setImageResource(tour.getImage());

        return convertView;
    }
}
