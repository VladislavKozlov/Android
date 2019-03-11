package com.k2v.jsonsqliteexample;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import java.util.List;


public class HttpActivity extends AppCompatActivity {

    private ProgressBar pb;
    private ListView lv;
    private List<String> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);

        pb = (ProgressBar) findViewById(R.id.progressBar);
        lv = (ListView) findViewById(R.id.listView);
        
        /*fetch data from the web*/
        Fetcher fetcher = new Fetcher();
        fetcher.execute("https://poloniex.com/public?command=returnTicker");

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = (String) lv.getItemAtPosition(position);
                //String price = (String) lv.getItemAtPosition(position);
                //Intent intent = new Intent(HttpActivity.this,TextViewActivity.class);
                Intent intent = new Intent(HttpActivity.this,SQLiteInsertActivity.class);
                intent.putExtra("name", name);
                //intent.putExtra("price", price);
                startActivity(intent);
            }
        });
    }

    public void ContactAdapter(String json){
        contactList = JsonParser.Parse(json);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contactList);
        System.out.println("contactList = ");
        System.out.println(contactList);
        lv.setAdapter(adapter);
    }

    //AsyncTask
   private class Fetcher extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute() {

            pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            String data = HttpManger.getData(params[0]);
            //String data = " ";
            //String data = params[0];
            return data;
        }

        @Override
        protected void onPostExecute(String str) {
            ContactAdapter(str);
            pb.setVisibility(View.INVISIBLE);
        }
    }
}

