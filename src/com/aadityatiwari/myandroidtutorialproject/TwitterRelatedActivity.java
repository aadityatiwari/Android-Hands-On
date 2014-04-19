package com.aadityatiwari.myandroidtutorialproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

import android.app.ListActivity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

/**
 * Demonstrates how to use a twitter application keys to access a user's
 * timeline
 */
public class TwitterRelatedActivity extends ListActivity {
	private ListActivity activity;
	final static String UserName = "aadityatiwari";
	final static String LOG_TAG = "TwitterRelatedActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		activity = this;

		downloadTweets();
	}

	// download twitter timeline after first checking to see if there is a
	// network connection
	public void downloadTweets() {
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

		if (networkInfo != null && networkInfo.isConnected()) {
			new DownloadTwitterTask().execute(UserName);
		} else {
			Log.e(LOG_TAG, "No network connection available.");
		}
	}

	// Uses an AsyncTask to download a Twitter user's timeline
	private class DownloadTwitterTask extends AsyncTask<String, Void, String> {
		final static String CONSUMER_KEY = "MY CONSUMER KEY";
		final static String CONSUMER_SECRET = "MY CONSUMER SECRET";
		final static String TwitterTokenURL = "https://api.twitter.com/oauth2/token";
		final static String TwitterStreamURL = "https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=";

		@Override
		protected String doInBackground(String... userNames) {
			String result = null;

			if (userNames.length > 0) {
				result = getTwitterStream(userNames[0]);
			}
			return result;
		}

		// onPostExecute convert the JSON results into a Twitter object (which
		// is an Array list of tweets
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}

		private String getTwitterStream(String userName) {
			String results = null;

			// Step 1: Encode consumer key and secret

			try {
				// URL encode the consumer key and secret
				String urlApiKey = URLEncoder.encode(CONSUMER_KEY, "UTF-8");
				String urlApiSecret = URLEncoder.encode(CONSUMER_SECRET,
						"UTF-8");

				// Concatenate the encoded consumer key, a colon character, and
				// the encoded consumer secret
				String combined = urlApiKey + ":" + urlApiSecret;

				// Base64 encode the string
				String base64Encoded = Base64.encodeToString(
						combined.getBytes(), Base64.NO_WRAP);

				// Step 2: Obtain a bearer token
				HttpPost httpPost = new HttpPost(TwitterTokenURL);
				httpPost.setHeader("Authorization", "Basic " + base64Encoded);
				httpPost.setHeader("Content-Type",
						"application/x-www-form-urlencoded;charset=UTF-8");
				httpPost.setEntity(new StringEntity(
						"grant_type=client_credentials"));

				String rawAuthorization = getResponseBody(httpPost);

			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return results;
		}

		private String getResponseBody(HttpRequestBase request) {
			StringBuilder sb = new StringBuilder();

			try {

				DefaultHttpClient httpClient = new DefaultHttpClient(
						new BasicHttpParams());
				HttpResponse response = httpClient.execute(request);
				int statusCode = response.getStatusLine().getStatusCode();
				String reason = response.getStatusLine().getReasonPhrase();

				if (statusCode == 200) {

					HttpEntity entity = response.getEntity();
					InputStream inputSteam = entity.getContent();

					BufferedReader bReader = new BufferedReader(
							new InputStreamReader(inputSteam, "UTF-8"), 8);
					String line = null;
					String nl = System.getProperty("line.separator");
					while ((line = bReader.readLine()) != null) {
						sb.append(line + nl);
					}

				} else {
					sb.append(reason);
				}

			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return sb.toString();

		}
	}
}
