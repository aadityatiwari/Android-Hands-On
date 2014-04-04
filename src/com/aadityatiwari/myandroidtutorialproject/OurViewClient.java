package com.aadityatiwari.myandroidtutorialproject;

import android.webkit.WebViewClient;

public class OurViewClient extends WebViewClient {

	public boolean shouldOverrideUrlLoading(android.webkit.WebView view,
			String url) {
		view.loadUrl(url);
		return true;

	};

}
