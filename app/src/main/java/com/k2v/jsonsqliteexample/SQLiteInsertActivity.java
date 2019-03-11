package com.k2v.jsonsqliteexample;

import android.content.Intent;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;


/**
 * Created by Vladislav Kozlov <k2v.akosa@gmail.com>
 */
public class SQLiteInsertActivity extends Activity implements OnClickListener {


   	public final String LOG_TAG = "Logs";
    private DBHelper dbHelper_;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_view);
        View showButton = findViewById(R.id.show_button);
        showButton.setOnClickListener(this);
        //View clearButton = findViewById(R.id.clear_button);
        //clearButton.setOnClickListener(this);

        dbHelper_ = new DBHelper(this);
        ContentValues cv = new ContentValues();

        String companyname = "companyname1";
        String latestprice = "latestprice1";
        //DB connect
        SQLiteDatabase db = dbHelper_.getWritableDatabase();
        Log.d(LOG_TAG, "--- Insert in jsontable: ---");

        cv.put("companyname", companyname);
        cv.put("latestprice", latestprice);
        long rowID = db.insert("jsontable", null, cv);
        Log.d(LOG_TAG, "row inserted, ID = " + rowID);

        dbHelper_.close();

    }

    @Override
    public void onClick (View v){

        if(v.getId() == R.id.show_button) {

            Intent intent = new Intent(SQLiteInsertActivity.this,SQLiteQueryActivity.class);
            startActivity(intent);
        }
        /*
        if(v.getId() == R.id.clear_button) {

            dbHelper_ = new DBHelper(this);
            SQLiteDatabase db = dbHelper_.getWritableDatabase();
            Log.d(LOG_TAG, "--- Clear jsontable: ---");
            int clearCount = db.delete("jsontable", null, null);
            Log.d(LOG_TAG, "deleted rows count = " + clearCount);
            dbHelper_.close();

        }
        */
    }
}
