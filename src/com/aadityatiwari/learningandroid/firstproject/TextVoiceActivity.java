package com.aadityatiwari.learningandroid.firstproject;

import java.util.Locale;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TextVoiceActivity extends Activity implements OnClickListener {

	static final String[] texts = { "Hi, Aaditya!", "What's up Gangsters!",
			"Are you kidding me?", "You are awesome!", "I Like you" };

	TextToSpeech textToSpeech;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.textvoice);
		Button b = (Button) findViewById(R.id.bTextToVoice);
		b.setOnClickListener(this);
		textToSpeech = new TextToSpeech(TextVoiceActivity.this,
				new OnInitListener() {
					@Override
					public void onInit(int status) {
						if (status != TextToSpeech.ERROR) {
							textToSpeech.setLanguage(Locale.US);
						}
					}
				});
	}

	@Override
	public void onClick(View v) {
		Random r = new Random();
		String random = texts[r.nextInt(5)];
		textToSpeech.speak(random, TextToSpeech.QUEUE_FLUSH, null);
	}

	@Override
	protected void onPause() {
		if (textToSpeech != null) {
			textToSpeech.stop();
			textToSpeech.shutdown();
		}
		super.onPause();
	}

}