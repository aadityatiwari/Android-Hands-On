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
import org.apache.http.client.methods.HttpGet;
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
import android.widget.ArrayAdapter;

import com.aadityatiwari.myandroidtutorialproject.twitter.Authenticated;
import com.aadityatiwari.myandroidtutorialproject.twitter.Tweet;
import com.aadityatiwari.myandroidtutorialproject.twitter.TwitterList;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

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
		final static String CONSUMER_KEY = "";
		final static String CONSUMER_SECRET = "";
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
			TwitterList twits = jsonToTwitterList(result);

			// Lets write the results to the console as well
			for (Tweet tweet : twits) {
				Log.i(LOG_TAG, tweet.getText());
			}

			// send the tweets to the adapter for rendering
			ArrayAdapter<Tweet> adapter = new ArrayAdapter<Tweet>(activity,
					android.R.layout.simple_list_item_1, twits);
			setListAdapter(adapter);

		}

		// converts a string of JSON data into a Twitter object
		private TwitterList jsonToTwitterList(String result) {
			TwitterList twits = null;
			if (result != null && !result.isEmpty()) {
				try {
					Gson gson = new Gson();
					twits = gson.fromJson(result, TwitterList.class);
				} catch (IllegalStateException ex) {

				}
			}
			return twits;
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
				Authenticated auth = jsonToAuthenticated(rawAuthorization);

				// Applications should verify that the value associated with the
				// token_type key of the returned object is bearer

				if (auth != null && auth.getToken_type().equals("bearer")) {

					// Step 3: Authenticate API requests and include an
					// Authorization header with the value of Bearer <>
					HttpGet httpGet = new HttpGet(TwitterStreamURL + userName);

					// construct a normal HTTPS request and include an
					// Authorization
					// header with the value of Bearer <>
					httpGet.setHeader("Authorization",
							"Bearer " + auth.getAccess_token());
					httpGet.setHeader("Content-Type", "application/json");

					// update the results with the body of the response
					results = getResponseBody(httpGet);

				}

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

		// convert a JSON authentication object into an Authenticated object
		private Authenticated jsonToAuthenticated(String rawAuthorization) {
			Authenticated auth = null;
			if (rawAuthorization != null && !rawAuthorization.isEmpty()) {
				try {
					Gson gson = new Gson();
					auth = gson.fromJson(rawAuthorization, Authenticated.class);
				} catch (JsonSyntaxException e) {
					// e.printStackTrace();
				} catch (IllegalStateException e) {
					// e.printStackTrace();
				}
			}

			return auth;
		}
	}
}
