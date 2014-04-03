package com.aadityatiwari.myandroidtutorialproject;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class MyGraphicsSurface extends Activity implements OnTouchListener {

	MyAnimationSurfaceView myAnimationSurfaceView;
	float x, y, sX, sY, fX, fY; // S => start ; f => finish
	float dX, dY, aniX, aniY, scaledX, scaledY;
	Bitmap redBall, aSign, bSign;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		myAnimationSurfaceView = new MyAnimationSurfaceView(this);
		myAnimationSurfaceView.setOnTouchListener(this);
		x = 0;
		y = 0;
		sX = 0;
		sY = 0;
		fX = 0;
		fY = 0;
		dX = dY = aniX = aniY = scaledX = scaledY = 0;
		redBall = BitmapFactory.decodeResource(getResources(),
				R.drawable.red_ball);
		aSign = BitmapFactory.decodeResource(getResources(), R.drawable.a);
		bSign = BitmapFactory.decodeResource(getResources(), R.drawable.b);
		setContentView(myAnimationSurfaceView);
	}

	@Override
	protected void onPause() {
		super.onPause();
		myAnimationSurfaceView.pause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		myAnimationSurfaceView.resume();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		x = event.getX();
		y = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			sX = event.getX();
			sY = event.getY();
			dX = dY = aniX = aniY = scaledX = scaledY = fX = fY = 0;
			break;
		case MotionEvent.ACTION_UP:
			fX = event.getX();
			fY = event.getY();
			dX = fX - sX;
			dY = fY - sY;
			scaledX = dX / 30;
			scaledY = dY / 30;
			x = y = 0;
			break;
		}
		return true;
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

				if (x != 0 && y != 0) {
					canvas.drawBitmap(redBall, (x - redBall.getWidth() / 2),
							(y - redBall.getHeight() / 2), null);
				}

				if (sX != 0 && sY != 0) {
					canvas.drawBitmap(aSign, sX - (aSign.getWidth() / 2), sY
							- (aSign.getHeight() / 2), null);
				}
				if (fX != 0 && fY != 0) {

					canvas.drawBitmap(redBall, (fX - redBall.getWidth() / 2)
							- aniX, (fY - redBall.getHeight() / 2) - aniY, null);
					canvas.drawBitmap(bSign, fX - (bSign.getWidth() / 2), fY
							- (bSign.getHeight() / 2), null);
				}
				aniX = aniX + scaledX;
				aniY = aniY + scaledY;

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
