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


/**
 * Created by Vladislav Kozlov <k2v.akosa@gmail.com>
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private DBHelper dbHelper_;
    private ProgressBar pb_;
    private ArrayList<String> contactList_;
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
        url_ = "https://poloniex.com/public?command=returnTicker";
        dbHelper_ = new DBHelper(this);
        SQLiteDatabase db = dbHelper_.getWritableDatabase();
        db.delete("jsontable", null, null);
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

    public void DBAdapter(String json){

        contactList_ = JsonParser.Parse(json);
        //insert data from contactList
        dbHelper_ = new DBHelper(this);
        ContentValues contVal = new ContentValues();
        //DB connect
        SQLiteDatabase db = dbHelper_.getWritableDatabase();
        String companyname;
        String latestprice;

        for (int i = 0; i < contactList_.size() - 1; i += 2) {

            companyname = contactList_.get(i);
            latestprice = contactList_.get(i + 1);
            contVal.put("companyname", companyname);
            contVal.put("latestprice", latestprice);
            db.insert("jsontable", null, contVal);
        }

        dbHelper_.close();
    }

    //AsyncTask
    private class Fetcher extends AsyncTask<String, String, String>{

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
		
            //DBAdapter(str);
			DBAdapter("{'json_array':['companyname1','price1','companyname2','price2','companyname3','price3']}");
            pb_.setVisibility(View.INVISIBLE);
        }
    }
}
