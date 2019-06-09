package com.example.panda3;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.util.Log;
import android.database.Cursor;

public class DatabaseHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final String TAG = "DatabaseHelper";
    public static final int DATABASE_VERSION = 2;
    public static final String TABLE_NAME = "task_table";

    public static final String COL1 = "ID";
    public static final String COL2 = "col2";
    public static final String COL3 = "col3";
    public static final String COL4 = "hour";
    public static final String COL5 = "minute";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " TEXT, " + COL3 + " TEXT, " +
                COL4 + " INT CHECK(" + COL4 + " < 25 AND " + COL4 + " > 0), " + COL5 + " INT CHECK(" + COL5 + " < 60 AND " + COL5 + " >= 0))";
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_UPDATE_ENTRIES = "DROP TABLE IF EXISTS "  + TABLE_NAME;
        db.execSQL(SQL_UPDATE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    public boolean addData(String item1, String item2, int item3, int item4) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item1);
        contentValues.put(COL3, item2);
        contentValues.put(COL4, item3);
        contentValues.put(COL5, item4);

        Log.d(TAG, "addData: Adding " + contentValues + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor getItemsID(String date){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL3 + " = '" + date + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public void deleteTask(int id, String name, String date, int hour, int minute){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + name +
                "' AND " + COL3 + " = '" + date + "'"+
                " AND " + COL4 + " = '" + hour + "'"+
                " AND " + COL5 + " = '" + minute + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }
    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void updateTask(String newName, int id, String oldName, int oldHour, int newHour, int oldMinute, int newMinute){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
                " = '" + newName + "', "
                + COL4 +
                " = '" + newHour + "', " +
                 COL5 +
                " = '" + newMinute + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + oldName + "'" +
                " AND " + COL4 + " = '" + oldHour + "'" +
                " AND " + COL5 + " = '" + oldMinute + "'" ;
        Log.d(TAG, "updateName: query: " + query);
        db.execSQL(query);

    }
}