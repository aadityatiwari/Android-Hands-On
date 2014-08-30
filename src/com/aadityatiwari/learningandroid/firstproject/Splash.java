package com.aadityatiwari.learningandroid.firstproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Splash extends Activity {
	MediaPlayer testSong;

	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		setContentView(R.layout.splash);
		testSong = MediaPlayer.create(Splash.this, R.raw.winged);

		SharedPreferences getPrefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());
		boolean music = getPrefs.getBoolean("musicCheckbox", false);
		if (music) {
			testSong.start();
		}

		Thread timer = new Thread() {
			@Override
			public void run() {
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					// String intentName =
					// "com.aadityatiwari.myandroidtutorialproject.STARTINGPOINT";
					String intentName = "com.aadityatiwari.myandroidtutorialproject.MYMENU";
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
