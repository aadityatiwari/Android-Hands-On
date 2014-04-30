package com.aadityatiwari.myandroidtutorialproject;

import java.util.Random;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MyFirstAppWidget extends AppWidgetProvider {

	//
	// @Override
	// public void onUpdate(Context context, AppWidgetManager appWidgetManager,
	// int[] appWidgetIds) {
	// super.onUpdate(context, appWidgetManager, appWidgetIds);
	// Random r = new Random();
	// int randomInt = r.nextInt(1000000);
	// String rand = String.valueOf(randomInt);
	//
	// final int N = appWidgetIds.length;
	//
	// for (int i = 0; i < N; i++) {
	// int awID = appWidgetIds[i];
	// RemoteViews v = new RemoteViews(context.getPackageName(),
	// R.layout.first_widget);
	// v.setTextViewText(R.id.tvwidgetUpdate, rand);
	// appWidgetManager.updateAppWidget(awID, v);
	// }
	//
	// }

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {

		ComponentName thisWidget = new ComponentName(context,
				MyFirstAppWidget.class);
		int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
		for (int widgetId : allWidgetIds) {
			int number = (new Random().nextInt(10000));

			RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
					R.layout.first_widget);
			Log.w("MyFirstAppWidget", String.valueOf(number));
			// Set the text
			remoteViews.setTextViewText(R.id.tvwidgetUpdate,
					String.valueOf(number));

			// Register an onClickListener
			Intent intent = new Intent(context, MyFirstAppWidget.class);

			intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
			intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

			PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
					0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			remoteViews
					.setOnClickPendingIntent(R.id.bwidgetOpen, pendingIntent);
			appWidgetManager.updateAppWidget(widgetId, remoteViews);
		}

	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		super.onDeleted(context, appWidgetIds);
		Toast.makeText(context, "FirstApp Widget deleted", Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
		super.onEnabled(context);
	}

	@Override
	public void onDisabled(Context context) {
		// TODO Auto-generated method stub
		super.onDisabled(context);
	}

}
