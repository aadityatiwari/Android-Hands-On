package com.aadityatiwari.myandroidtutorialproject;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;

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

		// Getting info about the widget that Launched this Activity
		Intent i = getIntent();
		Bundle extras = i.getExtras();
		if (extras != null) {
			awID = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
					AppWidgetManager.INVALID_APPWIDGET_ID);
		} else {
			finish();
		}
		awm = AppWidgetManager.getInstance(c);
	}

	@Override
	public void onClick(View v) {
		String textFromWidgetConfigTextView = info.getText().toString();
		RemoteViews views = new RemoteViews(c.getPackageCodePath(),
				R.layout.first_widget);
		// Set textFromWidgetConfigTextView into the widget TextView field
		views.setTextViewText(R.id.tvConfigInput, textFromWidgetConfigTextView);
		awm.updateAppWidget(awID, views);

		finish();

	}

}
