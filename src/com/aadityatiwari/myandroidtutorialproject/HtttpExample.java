package com.aadityatiwari.myandroidtutorialproject;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HtttpExample extends Activity {
	TextView httpStuff;
	HttpClient client;

	final static String URL = "http://aadityatiwari.com";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.httpexamplelayout);
		httpStuff = (TextView) findViewById(R.id.tvHttp);
		client = new DefaultHttpClient();		
	}

}
