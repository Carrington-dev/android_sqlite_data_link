package com.stemgon.datalink.home.database.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.stemgon.datalink.home.models.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "phonebook.db";
    private static final String TABLE_NAME = "users";
    private static final String COL_ID = "ID";
    private static final String COL_NAME = "EMAIL";
    private static final String COL_PHONE = "PHONE";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE \"users\" (\n" +
                "\t\"ID\"\tINTEGER NOT NULL UNIQUE,\n" +
                "\t\"EMAIL\"\tTEXT NOT NULL,\n" +
                "\t\"PHONE\"\tTEXT NOT NULL,\n" +
                "\tPRIMARY KEY(\"ID\"  AUTOINCREMENT)\n" +
                ");");
//        db.execSQL("CREATE TABLE " + TABLE_NAME + " ( " +  COL_ID + + "INTEGER PRIMARY KEY AUTOINCREMENT," + "EMAIL TEXT, AGE INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Methods for CRUD operations...
    public boolean insertData(String email, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, email);
        values.put(COL_PHONE, phone);
        long isInserted = db.insert(TABLE_NAME, null, values);
        db.close();

        if(isInserted == -1)
            return false;
        return true;
    }

    public List<User> getAllData() {
        List<User> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM my_table", null);

        if (cursor.moveToFirst()) {
            do {
                Integer id = cursor.getInt(0);
                String email = cursor.getString(1);
                String phone = String.valueOf(cursor.getInt(2));
                dataList.add(new User(id, email, phone));
//                dataList.add("ID: " + id + ", Name: " + name + ", Age: " + age);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return dataList;
    }


}
