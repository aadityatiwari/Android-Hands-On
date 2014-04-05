package com.aadityatiwari.myandroidtutorialproject;

import java.io.File;

import com.thenewboston.travis.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ExternalData extends Activity implements OnClickListener,
		OnItemSelectedListener {

	TextView canWrite, canRead;
	String environmentExternalState;
	boolean canR, canW;
	Spinner spinner;
	String[] paths = { "Music", "Pictures", "Download" };
	File path = null;
	File file = null;
	EditText saveFile;
	Button confirm, save;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.externaldata);
		canWrite = (TextView) findViewById(R.id.tvCanWrite);
		canRead = (TextView) findViewById(R.id.tvCanRead);
		confirm = (Button) findViewById(R.id.bConfirmSaveAs);
		save = (Button) findViewById(R.id.bSaveFile);
		saveFile = (EditText) findViewById(R.id.etSaveAs);
		confirm.setOnClickListener(this);
		save.setOnClickListener(this);

		checkState();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				ExternalData.this, android.R.layout.simple_spinner_item, paths);
		spinner = (Spinner) findViewById(R.id.spinner1);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
	}

	private void checkState() {
		// TODO Auto-generated method stub
		environmentExternalState = Environment.getExternalStorageState();

		if (environmentExternalState.equals(Environment.MEDIA_MOUNTED)) {
			// read and write
			canWrite.setText("true");
			canRead.setText("true");
			canW = canR = true;

		} else if (environmentExternalState
				.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
			// read but can't write
			canWrite.setText("false");
			canRead.setText("true");
			canW = false;
			canR = true;

		} else {
			canWrite.setText("false");
			canRead.setText("false");
			canW = canR = false;
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bSaveFile:

			break;

		case R.id.bConfirmSaveAs:
			save.setVisibility(View.VISIBLE);
			break;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		int pos = spinner.getSelectedItemPosition();
		switch (pos) {
		case 0:
			path = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
			break;
		case 1:
			path = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			break;
		case 2:
			path = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

}
