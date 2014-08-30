package com.aadityatiwari.learningandroid.firstproject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
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

			new LoadSomeStuff().execute(InternalDataFileName);

			break;

		}
	};

	public class LoadSomeStuff extends AsyncTask<String, Integer, String> {

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = new ProgressDialog(InternalData.this);
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			dialog.setMax(100);
			dialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			FileInputStream fis = null;
			String collected = null;

			// After ProgressDialog creation in onPreExecute(), we'll increment
			// the progress by calling the publishProgress and then wait for
			// 100ms before calling again. Once loop is processed We should
			// dismiss the dialog.
			// ProgressDialog logic --BEGINS
			for (int i = 0; i < 20; i++) {
				publishProgress(5);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			dialog.dismiss();
			// ProgressDialog logic --ENDS

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
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return collected;
		}

		@Override
		protected void onProgressUpdate(Integer... progress) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(progress);
			dialog.incrementProgressBy(progress[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			// Set the textView label with result string
			tvDataResults.setText(result);
		}

	}
}
