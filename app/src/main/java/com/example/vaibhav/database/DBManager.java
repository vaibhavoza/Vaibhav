package com.example.vaibhav.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.vaibhav.model.Employee;

import java.util.ArrayList;
import java.util.List;

import static com.example.vaibhav.database.DatabaseHelper.IMAGE;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name, String email,String number,byte[] image) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME, name);
        contentValue.put(DatabaseHelper.EMAIL, email);
        contentValue.put(DatabaseHelper.NUMBER, number);
        contentValue.put(DatabaseHelper.IMAGE, image);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.NAME, DatabaseHelper.EMAIL,DatabaseHelper.NUMBER, IMAGE };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public List<Employee> getAllUser() {

        List<Employee> userList = new ArrayList<Employee>();

//        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.NAME, DatabaseHelper.EMAIL,DatabaseHelper.NUMBER, IMAGE };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        // query the user table
        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Employee user = new Employee();
                user.setId(cursor.getString(cursor.getColumnIndex(DatabaseHelper._ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(DatabaseHelper.EMAIL)));
                user.setNumber(cursor.getString(cursor.getColumnIndex(DatabaseHelper.NUMBER)));
               // byte[] blob = cursor.getBlob(columns.getColumnIndex(IMAGE));
                user.setImg(cursor.getString(cursor.getColumnIndex(DatabaseHelper.IMAGE)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return user list
        return userList;
    }

    public int update(long _id, String name, String email,String number,String image) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, name);
        contentValues.put(DatabaseHelper.EMAIL, email);
        contentValues.put(DatabaseHelper.NUMBER, number);
        contentValues.put(IMAGE, image);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }
}
