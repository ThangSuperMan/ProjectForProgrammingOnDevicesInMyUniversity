package com.sinhvien.myapplication.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.sinhvien.myapplication.schemas.User;

import java.util.ArrayList;

interface InterfaceUserDAO {
    public boolean insert(User user);
    public User getUserByUsername(String usernameParams);
}

public class UserDAO {
    DBHelper helper;

    public UserDAO(Context context) {
        helper = new DBHelper(context);
    }

    public boolean insert(User user) {
        Log.d("insert-user", "username: " + user.getUsername() + "-" + "password: " + user.getPassword());
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());
        values.put("avatar_image", user.getAvatarImage());
        long row = db.insert("users", null, values);
        db.close();
        return (row > 0);
    }

//    public boolean insert(User user) {
//        Log.d("insert-user", "username: " + user.getUsername() + "-" + "password: " + user.getPassword());
//        SQLiteDatabase db = helper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("username", user.getUsername());
//        values.put("password", user.getPassword());
//        long row = db.insert("users", null, values);
//        db.close();
//        return (row > 0);
//    }

    public void retreiveImageFromDB(String userId ) {
        SQLiteDatabase db = helper  .getWritableDatabase();
        String sql = "select * from users where user_id=?";
        Cursor cursor = db.rawQuery(sql, new String[]{userId});
    }

    @SuppressLint("Range")
    public ArrayList<User> getAll() {
        Log.d("get_all", "getAll is being executed!");
        ArrayList<User> list = new ArrayList<>();

        // Create the db file and allow using CRUD operators
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from users", null);

        while (cursor.moveToNext()) {
            User user = new User();
            String userId = cursor.getString(cursor.getColumnIndex("tour_id"));
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("avatar_image"));

            user.setId(userId);
            user.setUsername(username);
            user.setPassword(password);
            user.setAvatarImage(blob);
            list.add(user);
        }

        db.close();
        cursor.close();
        return list;
    }



    @SuppressLint("Range")
    public User getUserByUsername(String usernameParams) {
        String sql = "select * from users where username=?";
        User user = new User();
        // Connect to the database
        // Create the db file and allow using CRUD operators
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, new String[] {usernameParams});
        if (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex("user_id"));
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            Log.d("data", "id: " + id + ", username: " + username + ", password: " + password);

            user.setId(id);
            user.setUsername(username);
            user.setPassword(password);
        }

        return user;
    }
}
