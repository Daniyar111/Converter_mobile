package com.example.saint.convertproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by saint on 18.03.2018.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "CONVERTER";
    private static final int DB_VERSION = 2;
    private static final String TABLE_NAME = "DATA";
    private static final String ID = "_id";
    private static final String TEXT_FIRST = "TEXT_FIRST";
    private static final String TEXT_SECOND = "TEXT_SECOND";
    private static final String SPINNER_FIRST = "SPINNER_FIRST";
    private static final String SPINNER_SECOND = "SPINNER_SECOND";
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " (" +
            ID + " INTEGER_PRIMARY_KEY, " +
            TEXT_FIRST + " TEXT, " +
            TEXT_SECOND + " TEXT, " +
            SPINNER_FIRST + " INTEGER, " +
            SPINNER_SECOND + " INTEGER);";

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void saveData(SQLiteModel sqLiteModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        db.delete(TABLE_NAME, null, null);
        cv.put(TEXT_FIRST, sqLiteModel.getEditTextFirst());
        cv.put(TEXT_SECOND, sqLiteModel.getEditTextSecond());
        cv.put(SPINNER_FIRST, sqLiteModel.getSpinnerFirst());
        cv.put(SPINNER_SECOND, sqLiteModel.getSpinnerSecond());
        long s = db.insert(TABLE_NAME, null, cv);
        Log.d("HEY", "ROW = " + s);
        db.close();
    }

    public SQLiteModel getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteModel sqLiteModel = new SQLiteModel();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            int textFirstIndex = cursor.getColumnIndex(TEXT_FIRST);
            int textSecondIndex = cursor.getColumnIndex(TEXT_SECOND);
            int spinnerFirstIndex = cursor.getColumnIndex(SPINNER_FIRST);
            int spinnerSecondIndex = cursor.getColumnIndex(SPINNER_SECOND);
            do{
                String textFirst = cursor.getString(textFirstIndex);
                String textSecond = cursor.getString(textSecondIndex);
                String spinnerFirst = cursor.getString(spinnerFirstIndex);
                String spinnerSecond = cursor.getString(spinnerSecondIndex);
                sqLiteModel.setEditTextFirst(textFirst);
                sqLiteModel.setEditTextSecond(textSecond);
                sqLiteModel.setSpinnerFirst(Integer.parseInt(spinnerFirst));
                sqLiteModel.setSpinnerSecond(Integer.parseInt(spinnerSecond));
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return sqLiteModel;
    }

    public void clearDB(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }
}
