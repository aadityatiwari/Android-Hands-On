package com.aadityatiwari.learningandroid.firstproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartingPoint extends Activity {

	int counter;
	Button addButton;
	Button subtractButton;
	TextView tvTotalCountDisplay;
	String totalLabel = "Your total is : ";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		counter = 0;
		addButton = (Button) findViewById(R.id.bAdd);
		subtractButton = (Button) findViewById(R.id.bSubtract);
		tvTotalCountDisplay = (TextView) findViewById(R.id.tvTotalValueDisplay);

//		AdView ad = (AdView) findViewById(R.id.ad);
//		AdRequest adReq = new AdRequest();
//		ad.loadAd(adReq);

		addButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				counter++;
				tvTotalCountDisplay.setText(totalLabel + counter);
			}
		});

		subtractButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				counter--;
				tvTotalCountDisplay.setText(totalLabel + counter);
			}
		});
	}

	@Override
	public void setContentView(int layoutResID) {
		// TODO Auto-generated method stub
		super.setContentView(layoutResID);
	}
}
