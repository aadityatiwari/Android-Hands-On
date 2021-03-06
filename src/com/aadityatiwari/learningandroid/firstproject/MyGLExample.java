package com.aadityatiwari.learningandroid.firstproject;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class MyGLExample extends Activity {

	GLSurfaceView glSurface;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		glSurface = new GLSurfaceView(this);
		glSurface.setRenderer(new MyGLRendererEx());
		setContentView(glSurface);
	}

	@Override
	protected void onPause() {
		super.onPause();
		glSurface.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		glSurface.onResume();
	}
}
