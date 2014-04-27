package com.aadityatiwari.myandroidtutorialproject;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MyFirstAppWidgetConfig extends Activity implements OnClickListener {

	EditText info;
	AppWidgetManager awm;
	Context c;
	int awID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_widgetconfig);
		Button b = (Button) findViewById(R.id.bwidgetconfig);
		b.setOnClickListener(this);
		c = MyFirstAppWidgetConfig.this;
		info = (EditText) findViewById(R.id.etwidgetconfig);

	}

	@Override
	public void onClick(View v) {

	}

}
