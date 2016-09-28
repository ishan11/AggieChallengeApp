package com.example.christina.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Christina on 4/24/2016.
 */
public class DatabaseHelper1 extends SQLiteOpenHelper{
    public static final String DATABASE_NAME ="seizures.db";
    public static final String TABLE_NAME= "seizure_log";
    public static final String COL_1= "ID";
    public static final String COL_2= "DATE";
    public static final String COL_3= "SYMPTOMS";
    public static final String COL_4= "MEDICINE";

    public DatabaseHelper1(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db= this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //creates table with the quarry
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,SYMPTOMS TEXT,MEDICINE INTEGER)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertDataa(String date,String symptoms,String meds){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,date);
        contentValues.put(COL_3,symptoms);
        contentValues.put(COL_4, meds);
        long result= db.insert(TABLE_NAME, null ,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }

    public Cursor getAllDataa(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateDataa(String id, String date, String symptoms, String meds){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,date);
        contentValues.put(COL_3,symptoms);
        contentValues.put(COL_4,meds);
        db.update(TABLE_NAME, contentValues,"ID=?",new String[]{id});
        return true;
    }
    public int deleteDataa(String id) {
        SQLiteDatabase db= this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID=?", new String []{id});
    }
}

