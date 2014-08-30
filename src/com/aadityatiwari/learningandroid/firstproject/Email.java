package com.aadityatiwari.learningandroid.firstproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Email extends Activity implements View.OnClickListener {

	EditText etEmails, etIntro, etName, etThings, etActions, etOutro;
	String emailsText, introText, nameText, thingsText, actionText, outroText;
	Button bSendEmail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.email);
		initializeVariables();
		bSendEmail.setOnClickListener(this);
	}

	private void initializeVariables() {
		etEmails = (EditText) findViewById(R.id.etEmails);
		etIntro = (EditText) findViewById(R.id.etIntro);
		etName = (EditText) findViewById(R.id.etName);
		etThings = (EditText) findViewById(R.id.etThings);
		etActions = (EditText) findViewById(R.id.etActions);
		etOutro = (EditText) findViewById(R.id.etOutro);

		bSendEmail = (Button) findViewById(R.id.bSendEmail);
	}

	@Override
	public void onClick(View v) {
		fetchEditTextValuesIntoStrings();
		String emailAddress[] = { emailsText };
		String message = " This is the message being sent from the Android application running on AVD emulator and is build via the android tutorials";

		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, emailAddress);
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
				"Email subject");
		emailIntent.setType("plain/text");
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
		startActivity(emailIntent);
	}

	private void fetchEditTextValuesIntoStrings() {
		emailsText = etEmails.getText().toString();
		introText = etIntro.getText().toString();
		nameText = etName.getText().toString();
		thingsText = etThings.getText().toString();
		actionText = etActions.getText().toString();
		outroText = etOutro.getText().toString();
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}

}
