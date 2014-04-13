package com.aadityatiwari.myandroidtutorialproject;

import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class SecondMapActivity extends MapActivity {

	MapView map;
	long start;
	long stop;
	List<Overlay> overlayList;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.main);
		map = (MapView) findViewById(R.id.mvMain);
		map.setBuiltInZoomControls(true);

		Touchy t = new Touchy();
		overlayList = map.getOverlays();
		overlayList.add(t);

	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	// Overlay inner class -- BEGINS
	class Touchy extends Overlay {

		@SuppressWarnings("deprecation")
		@Override
		public boolean onTouchEvent(MotionEvent e, MapView m) {
			// TODO Auto-generated method stub
			// return super.onTouchEvent(e, m);
			if (e.getAction() == MotionEvent.ACTION_DOWN) {
				start = e.getEventTime();

			}
			if (e.getAction() == MotionEvent.ACTION_UP) {
				stop = e.getEventTime();

			}

			if (stop - start > 1500) {
				AlertDialog alert = new AlertDialog.Builder(
						SecondMapActivity.this).create();
				alert.setTitle("AlertDialog Title: Pick an option");
				alert.setMessage("AlertDialog Message");

				alert.setButton("AlertDialog Button: Pinpoint",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub

							}
						});

				alert.setButton2("AlertDialog Button: Address",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub

							}
						});

				alert.setButton3("AlertDialog Button: Option 3",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub

							}
						});

				alert.show();

			}
			return false;
		}
	}

	// Overlay inner class -- ENDS
}
