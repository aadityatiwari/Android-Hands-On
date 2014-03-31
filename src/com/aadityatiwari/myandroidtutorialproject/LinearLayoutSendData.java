package com.aadityatiwari.myandroidtutorialproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class LinearLayoutSendData extends Activity implements OnClickListener,
		OnCheckedChangeListener {

	TextView tvQuestion, tvSendText;
	RadioButton rA, rB, rC;
	RadioGroup rgOptions;
	Button bReturnData;
	String etSendDataValueFromRelativeLayoutGetDataClass;
	String returnDataText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayoutsenddata);
		initialize();

		SharedPreferences getData = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());
		String et = getData.getString("name", "Aaditya is ...");
		String values = getData.getString("list", "4");

		if (values.contentEquals("1")) {
			tvQuestion.setText(et);
		}

		/*
		 * Bundle bundleReceivedFromRelativeLayoutGetDataActivity = getIntent()
		 * .getExtras(); etSendDataValueFromRelativeLayoutGetDataClass =
		 * bundleReceivedFromRelativeLayoutGetDataActivity
		 * .getString("etSendDataValueFromRelativeLayoutGetDataClass");
		 * tvQuestion.setText(etSendDataValueFromRelativeLayoutGetDataClass);
		 */
	}

	private void initialize() {
		tvQuestion = (TextView) findViewById(R.id.tvQuestion);
		tvSendText = (TextView) findViewById(R.id.tvSendText);
		rA = (RadioButton) findViewById(R.id.rA);
		rB = (RadioButton) findViewById(R.id.rB);
		rC = (RadioButton) findViewById(R.id.rC);
		rgOptions = (RadioGroup) findViewById(R.id.rgOptions);
		bReturnData = (Button) findViewById(R.id.bReturnData);
		bReturnData.setOnClickListener(this);
		rgOptions.setOnCheckedChangeListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent i = new Intent();
		Bundle bundle = new Bundle();
		bundle.putString("returnTextDataFromLinearLayoutSendDataClass",
				returnDataText);
		i.putExtras(bundle);
		setResult(RESULT_OK, i);
		finish();
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {

		case R.id.rA:
			returnDataText = "Radio A !";
			break;
		case R.id.rB:
			returnDataText = "Radio B !";
			break;
		case R.id.rC:
			returnDataText = "Both !";
		}
		tvSendText.setText(returnDataText);
	}

}
