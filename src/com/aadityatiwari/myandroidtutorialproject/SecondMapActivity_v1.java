package com.aadityatiwari.myandroidtutorialproject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class SecondMapActivity_v1 extends MapActivity implements
		LocationListener {

	MapView map;
	long start;
	long stop;
	List<Overlay> overlayList;
	MapController controller;
	MyLocationOverlay compass;

	int x, y;
	GeoPoint touchedPoint;
	Drawable d;
	int lat;
	int longi;
	LocationManager lm;
	String towers;

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

		d = getResources().getDrawable(R.drawable.android);

		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria crit = new Criteria();

		towers = lm.getBestProvider(crit, false);
		Location location = lm.getLastKnownLocation(towers);

		if (location != null) {

			lat = (int) (location.getLatitude() * 1E6);
			longi = (int) (location.getLongitude() * 1E6);

			GeoPoint ourLocation = new GeoPoint(lat, longi);
			OverlayItem overlayItem = new OverlayItem(ourLocation,
					"2-Ist String", "2-2nd String");
			PinpointItemizedOverlay pinpoint = new PinpointItemizedOverlay(d,
					SecondMapActivity_v1.this);
			pinpoint.insertPinpoint(overlayItem);
			overlayList.add(pinpoint);
		} else {
			Toast.makeText(getBaseContext(), "Couldn't get provider",
					Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		compass.disableCompass();
		super.onPause();
		lm.removeUpdates(this);
		finish();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		compass.enableCompass();
		super.onResume();
		lm.requestLocationUpdates(towers, 500, 1, this);
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
				alert.setTitle("Pick an option");
				alert.setMessage("AlertDialog Message");

				alert.setButton("Pinpoint",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {

								OverlayItem overlayItem = new OverlayItem(
										touchedPoint, "2-Ist String",
										"2-2nd String");
								PinpointItemizedOverlay pinpoint = new PinpointItemizedOverlay(
										d, SecondMapActivity_v1.this);
								pinpoint.insertPinpoint(overlayItem);
								overlayList.add(pinpoint);

							}
						});

				alert.setButton2("Get Address",
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

									if (address.size() > 0) {
										String display = "";
										for (int i = 0; i < address.get(0)
												.getMaxAddressLineIndex(); i++) {
											display += address.get(0)
													.getAddressLine(i) + "\n";
										}
										Toast t3 = Toast.makeText(
												getBaseContext(), display,
												Toast.LENGTH_LONG);
										t3.show();
									}

								} catch (IOException e) {

									e.printStackTrace();

								} finally {

								}

							}
						});

				alert.setButton3("Toggle view",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub

								if (map.isSatellite()) {
									map.setSatellite(false);
									map.setStreetView(true);
								}

								else {
									map.setStreetView(false);
									map.setSatellite(true);

								}

							}
						});

				alert.show();

				return true;

			}
			return false;
		}
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		lat = (int) (location.getLatitude() * 1E6);
		longi = (int) (location.getLongitude() * 1E6);
		
		GeoPoint ourLocation = new GeoPoint(lat, longi);
		OverlayItem overlayItem = new OverlayItem(ourLocation,
				"2-Ist String", "2-2nd String");
		PinpointItemizedOverlay pinpoint = new PinpointItemizedOverlay(d,
				SecondMapActivity_v1.this);
		pinpoint.insertPinpoint(overlayItem);
		overlayList.add(pinpoint);
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	// Overlay inner class -- ENDS
}
