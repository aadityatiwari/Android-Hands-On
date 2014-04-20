package com.aadityatiwari.myandroidtutorialproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WeatherRelatedActivity extends Activity implements OnClickListener {

	static final String baseURL = "http://api.openweathermap.org/data/2.5/weather?q=";
	static final String QUERY_CONCAT_OPERATOR = "&";
	static final String RESULT_MODE = "mode=xml";
	TextView tv;
	EditText city, state;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.weather);
		Button b = (Button) findViewById(R.id.bWeather);
		tv = (TextView) findViewById(R.id.tvWeather);
		city = (EditText) findViewById(R.id.etCity);
		state = (EditText) findViewById(R.id.etState);
		b.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		String c = city.getText().toString();
		String s = state.getText().toString();

		StringBuilder URL = new StringBuilder(baseURL);
		URL.append(c + "," + s + QUERY_CONCAT_OPERATOR + RESULT_MODE);
		String fullUrl = URL.toString();
		
		

	}

}
