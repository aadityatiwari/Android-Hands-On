package com.aadityatiwari.myandroidtutorialproject;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class PinpointItemizedOverlay extends ItemizedOverlay<OverlayItem> {

	private ArrayList<OverlayItem> pinpoints = new ArrayList<OverlayItem>();
	private Context context;

	public PinpointItemizedOverlay(Drawable arg0) {
		super(boundCenter(arg0));
		// TODO Auto-generated constructor stub
	}

	public PinpointItemizedOverlay(Drawable m, Context context) {
		this(m);
		this.context = context;
	}

	@Override
	protected OverlayItem createItem(int arg0) {
		// TODO Auto-generated method stub
		return pinpoints.get(arg0);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return pinpoints.size();
	}

	public void insertPinpoint(OverlayItem item) {
		pinpoints.add(item);
		this.populate();
	}

}
