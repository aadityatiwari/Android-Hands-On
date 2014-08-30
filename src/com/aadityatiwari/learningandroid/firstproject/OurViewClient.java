package com.aadityatiwari.learningandroid.firstproject;

import android.webkit.WebViewClient;

public class OurViewClient extends WebViewClient {

	@Override
	public boolean shouldOverrideUrlLoading(android.webkit.WebView view,
			String url) {
		view.loadUrl(url);
		return true;

	};

}
