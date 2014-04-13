package com.aadityatiwari.myandroidtutorialproject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyMenu extends ListActivity {

	String[] classes = { "SecondMapActivity", "FirstMapActivity", "Accelerate", "SQLiteExample",
			"ExternalData", "InternalData", "SharedPrefs", "Flipper",
			"SimpleBrowser", "Tabs", "Slider", "SoundStuff",
			"MyGraphicsSurface", "MyGraphics", "RelativeLayoutGetData",
			"Camera", "Email", "TextPlay", "Splash", "StartingPoint" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		/* Full Screen Window block--BEGINS */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		/* Full Screen Window block --ENDS */
		setListAdapter(new ArrayAdapter<String>(MyMenu.this,
				android.R.layout.simple_list_item_1, classes));

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String clickedPositionClassName = classes[position];
		String packageName = "com.aadityatiwari.myandroidtutorialproject.";
		try {
			Class ourClass = Class.forName(packageName
					+ clickedPositionClassName);
			Intent ourIntent = new Intent(MyMenu.this, ourClass);
			startActivity(ourIntent);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater blowUp = getMenuInflater();
		blowUp.inflate(R.menu.test_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		// return super.onOptionsItemSelected(item);
		switch (item.getItemId()) {

		case R.id.aboutUs:
			Intent i1 = new Intent(
					"com.aadityatiwari.myandroidtutorialproject.ABOUT");
			startActivity(i1);
			break;
		case R.id.preferences:
			Intent i2 = new Intent(
					"com.aadityatiwari.myandroidtutorialproject.PREFS");
			startActivity(i2);
			break;
		case R.id.exit:
			finish();

		}
		return false;
	}

}
