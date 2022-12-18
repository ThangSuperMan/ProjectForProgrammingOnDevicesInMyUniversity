package com.sinhvien.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sinhvien.myapplication.authentication.Auth;
import com.sinhvien.myapplication.schemas.Comment;
import com.sinhvien.myapplication.schemas.Tour;
import com.sinhvien.myapplication.schemas.User;
import com.sinhvien.myapplication.sqlite.CommentDAO;
import com.sinhvien.myapplication.sqlite.TourDAO;
import com.sinhvien.myapplication.sqlite.UserDAO;
import java.util.ArrayList;

public class DetailTourActivity extends AppCompatActivity {

    // Variables
    Tour selectedTour;
    String selectedTourId;
    ArrayList<Comment> comments = new ArrayList<Comment>();

    // UI Components
    Button addCommentButton;
    ListView commentListView;
    CommentAdapter tourAdapter;

    public static ArrayList<Tour> tours = new ArrayList<Tour>();
    Comment comment = new Comment();

    // Databases
    TourDAO tourDao;
    UserDAO userDao;
    CommentDAO commentDao;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tour);

        // Hidden action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // Database
        tourDao = new TourDAO(DetailTourActivity.this);
        commentDao = new CommentDAO(DetailTourActivity.this);
        userDao = new UserDAO(DetailTourActivity.this);

        getSelectedTour();

        // Add event listeners
        addCommentButton = (Button) findViewById(R.id.add_comment_button);
        addCommentButton.setOnClickListener((View) -> {
            if (Auth.isUser) {
                handleAddComment();
            } else {
                // You have to login first
                Toast.makeText(this, "You have to login first if you would like to use this feature!", Toast.LENGTH_SHORT).show();
                Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        // Setup data
        comments = commentDao.getAllByTourId(selectedTourId);

        // New of setup list func
        tourAdapter = new CommentAdapter (getApplicationContext(), 0, comments);

        // Combine more props for comment schema
        combineEssentialPropsForCommentSchema();

        setValues();
        setUpList();
    }

    // Functions
    private void combineEssentialPropsForCommentSchema() {
        Toast.makeText(this, "combineEssentialPropsForCommentSchema is just begin executed!", Toast.LENGTH_SHORT).show();
        for (int i = 0; i < comments.size(); i++) {
            String userId = comments.get(i).getUserId();
            User user = new User();
            user = userDao.getUserById(userId);
            comments.get(i).setUsername(user.getUsername());
            comments.get(i).setImage(user.getAvatarImage());
        }
    }

    private void setUpList() {
        commentListView = (ListView) findViewById(R.id.comments_list_view);
        commentListView.setAdapter(tourAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void handleAddComment() {
        Toast.makeText(this, "handleAddComment", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailTourActivity.this);
        View view = LayoutInflater.from(DetailTourActivity.this).inflate(
                R.layout.layout_add_comment, (ConstraintLayout)findViewById(R.id.layout_dialog_comment_container)
        );
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();

        EditText editText = view.findViewById(R.id.content_edit_text);

        Button addCommentButton = view.findViewById(R.id.add_comment_button);
        addCommentButton.setOnClickListener((View) -> {
            if (editText.getText().toString().equals("")) {
                return;
            } else {
                // Prepare for insert data to the table
                comment.setBody(editText.getText().toString());
                commentDao = new CommentDAO(DetailTourActivity.this);

                String userId;
                // Whole own this comment
                userId = Auth.user.getId();

                // Get content would like to insert
                String currentUsername = comment.getUsername();
                if (commentDao.insert(comment, userId, selectedTourId)) {

                    // Refresh the listview data change
                    comments.clear();
                    comments = commentDao.getAllByTourId(selectedTourId);
                    combineEssentialPropsForCommentSchema();

                    for (int i = 0; i < comments.size(); i++) {
                        Toast.makeText(this, "body of comment: " + comments.get(i).getBody(), Toast.LENGTH_SHORT).show();
                    }

                    tourAdapter.addAll(comments);
                    tourAdapter.notifyDataSetChanged();
                    alertDialog.hide();

                    Toast.makeText(this, "Add comment successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Add comment unsuccessfully!", Toast.LENGTH_SHORT).show();
                }

                alertDialog.hide();
            }
        });

        // Make the background of alert dialog transparent
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
        alertDialog.show();
    }

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
        int resourceID = getApplication().getResources().getIdentifier(tour.getImage(), "drawable",getApplication().getPackageName());
        imageView.setImageResource(resourceID);
    }
}