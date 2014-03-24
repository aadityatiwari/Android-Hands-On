package com.aadityatiwari.myandroidtutorialproject;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class TextPlay extends Activity implements View.OnClickListener {

	Button checkCommandButton;
	ToggleButton toggleButton;
	EditText inputEditText;
	TextView displayTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text);
		initVariables();
		toggleButton.setOnClickListener(this);
		checkCommandButton.setOnClickListener(this);

	}

	private void initVariables() {
		checkCommandButton = (Button) findViewById(R.id.bResults);
		toggleButton = (ToggleButton) findViewById(R.id.tbPassword);
		inputEditText = (EditText) findViewById(R.id.etCommands);
		displayTextView = (TextView) findViewById(R.id.tvResults);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.bResults:
			/* checkCommandButton OnClickListener section -- BEGINS */
			String check = inputEditText.getText().toString();
			if (check.equals("left")) {
				displayTextView.setGravity(Gravity.LEFT);
			} else if (check.equals("center")) {
				displayTextView.setGravity(Gravity.CENTER);
			} else if (check.equals("right")) {
				displayTextView.setGravity(Gravity.RIGHT);
			} else if (check.equals("blue")) {
				displayTextView.setGravity(Gravity.CENTER);
			} else if (check.equals("random")) {
				Random r = new Random();
				displayTextView.setText("< Random >");
				displayTextView.setTextSize(r.nextInt(75));
				displayTextView.setTextColor(Color.rgb(r.nextInt(255),
						r.nextInt(255), r.nextInt(255)));
				displayTextView.setGravity(Gravity.CENTER);
				switch (r.nextInt(3)) {
				case 0:
					displayTextView.setGravity(Gravity.LEFT);
					break;
				case 1:
					displayTextView.setGravity(Gravity.CENTER);
					break;
				case 2:
					displayTextView.setGravity(Gravity.RIGHT);
					break;
				default:
				}
			} else {
				displayTextView.setText("invalid");
				displayTextView.setGravity(Gravity.CENTER);
				displayTextView.setTextColor(Color.RED);
			}
			/* checkCommandButton OnClickListener section -- ENDS */
			break;
		case R.id.tbPassword:
			/* toggleButton OnClickListener section -- BEGINS */
			if (toggleButton.isChecked()) {
				inputEditText.setInputType(InputType.TYPE_CLASS_TEXT
						| InputType.TYPE_TEXT_VARIATION_PASSWORD);
			} else {
				inputEditText.setInputType(InputType.TYPE_CLASS_TEXT);
			}
			/* toggleButton OnClickListener section -- ENDS */
			break;
		default:

		}

	}

}
