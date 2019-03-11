package com.k2v.jsonsqliteexample;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.view.View.OnClickListener;

/**
 * Created by Vladislav Kozlov <k2v.akosa@gmail.com>
 */
public class TextViewActivity extends Activity implements OnClickListener {

    TextView textName;
    //TextView textPrice;
    private DBHelper dbHelper_;
    public final String LOG_TAG = "Logs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	
        super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.text_view);
        View clearButton = findViewById(R.id.clear_button);
        clearButton.setOnClickListener(this);

        dbHelper_ = new DBHelper(this);

        textName = (TextView)findViewById(R.id.textName);
        //textPrice = (TextView)findViewById(R.id.textPrice);

        //store the text in variable
        String name = getIntent().getExtras().getString("name");
        //String price = getIntent().getExtras().getString("price");
        //display text
        textName.setText(name);
        //textPrice.setText(name);
    }

    @Override
    public void onClick (View v){

        if(v.getId() == R.id.clear_button) {

            dbHelper_ = new DBHelper(this);
            SQLiteDatabase db = dbHelper_.getWritableDatabase();
            Log.d(LOG_TAG, "--- Clear jsontable: ---");
            int clearCount = db.delete("jsontable", null, null);
            Log.d(LOG_TAG, "deleted rows count = " + clearCount);
            dbHelper_.close();

            //Intent intent = new Intent(TextViewActivity.this, MainActivity.class);
            //startActivity(intent);
        }
    }
}
