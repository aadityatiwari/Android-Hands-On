package com.aadityatiwari.learningandroid.firstproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class Tabs extends Activity implements OnClickListener {
	TabHost th;
	TextView showWatchResults;
	long start, stop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		th = (TabHost) findViewById(R.id.tabhost);
		Button addNewTab = (Button) findViewById(R.id.bAddTab);
		Button bStart = (Button) findViewById(R.id.bStartWatch);
		Button bStop = (Button) findViewById(R.id.bStopWatch);

		showWatchResults = (TextView) findViewById(R.id.tvShowWatchResults);
		bStart.setOnClickListener(this);
		bStop.setOnClickListener(this);
		addNewTab.setOnClickListener(this);

		// TabHost setup -- BEGINS
		th.setup();
		TabSpec specs = th.newTabSpec("tag1");
		specs.setContent(R.id.tab1);
		specs.setIndicator("Stopwatch");
		th.addTab(specs);

		specs = th.newTabSpec("tag2");
		specs.setContent(R.id.tab2);
		specs.setIndicator("Tab 2");
		th.addTab(specs);

		specs = th.newTabSpec("tag3");
		specs.setContent(R.id.tab3);
		specs.setIndicator("Add a Tab");
		th.addTab(specs);
		// TabHost setup -- ENDS

		start = 0;

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.bAddTab: {
			TabSpec ourSpec = th.newTabSpec("tag1");
			ourSpec.setContent(new TabContentFactory() {

				@Override
				public View createTabContent(String tag) {
					// TODO Auto-generated method stub
					TextView tv = new TextView(Tabs.this);
					tv.setText("A new Tab has been created");
					return tv;
				}
			});
			ourSpec.setIndicator("New tab");
			th.addTab(ourSpec);
			break;
		}
		case R.id.bStartWatch: {
			start = System.currentTimeMillis();
			break;
		}
		case R.id.bStopWatch: {
			stop = System.currentTimeMillis();
			if (start != 0) {
				long result = stop - start;
				int millis = (int) result;
				int seconds = millis / 1000;
				int minutes = seconds / 60;
				int hour = minutes / 60;
				millis = millis % 1000;
				seconds = seconds % 60;
				minutes = minutes % 60;

				showWatchResults.setText("Time elapsed::: " + hour + " hours, "
						+ minutes + " minutes, " + seconds + " seconds, and "
						+ millis + " milli-seconds.");
			}

			break;
		}
		}
	}

}
