package com.aadityatiwari.myandroidtutorialproject;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class HttpExample extends Activity {
	TextView httpStuff;
	HttpClient client;
	JSONObject json;
	final static String LOG_TAG = "<aaditya>";

	final static String URL = "https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.httpexamplelayout);
		httpStuff = (TextView) findViewById(R.id.tvHttp);
		//System.setProperty("twitter4j.http.useSSL", "true");
		client = new DefaultHttpClient();

		// Call MyInnerAsyncReadClass to fetch last tweet only after first
		// checking to see if there is a network connection
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

		if (networkInfo != null && networkInfo.isConnected()) {
			new MyInnerAsyncReadClass().execute("text");
		} else {
			Log.e(LOG_TAG, "No network connection available.");
		}

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
			// Toast.makeText(HttpExample.this, "error", Toast.LENGTH_SHORT)
			// .show();
			Log.e(LOG_TAG, "Error occured!");
			return null;
		}

	}

	public class MyInnerAsyncReadClass extends
			AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub

			try {
				json = lastTweet("aadityatiwari");
				return json.getString(params[0]);
			} catch (IOException | JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			// super.onPostExecute(result);
			httpStuff.setText(result);
		}
	}

}
