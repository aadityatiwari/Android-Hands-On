package com.aadityatiwari.myandroidtutorialproject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InternalData extends Activity implements OnClickListener {
	EditText etSharedData;
	TextView tvDataResults;
	FileOutputStream fos;
	String InternalDataFileName = "MyInternalDataFile";

	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpreferences);
		setUpvariables();
	}

	private void setUpvariables() {
		Button save = (Button) findViewById(R.id.bSavePrefs);
		Button load = (Button) findViewById(R.id.bLoadPrefs);
		etSharedData = (EditText) findViewById(R.id.etSharedPrefs);
		tvDataResults = (TextView) findViewById(R.id.tvLoadSharedPrefs);
		save.setOnClickListener(this);
		load.setOnClickListener(this);

		try {
			fos = openFileOutput(InternalDataFileName, Context.MODE_PRIVATE);
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.bSavePrefs:
			String stringData = etSharedData.getText().toString();

			// File f = new File(InternalDataFileName);
			// try {
			// fos = new FileOutputStream(f);
			// fos.close();
			// } catch (FileNotFoundException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// } catch (IOException e) {
			// e.printStackTrace();
			// }

			try {
				fos = openFileOutput(InternalDataFileName, Context.MODE_PRIVATE);
				fos.write(stringData.getBytes());
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		case R.id.bLoadPrefs:
			FileInputStream fis = null;
			String collected = null;
			try {
				fis = openFileInput(InternalDataFileName);
				byte[] dataArray = new byte[fis.available()];
				while (fis.read(dataArray) != -1) {
					collected = new String(dataArray);

				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					fis.close();
					tvDataResults.setText(collected);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			break;

		}
	};
}
