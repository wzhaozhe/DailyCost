package com.zhaozhe.dailycost;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "DailyCostDB";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "DailyCost";
    private static final String ID_COL = "id";
    private static final String DB_AMOUNT = "Amount";
    private static final String DB_DATE = "Date";

    public DBHandler(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " integer PRIMARY KEY AUTOINCREMENT, "
                + DB_DATE + " TEXT, "
                + DB_AMOUNT + " TEXT) ";
        db.execSQL(query);
    }

    public void addNewPayment(String date, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DB_DATE, date);
        values.put(DB_AMOUNT, amount);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
