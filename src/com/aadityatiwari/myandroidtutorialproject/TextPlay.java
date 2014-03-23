package com.aadityatiwari.myandroidtutorialproject;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class TextPlay extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text);

		Button checkCommandButton = (Button) findViewById(R.id.bResults);
		final ToggleButton toggleButton = (ToggleButton) findViewById(R.id.tbPassword);
		final EditText inputEditText = (EditText) findViewById(R.id.etCommands);
		TextView displayTextView = (TextView) findViewById(R.id.tvResults);
		toggleButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(toggleButton.isChecked()){
					inputEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
				}
				else {
					inputEditText.setInputType(InputType.TYPE_CLASS_TEXT);
				}
			}
		});

	}

}
