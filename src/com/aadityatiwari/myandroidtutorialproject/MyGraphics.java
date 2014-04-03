package com.aadityatiwari.myandroidtutorialproject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class MyGraphics extends Activity {

	MyAnimationView ourView;
	WakeLock wL; // Used to lock the screen after few seconds to save battery
					// power

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// wake-lock
		PowerManager pM = (PowerManager) getSystemService(Context.POWER_SERVICE);
		WakeLock wl = pM
				.newWakeLock(PowerManager.FULL_WAKE_LOCK, "wakeLockTag");
		super.onCreate(savedInstanceState);
		wl.acquire();
		ourView = new MyAnimationView(this);
		setContentView(ourView);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		wL.release();
	}

}
