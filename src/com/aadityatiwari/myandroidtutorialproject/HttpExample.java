package com.aadityatiwari.myandroidtutorialproject;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class HttpExample extends Activity {
	TextView httpStuff;
	HttpClient client;

	final static String URL = "https://api.twitter.com/1/statuses/user_timeline.json?screen_name=";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.httpexamplelayout);
		httpStuff = (TextView) findViewById(R.id.tvHttp);

		// client = new DefaultHttpClient();
		/*
		 * HttpGetMethodExample test = new HttpGetMethodExample(); String
		 * returnedData; try { returnedData = test.getInternetData();
		 * System.out.println(returnedData); httpStuff.setText(returnedData); }
		 * catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
	}

	public JSONObject lastTweet(String username)
			throws ClientProtocolException, IOException, JSONException {
		StringBuilder url = new StringBuilder(URL);
		url.append(username);

		HttpGet get = new HttpGet(url.toString());
		HttpResponse r = client.execute(get);
		int status = r.getStatusLine().getStatusCode();

		if (status == 200) {
			HttpEntity e = r.getEntity();
			String data = EntityUtils.toString(e);
			JSONArray timeline = new JSONArray(data);
			// Zero index will return most recent tweet.
			JSONObject last = timeline.getJSONObject(0);
			return last;
		} else {
			Toast.makeText(getBaseContext(), "error", )
			return null;
		}

	}

}
