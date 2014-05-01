package com.aadityatiwari.myandroidtutorialproject;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class MyGLTriangleEx {

	private float vertices[] = { 0f, 1f, // p0
			1.0f, -1.0f, // p1
			-1.0f, -1.0f // p2
	};

	private FloatBuffer vertBuff, colorBuff;

	public MyGLTriangleEx() {

		ByteBuffer bBuff = ByteBuffer.allocateDirect(vertices.length * 4);
		bBuff.order(ByteOrder.nativeOrder());
		vertBuff = bBuff.asFloatBuffer();
	}

}
