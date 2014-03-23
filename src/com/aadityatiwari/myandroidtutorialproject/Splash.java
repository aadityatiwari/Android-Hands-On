package com.aadityatiwari.myandroidtutorialproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle AadityaLovesBacon) {
		// TODO Auto-generated method stub
		super.onCreate(AadityaLovesBacon);
		setContentView(R.layout.splash);
		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					String intentName = "com.aadityatiwari.myandroidtutorialproject.STARTINGPOINT";
					Intent openStartingPointIntent = new Intent(intentName);
					startActivity(openStartingPointIntent);
				}
			}

		};

		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}
