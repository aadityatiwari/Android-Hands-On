package com.aadityatiwari.myandroidtutorialproject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class SimpleBrowser extends Activity implements OnClickListener {

	EditText url;
	WebView browser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simplebrowser);

		browser = (WebView) findViewById(R.id.wvBrowser);

		browser.getSettings().setJavaScriptEnabled(true);

		// Next LOC sets the WebView to load pages in overview mode, that is,
		// zooms
		// out the content to fit on screen by width.
		browser.getSettings().setLoadWithOverviewMode(true);
		browser.getSettings().setUseWideViewPort(true);

		browser.setWebViewClient(new OurViewClient());

		try {
			browser.loadUrl("http://aadityatiwari.com");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		url = (EditText) findViewById(R.id.etUrl);

		Button go = (Button) findViewById(R.id.bGo);
		Button back = (Button) findViewById(R.id.bBack);
		Button refresh = (Button) findViewById(R.id.bRefresh);
		Button forward = (Button) findViewById(R.id.bForward);
		Button clearHistory = (Button) findViewById(R.id.bHistory);

		go.setOnClickListener(this);
		back.setOnClickListener(this);
		refresh.setOnClickListener(this);
		forward.setOnClickListener(this);
		clearHistory.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.bGo:
			String theWebsite = url.getText().toString();
			browser.loadUrl(theWebsite);

			// Code to hide keyboard after using an EditText- BEGINS
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromInputMethod(url.getWindowToken(), 0);
			// Code to hide keyboard after using an EditText- ENDS

			break;
		case R.id.bBack:
			if (browser.canGoBack())
				browser.goBack();
			break;
		case R.id.bForward:
			if (browser.canGoForward())
				browser.goForward();
			break;
		case R.id.bRefresh:
			browser.reload();
			break;
		case R.id.bHistory:
			browser.clearHistory();
			break;
		}
	}

}
