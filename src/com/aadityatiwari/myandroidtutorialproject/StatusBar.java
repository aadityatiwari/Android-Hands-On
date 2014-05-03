package com.aadityatiwari.myandroidtutorialproject;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StatusBar extends Activity implements OnClickListener {

	NotificationManager nm;
	static final int uniqueID = 1648495445;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statusbar);
		Button stat = (Button) findViewById(R.id.bStatusBar);
		stat.setOnClickListener(this);
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		nm.cancel(uniqueID);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, StatusBar.class);
		PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
		String date = new SimpleDateFormat("MM/yyyy").format(new Date());
		String body = "I'm learning how to create Android application and it's very exciting -"
				+ date;
		String title = "Aaditya Tiwari";
		InputStream is = getResources().openRawResource(R.drawable.lightning48);
		Bitmap icon = BitmapFactory.decodeStream(is);
		Notification noti = new Notification.Builder(this)
				.setContentTitle(title).setContentText(body)
				.setSmallIcon(R.drawable.lightning).setLargeIcon(icon)
				.setContentIntent(pi).getNotification();
		noti.defaults = Notification.DEFAULT_ALL;
		nm.notify(uniqueID, noti);
		finish();

	}

}
