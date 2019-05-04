package com.k2v.jsonsqliteexample;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import java.util.ArrayList;
import android.widget.ListView;
import java.util.HashMap;
import android.widget.SimpleAdapter;


/**
 * Created by Vladislav Kozlov <k2v.akosa@gmail.com>
 */
public class SQLiteQueryActivity extends Activity {

    public ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
    public HashMap<String, String> map;

    private DBHelper dbHelper_;
    private ListView lv_;
    private String tableName_;
    private String cryptoTicker_;
    private String lastTraide_;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        lv_ = (ListView) findViewById(R.id.listView);
        tableName_ = getString(R.string.table_name);
        cryptoTicker_ = getString(R.string.cryptoticker);
        lastTraide_ = getString(R.string.lasttraide);
        dbHelper_ = new DBHelper(this);
        SQLiteDatabase db = dbHelper_.getWritableDatabase();
        Cursor cursor = db.query(tableName_, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {

            int nameColIndex = cursor.getColumnIndex(cryptoTicker_);
            int priceColIndex = cursor.getColumnIndex(lastTraide_);

            do {

                map = new HashMap<String, String>();
                map.put("ticker", cursor.getString(nameColIndex));
                map.put("last", cursor.getString(priceColIndex));
                arrayList.add(map);

            } while (cursor.moveToNext());
        } else
        cursor.close();
        dbHelper_.close();

        SimpleAdapter adapter = new SimpleAdapter(this, arrayList, android.R.layout.simple_list_item_2,
                new String[]{"ticker", "last"},
                new int[]{android.R.id.text1, android.R.id.text2});
        lv_.setAdapter(adapter);
    }
}
