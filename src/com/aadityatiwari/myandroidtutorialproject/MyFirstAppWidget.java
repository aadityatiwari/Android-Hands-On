package com.aadityatiwari.myandroidtutorialproject;

import java.util.Random;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MyFirstAppWidget extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		Random r = new Random();
		int randomInt = r.nextInt(100000000);
		String rand = String.valueOf(randomInt);

		final int N = appWidgetIds.length;

		for (int i = 0; i < N; i++) {
			int awID = appWidgetIds[i];
			RemoteViews v = new RemoteViews(context.getPackageName(),
					R.layout.first_widget);
			v.setTextViewText(R.id.tvwidgetUpdate, rand);
			appWidgetManager.updateAppWidget(awID, v);
		}

	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		super.onDeleted(context, appWidgetIds);
		Toast.makeText(context, "Widget deleted", Toast.LENGTH_SHORT).show();
	}
}