package com.aadityatiwari.myandroidtutorialproject;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Splash extends Activity {
	MediaPlayer testSong;
	@Override
	protected void onCreate(Bundle AadityaLovesBacon) {
		// TODO Auto-generated method stub
		super.onCreate(AadityaLovesBacon);
		setContentView(R.layout.splash);
		testSong = MediaPlayer.create(Splash.this, R.raw.winged);
		testSong.start();
		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					//String intentName = "com.aadityatiwari.myandroidtutorialproject.STARTINGPOINT";
					String intentName = "com.aadityatiwari.myandroidtutorialproject.MENU";
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
		testSong.release();
		finish();
	}
}
