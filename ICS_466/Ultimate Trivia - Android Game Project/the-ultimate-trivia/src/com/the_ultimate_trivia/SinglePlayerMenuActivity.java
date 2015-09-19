package com.the_ultimate_trivia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class SinglePlayerMenuActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_player_menu);
		View timeTrialButton = findViewById(R.id.time_trial_button);
		timeTrialButton.setOnClickListener(this);
		View survivalButton = findViewById(R.id.survival_button);
		survivalButton.setOnClickListener(this);
		View leaderBoardButton = findViewById(R.id.leaderboard_button);
		leaderBoardButton.setOnClickListener(this);
		View playButton = findViewById(R.id.play_button);
		playButton.setOnClickListener(this);
		View exitButton = findViewById(R.id.exit_button);
		exitButton.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.time_trial_button:
			Toast display = Toast.makeText(this , "Not Yet Implemented", Toast.LENGTH_SHORT);
			display.show();
			break;
		case R.id.survival_button:
			Toast display2 = Toast.makeText(this , "Not Yet Implemented", Toast.LENGTH_SHORT);
			display2.show();
			break;
		case R.id.leaderboard_button:
			Toast display3 = Toast.makeText(this , "Not Yet Implemented", Toast.LENGTH_SHORT);
			display3.show();
			break;
		case R.id.play_button:
			Intent a = new Intent(this, SinglePlayerActivity.class);
			startActivity(a);
			break;
		case R.id.exit_button:
			finish();
		}
		
	}

}
