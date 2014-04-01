package com.aadityatiwari.myandroidtutorialproject;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class MyGraphicsSurface extends Activity implements OnTouchListener {

	MyAnimationSurfaceView ourSurfaceView;
	float x, y, sX, sY, fX, fY;
	Bitmap bmS, bmF, test;
	float dX, dY, aniX, aniY, scaledX, scaledY;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ourSurfaceView = new MyAnimationSurfaceView(this);
		ourSurfaceView.setOnTouchListener(this);
		x = 0;
		y = 0;
		sX = 0;
		sY = 0;
		fX = 0;
		fY = 0;
		setContentView(ourSurfaceView);
	}

	@Override
	protected void onPause() {
		super.onPause();
		ourSurfaceView.pause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		ourSurfaceView.resume();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		x = event.getX();
		y = event.getY();
		return false;
	}
	
	public class MyAnimationSurfaceView extends SurfaceView implements Runnable {

		SurfaceHolder ourSurfaceHolder;
		Thread thread = null;
		boolean isThreadRunning = false;

		public MyAnimationSurfaceView(Context context) {
			super(context);
			ourSurfaceHolder = getHolder();
		}

		public void run() {

			while (isThreadRunning) {
				if (!ourSurfaceHolder.getSurface().isValid()) {
					continue;
				}
				Canvas canvas = ourSurfaceHolder.lockCanvas();
				canvas.drawRGB(240, 240, 240);
				ourSurfaceHolder.unlockCanvasAndPost(canvas);
			}
		}

		public void pause() {
			isThreadRunning = false;
			while (true) {
				try {
					thread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		public void resume() {
			isThreadRunning = true;
			thread = new Thread(this);
			thread.start();
		}

	}
}
