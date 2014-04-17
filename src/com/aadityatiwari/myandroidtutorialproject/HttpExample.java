package com.aadityatiwari.myandroidtutorialproject;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HttpExample extends Activity {
	TextView httpStuff;
	HttpClient client;

	final static String URL = "http://www.aadityatiwari.com";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.httpexamplelayout);
		httpStuff = (TextView) findViewById(R.id.tvHttp);
		// client = new DefaultHttpClient();
		HttpGetMethodExample test = new HttpGetMethodExample();
		String returnedData;
		try {
			returnedData = test.getInternetData();
			httpStuff.setText(returnedData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
