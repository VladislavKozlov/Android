package com.k2v.jsonsqliteexample;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;
import android.widget.ListView;
import java.util.HashMap;
import android.view.View.OnClickListener;
import android.widget.SimpleAdapter;


/**
 * Created by Vladislav Kozlov <k2v.akosa@gmail.com>
 */
public class SQLiteQueryActivity extends Activity implements OnClickListener {

    public ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
    public HashMap<String, String> map;

    private DBHelper dbHelper_;
    private ListView lv_;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        View exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(this);
        lv_ = (ListView) findViewById(R.id.listView);
        dbHelper_ = new DBHelper(this);
        //DB connect
        SQLiteDatabase db = dbHelper_.getWritableDatabase();
        Cursor cursor = db.query("jsontable", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {

            int nameColIndex = cursor.getColumnIndex("companyname");
            int priceColIndex = cursor.getColumnIndex("latestprice");

            do {

                map = new HashMap<String, String>();
                map.put("name", cursor.getString(nameColIndex));
                map.put("price", cursor.getString(priceColIndex));
                arrayList.add(map);

            } while (cursor.moveToNext());
        } else
        cursor.close();
        dbHelper_.close();

        SimpleAdapter adapter = new SimpleAdapter(this, arrayList, android.R.layout.simple_list_item_2,
                new String[]{"name", "price"},
                new int[]{android.R.id.text1, android.R.id.text2});
        lv_.setAdapter(adapter);
    }

    public void onClick(View v) {

        if(v.getId() == R.id.exit_button) {

            finish();
        }
    }
}
