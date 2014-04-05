package com.aadityatiwari.myandroidtutorialproject;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPrefs extends Activity implements OnClickListener {
	EditText etSharedData;
	TextView tvDataResults;
	// Think of SharedPreferences as Folder which can holds multiple files
	SharedPreferences sharedPreferencesData;
	public static final String sharedPreferencesDataFileName = "MySharedPreferencesDataFile";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpreferences);
		initVariables();
		sharedPreferencesData = getSharedPreferences(
				sharedPreferencesDataFileName, 0);
	}

	private void initVariables() {
		// TODO Auto-generated method stub
		Button save = (Button) findViewById(R.id.bSavePrefs);
		Button load = (Button) findViewById(R.id.bLoadPrefs);
		etSharedData = (EditText) findViewById(R.id.etSharedPrefs);
		tvDataResults = (TextView) findViewById(R.id.tvLoadSharedPrefs);
		save.setOnClickListener(this);
		load.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.bSavePrefs:
			String stringData = etSharedData.getText().toString();
			SharedPreferences.Editor editor = sharedPreferencesData.edit();
			editor.putString("sharedString", stringData);
			editor.commit();

			break;
		case R.id.bLoadPrefs:
			// Calling again below to reload the saved settings
			sharedPreferencesData = getSharedPreferences(
					sharedPreferencesDataFileName, 0);
			String returnedData = sharedPreferencesData.getString(
					"sharedString", "Couldn't load data");
			tvDataResults.setText(returnedData);
			break;

		}
	}

}
