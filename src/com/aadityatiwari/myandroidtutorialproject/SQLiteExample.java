package com.aadityatiwari.myandroidtutorialproject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQLiteExample extends Activity implements OnClickListener {

	Button sqlUpdate, sqlView, sqlModify, sqlGetInfo, sqlDelete;
	EditText sqlName, sqlHotness, sqlRow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqliteexample);

		sqlName = (EditText) findViewById(R.id.etSQLName);
		sqlHotness = (EditText) findViewById(R.id.etSQLHotness);
		sqlUpdate = (Button) findViewById(R.id.bSQLUpdate);
		sqlView = (Button) findViewById(R.id.bSQLopenView);

		sqlView.setOnClickListener(this);
		sqlUpdate.setOnClickListener(this);

		sqlRow = (EditText) findViewById(R.id.etSQLrowInfo);

		sqlModify = (Button) findViewById(R.id.bSQLmodify);
		sqlGetInfo = (Button) findViewById(R.id.bgetInfo);
		sqlDelete = (Button) findViewById(R.id.bSQLdelete);
		sqlDelete.setOnClickListener(this);
		sqlModify.setOnClickListener(this);
		sqlGetInfo.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bSQLUpdate:
			boolean didItWork = true;
			try {
				String name = sqlName.getText().toString();
				String hotness = sqlHotness.getText().toString();

				HotOrNot entry = new HotOrNot(SQLiteExample.this);
				entry.open();
				entry.createEntry(name, hotness);
				entry.close();
			} catch (Exception e) {
				didItWork = false;
				String exceptionString = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Exception occured!");
				TextView tv = new TextView(this);
				tv.setText(exceptionString);
				d.setContentView(tv);
				d.show();

			} finally {
				if (didItWork) {
					Dialog d = new Dialog(this);
					d.setTitle("Oh Yeah !!");
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.setContentView(tv);
					d.show();
				}
			}

			break;

		case R.id.bSQLopenView:
			Intent i = new Intent(
					"com.aadityatiwari.myandroidtutorialproject.SQLVIEW");
			startActivity(i);
			break;

		case R.id.bgetInfo:

			try {
				String s = sqlRow.getText().toString();
				long l = Long.parseLong(s);

				HotOrNot honInstance = new HotOrNot(this);
				honInstance.open();
				String returnedName = honInstance.getName(l);
				String returnedHotness = honInstance.getHotness(l);
				honInstance.close();

				sqlName.setText(returnedName);
				sqlHotness.setText(returnedHotness);
			} catch (Exception e1) {

				String error = e1.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Dang it!");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			}

			break;

		case R.id.bSQLmodify:

			try {

				String s1 = sqlRow.getText().toString();
				long l1 = Long.parseLong(s1);

				String name = sqlName.getText().toString();
				String hotness = sqlHotness.getText().toString();

				HotOrNot honModify = new HotOrNot(SQLiteExample.this);
				honModify.open();
				honModify.updateEntry(l1, name, hotness);
				honModify.close();
			} catch (Exception e) {

				String exceptionString = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Exception occured!");
				TextView tv = new TextView(this);
				tv.setText(exceptionString);
				d.setContentView(tv);
				d.show();

			}

			break;

		case R.id.bSQLdelete:

			try {
				String s2 = sqlRow.getText().toString();
				long l2 = Long.parseLong(s2);

				HotOrNot honDelete = new HotOrNot(SQLiteExample.this);
				honDelete.open();
				honDelete.deleteEntry(l2);
				honDelete.close();
			} catch (Exception e) {

				String exceptionString = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Exception occured!");
				TextView tv = new TextView(this);
				tv.setText(exceptionString);
				d.setContentView(tv);
				d.show();

			}
			break;
		}

	}

}
