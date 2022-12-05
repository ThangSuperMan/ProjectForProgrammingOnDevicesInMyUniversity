package com.sinhvien.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sinhvien.myapplication.schemas.Comment;
import com.sinhvien.myapplication.utils.Utils;

import java.util.List;

public class CommentAdapter extends ArrayAdapter<Comment> {
    private static Context context;

    public CommentAdapter (Context context, int resource, List<Comment> comments) {
        super(context, resource, comments);
        this.context = context;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Comment comment = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.comment_cell, parent, false);
        }

        TextView username = (TextView) convertView.findViewById(R.id.username_text_view);
        TextView body = (TextView) convertView.findViewById(R.id.body_text_view);
        TextView createdAt = (TextView) convertView.findViewById(R.id.created_at_text_view);
        ImageView image = (ImageView) convertView.findViewById(R.id.profile_image_view);

        username.setText(comment.getUsername());
        body.setText(comment.getBody());
        createdAt.setText(comment.getCreatedAt());

        if (comment.getImage() == null) {
            Toast.makeText(context, "image null", Toast.LENGTH_SHORT).show();
        } else {
            Bitmap imageBitmap = Utils.getImage(comment.getImage());
            image.setImageBitmap(imageBitmap);
        }

        // context -> MainActivity
//        int resourceID = context.getResources().getIdentifier(comment.getImage(), "drawable",context.getPackageName());
//        image.setImageResource(resourceID);

        return convertView;
    }
}

