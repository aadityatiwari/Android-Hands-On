package com.aadityatiwari.myandroidtutorialproject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.MotionEvent;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

public class SecondMapActivity_v1 extends MapActivity {

	MapView map;
	long start;
	long stop;
	List<Overlay> overlayList;
	MapController controller;
	MyLocationOverlay compass;

	int x, y;
	GeoPoint touchedPoint;
	Drawable d;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.secondmap);
		map = (MapView) findViewById(R.id.mvMain);
		map.setBuiltInZoomControls(true);

		Touchy t = new Touchy();
		overlayList = map.getOverlays();
		overlayList.add(t);

		compass = new MyLocationOverlay(SecondMapActivity_v1.this, map);
		overlayList.add(compass);

		controller = map.getController();
		GeoPoint point = new GeoPoint(51643234, 7848593);
		controller.animateTo(point);
		controller.setZoom(6);

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		compass.disableCompass();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		compass.enableCompass();
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
				x = (int) e.getX();
				y = (int) e.getY();
				touchedPoint = map.getProjection().fromPixels(x, y);

			}
			if (e.getAction() == MotionEvent.ACTION_UP) {
				stop = e.getEventTime();

			}

			if (stop - start > 1500) {
				AlertDialog alert = new AlertDialog.Builder(
						SecondMapActivity_v1.this).create();
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

								Geocoder geocoder = new Geocoder(
										getBaseContext(), Locale.getDefault());

								try {
									List<Address> address = geocoder.getFromLocation(
											touchedPoint.getLatitudeE6() / 1E6,
											touchedPoint.getLongitudeE6() / 1E6,
											1);

								} catch (IOException e) {

									e.printStackTrace();

								} finally {

								}

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

				return true;

			}
			return false;
		}
	}

	// Overlay inner class -- ENDS
}
