package com.aadityatiwari.myandroidtutorialproject;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Accelerate extends Activity implements SensorEventListener {
	float x, y, sensorX, sensorY;
	Bitmap ball;
	SensorManager sm;
	MyAnimationSurfaceView ourSurfaceView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		sm = (SensorManager) getSystemService(SENSOR_SERVICE);
		if (sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
			Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
		}

		ball = BitmapFactory.decodeResource(getResources(),
				R.drawable.green_ball);
		x = y = sensorX = sensorY = 0;
		ourSurfaceView = new MyAnimationSurfaceView(this);
		ourSurfaceView.resume();
		setContentView(ourSurfaceView);

	}

	@Override
	public void onSensorChanged(SensorEvent e) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(20);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sensorX = e.values[0];
		sensorY = e.values[1];
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		sm.unregisterListener(this);
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
				float centerX = canvas.getWidth() / 2;
				float centerY = canvas.getHeight() / 2;
				canvas.drawBitmap(ball, centerX + sensorX * 20, centerY
						+ sensorY * 20, null);

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
