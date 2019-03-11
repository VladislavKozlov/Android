package com.k2v.jsonsqliteexample;

import android.content.Intent;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;

/**
 * Created by Vladislav Kozlov <k2v.akosa@gmail.com>
 */
public class SQLiteQueryActivity extends Activity {

    public ArrayList jsonList = new ArrayList<String>();

   	public final String LOG_TAG = "Logs";
    private DBHelper dbHelper_;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        dbHelper_ = new DBHelper(this);
        //DB connect
        SQLiteDatabase db = dbHelper_.getWritableDatabase();
        Log.d(LOG_TAG, "--- Rows in jsontable: ---");

        Cursor cursor = db.query("jsontable", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {

            int idColIndex = cursor.getColumnIndex("id");
            int nameColIndex = cursor.getColumnIndex("companyname");
            int priceColIndex = cursor.getColumnIndex("latestprice");

            do {
                Log.d(LOG_TAG,
                        "ID = " + cursor.getInt(idColIndex) +
                                ", companyname = " + cursor.getString(nameColIndex) +
                                ", latestprice = " + cursor.getString(priceColIndex));
                jsonList.add(cursor.getString(nameColIndex));
                jsonList.add(cursor.getString(priceColIndex));

            } while (cursor.moveToNext());
        } else
            Log.d(LOG_TAG, "0 rows");
        cursor.close();
        dbHelper_.close();

        String name = (String) jsonList.get(0);
        //String price = (String) jsonList.get(1);
        Intent intent = new Intent(SQLiteQueryActivity.this, TextViewActivity.class);
        intent.putExtra("name", name);
        //intent.putExtra("price", price);
        startActivity(intent);
    }

}
