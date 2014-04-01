package com.aadityatiwari.myandroidtutorialproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

public class MyAnimationView extends View {

	Bitmap gBall;
	float changingY;
	Typeface font;

	public MyAnimationView(Context context) {
		super(context);
		// gBall = BitmapFactory.decodeResource(getResources(),);
		gBall = BitmapFactory.decodeResource(getResources(),
				R.drawable.red_ball);
		changingY = 0;
		font = Typeface.createFromAsset(context.getAssets(), "densmore.ttf");
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);

		
		Paint textPaint = new Paint();
		textPaint.setARGB(30, 5, 5, 5);
		textPaint.setTextAlign(Align.CENTER);
		textPaint.setTextSize(50);
		textPaint.setTypeface(font);
		canvas.drawText("The Doors : Big fan!", canvas.getWidth() / 2, 200, textPaint);
		
		float left = (canvas.getWidth() - gBall.getWidth()) / 2;
		canvas.drawBitmap(gBall, left, changingY, null);

		if (changingY < canvas.getHeight()) {
			changingY += 10;
		} else {
			changingY = 0;
		}

		Rect middleRect = new Rect();
		middleRect.set(0, 400, canvas.getWidth(), 550);
		Paint ourBlue = new Paint();
		ourBlue.setColor(Color.BLUE);
		canvas.drawRect(middleRect, ourBlue);
		// To invalidate the whole view, call invalidate() method
		invalidate();

	}

}
