package com.aadityatiwari.learningandroid.firstproject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aadityatiwari.learningandroid.firstproject.openweathermap.HandlingWeatherXMLStuff;

public class WeatherRelatedActivity extends Activity implements OnClickListener {

	static final String baseURL = "http://api.openweathermap.org/data/2.5/weather?q=";
	static final String QUERY_CONCAT_OPERATOR = "&";
	static final String RESULT_MODE = "mode=xml";
	static final String UNIT_METRICS = "units=metric";
	TextView tv;
	EditText city, country;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.weather);
		Button b = (Button) findViewById(R.id.bWeather);
		tv = (TextView) findViewById(R.id.tvWeather);
		city = (EditText) findViewById(R.id.etCity);
		country = (EditText) findViewById(R.id.etCountry);
		b.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		String ci = city.getText().toString();
		String co = country.getText().toString();

		StringBuilder URL = new StringBuilder(baseURL);
		URL.append(ci + "," + co + QUERY_CONCAT_OPERATOR + RESULT_MODE
				+ QUERY_CONCAT_OPERATOR + UNIT_METRICS);
		String fullUrl = URL.toString();

		new FetchTemperatureTask().execute(fullUrl);
	}

	private class FetchTemperatureTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			String result = null;

			if (params != null && params.length > 0) {
				result = getTemperatueData(params[0]);
			}
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			// super.onPostExecute(result);
			tv.setText(result);
		}

		private String getTemperatueData(String fullUrl) {
			String result = null;
			try {
				URL website = new URL(fullUrl);
				// Getting XMLreader to parse data
				SAXParserFactory spf = SAXParserFactory.newInstance();
				SAXParser sp = spf.newSAXParser();
				XMLReader xr = sp.getXMLReader();

				HandlingWeatherXMLStuff xmlStuff = new HandlingWeatherXMLStuff();
				xr.setContentHandler(xmlStuff);
				xr.parse(new InputSource(website.openStream()));

				result = xmlStuff.getInformation();

			} catch (MalformedURLException e) {
				tv.setText("MalformedURLException");
			} catch (ParserConfigurationException e) {
				tv.setText("ParserConfigurationException");
			} catch (SAXException e) {
				tv.setText("SAXException");
			} catch (IOException e) {
				tv.setText("IOException");
			}

			return result;
		}

	}

}
