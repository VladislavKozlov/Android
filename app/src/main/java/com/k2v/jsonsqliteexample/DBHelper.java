package com.k2v.jsonsqliteexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Vladislav Kozlov <k2v.akosa@gmail.com>
 */
public class DBHelper extends SQLiteOpenHelper {

    public static String DB_NAME;
    public static String TABLE_NAME;
    public static String CRYPTOTICKER;
    public static String lASTTRAIDE;
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {

        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + "id integer primary key autoincrement,"
                + CRYPTOTICKER + "text,"
                + lASTTRAIDE + "text" + ");");
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

