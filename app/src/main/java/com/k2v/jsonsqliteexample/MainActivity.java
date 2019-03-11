package com.k2v.jsonsqliteexample;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


/**
 * Created by Vladislav Kozlov <k2v.akosa@gmail.com>
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		View parseButton = findViewById(R.id.parse_button);
        parseButton.setOnClickListener(this);

		View exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(this);

    }

	@Override
	protected void onDestroy() {
  		super.onDestroy();
 	}

 	@Override
 	protected void onStop() {
  		super.onStop();
	}
	
	public void onClick(View v) {

        if(v.getId() == R.id.parse_button) {

            Intent intent = new Intent(MainActivity.this, SQLiteInsertActivity.class);
            //Intent intent = new Intent(MainActivity.this, HttpActivity.class);
            startActivity(intent);
        }

        if(v.getId() == R.id.exit_button) {

            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
