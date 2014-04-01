package com.aadityatiwari.myandroidtutorialproject;

import android.app.Activity;
import android.os.Bundle;
import android.os.PowerManager.WakeLock;

public class MyGraphics extends Activity {

	MyAnimationView ourView;
	WakeLock wL;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
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
