package com.example.tests;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Datahelper2 extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    public static final String DB_NAME = "usersdb2";
    public static final String TABLE_Users = "userdetails2";
    public static final String KEY_ID = "id2";
    public static final String KEY_NAME = "answer";



    public Datahelper2(Context context){
        super(context, DB_NAME, null, DB_VERSION);

    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Users + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT"+")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
        onCreate(db);
    }

    public void insertData(String answer){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, answer);

        db.insert(TABLE_Users, null, contentValues);
        db.close();

    }



    public Cursor viewData2(){
        SQLiteDatabase db = this.getReadableDatabase();
        String qery = "Select * from " + TABLE_Users;

        return db.rawQuery(qery, null);
    }





}
