package com.aadityatiwari.myandroidtutorialproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RelativeLayoutGetData extends Activity implements OnClickListener {

	EditText etSendData;
	Button bSA, bSAFR;
	TextView tvGetText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.relativelayoutgetdata);
		initialize();
	}

	private void initialize() {
		etSendData = (EditText) findViewById(R.id.etSendData);
		tvGetText = (TextView) findViewById(R.id.tvGetText);
		bSA = (Button) findViewById(R.id.bSA);
		bSAFR = (Button) findViewById(R.id.bSAFR);
		bSA.setOnClickListener(this);
		bSAFR.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bSA:
			String etSendDataValueFromRelativeLayoutGetDataClassOnClickOfbSA = etSendData
					.getText().toString();
			Bundle bundleSA = new Bundle();
			bundleSA.putString("etSendDataValueFromRelativeLayoutGetDataClass",
					etSendDataValueFromRelativeLayoutGetDataClassOnClickOfbSA);
			Intent aSA = new Intent(RelativeLayoutGetData.this,
					LinearLayoutSendData.class);
			aSA.putExtras(bundleSA);
			startActivity(aSA);
			break;

		case R.id.bSAFR:

			String etSendDataValueFromRelativeLayoutGetDataClassOnClickOfbSAFR = etSendData
					.getText().toString();
			Bundle bundleSAFR = new Bundle();
			bundleSAFR
					.putString("etSendDataValueFromRelativeLayoutGetDataClass",
							etSendDataValueFromRelativeLayoutGetDataClassOnClickOfbSAFR);

			Intent aSAFR = new Intent(RelativeLayoutGetData.this,
					LinearLayoutSendData.class);
			aSAFR.putExtras(bundleSAFR);
			startActivityForResult(aSAFR, 0);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			String s = bundle
					.getString("returnTextDataFromLinearLayoutSendDataClass");
			tvGetText.setText(s);

		}
	}

}
