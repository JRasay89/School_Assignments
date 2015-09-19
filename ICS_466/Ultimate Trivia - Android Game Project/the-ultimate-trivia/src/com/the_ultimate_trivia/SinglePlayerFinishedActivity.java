package com.the_ultimate_trivia;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class SinglePlayerFinishedActivity extends Activity implements OnClickListener{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_play_finish);
		View returnButton = findViewById(R.id.end_quiz_button);
		returnButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		this.finish();
		
	}

}
