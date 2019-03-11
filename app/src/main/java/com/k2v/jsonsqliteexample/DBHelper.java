package com.k2v.jsonsqliteexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Vladislav Kozlov <k2v.akosa@gmail.com>
 */
public class DBHelper extends SQLiteOpenHelper {

    final String LOG_TAG = "Logs";

    public DBHelper(Context context) {
        super(context, "myDB", null, 1);
    }

    @Override
    public void onCreate (SQLiteDatabase db){
        Log.d(LOG_TAG, "--- onCreate database ---");
        db.execSQL("create table jsontable ("
                + "id integer primary key autoincrement,"
                + "companyname text,"
                + "latestprice text" + ");");
    }

    @Override
    public void onUpgrade (SQLiteDatabase db,int oldVersion, int newVersion){

    }
}
