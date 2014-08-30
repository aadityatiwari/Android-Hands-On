package com.aadityatiwari.learningandroid.firstproject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SQLView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlview);

		TextView tvSQLinfo = (TextView) findViewById(R.id.tvSQLinfo);
		HotOrNot info = new HotOrNot(this);
		
		info.open();
		String stringData = info.getData();		
		info.close();
		
		tvSQLinfo.setText(stringData);
		
	}

}
