package com.k2v.jsonsqliteexample;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import java.util.ArrayList;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.k2v.jsonsqliteexample.models.dao.DBHelper;
import com.k2v.jsonsqliteexample.models.http.HttpManger;
import com.k2v.jsonsqliteexample.models.json.JsonParser;

/**
 * Created by Vladislav Kozlov <k2v.akosa@gmail.com>
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String dbName_;
    private String tableName_;
    private String cryptoTicker_;
    private String lastTraide_;
    private String itemName_;
    private DBHelper dbHelper_;
    private ProgressBar pb_;
    private ArrayList<String> tickerList_;
    private String url_;
    private Fetcher fetcher_;
    private Boolean fetcherFlag_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        View parseButton = findViewById(R.id.parse_button);
        parseButton.setOnClickListener(this);
        View showButton = findViewById(R.id.show_button);
        showButton.setOnClickListener(this);
        View exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(this);
        pb_ = (ProgressBar) findViewById(R.id.progressBar);

        url_ = getString(R.string.url);
        dbName_ = getString(R.string.db_name);
        tableName_ = getString(R.string.table_name);
        cryptoTicker_ = getString(R.string.cryptoticker);
        lastTraide_ = getString(R.string.lasttraide);
        itemName_ = getString(R.string.item_name);
        DBHelper.DB_NAME = dbName_;
        DBHelper.TABLE_NAME = tableName_;
        DBHelper.CRYPTOTICKER = cryptoTicker_;
        DBHelper.lASTTRAIDE = lastTraide_;

        dbHelper_ = new DBHelper(this);
        SQLiteDatabase db = dbHelper_.getWritableDatabase();//DB connect
        dbHelper_.onCreate(db);//Create table if not exists
        db.delete(tableName_, null, null);//Delete data from table
        dbHelper_.close();
        fetcher_ = new Fetcher();
        fetcherFlag_ = false;
    }

    public void onClick(View v) {
        if(v.getId() == R.id.parse_button) {
            if(!fetcherFlag_) {
                fetcher_.execute(url_);
                fetcherFlag_ = true;
            }
        }
        if(v.getId() == R.id.show_button) {
            Intent intent = new Intent(MainActivity.this,SQLiteQueryActivity.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.exit_button) {
            finish();
        }
    }

    public void dbAdapter(String json){
        tickerList_ = JsonParser.Parse(json, itemName_);
        //insert data from tickerList_
        dbHelper_ = new DBHelper(this);
        ContentValues contVal = new ContentValues();
        SQLiteDatabase db = dbHelper_.getWritableDatabase();
        String ticker;
        String last;
        for (int i = 0; i < tickerList_.size() - 1; i += 2) {
            ticker = tickerList_.get(i);
            last = tickerList_.get(i + 1);
            contVal.put(cryptoTicker_, ticker);
            contVal.put(lastTraide_, last);
            db.insert(tableName_, null, contVal);
        }
        dbHelper_.close();
    }

    //AsyncTask
    private class Fetcher extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            pb_.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            String data = HttpManger.getData(params[0]);
            return data;
        }

        @Override
        protected void onPostExecute(String str) {
            dbAdapter(str);
            pb_.setVisibility(View.INVISIBLE);
        }
    }
}
