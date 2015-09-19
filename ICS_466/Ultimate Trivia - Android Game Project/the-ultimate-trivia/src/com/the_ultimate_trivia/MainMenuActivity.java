package com.the_ultimate_trivia;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;

public class MainMenuActivity extends Activity implements OnClickListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainmenu);
		
		View newButton = findViewById(R.id.single_player_button);
		newButton.setOnClickListener(this);
		View aboutButton = findViewById(R.id.multiplayer_button);
		aboutButton.setOnClickListener(this);
		View createButton = findViewById(R.id.create_button);
		createButton.setOnClickListener(this);
		View exitButton = findViewById(R.id.exit_button);
		exitButton.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.single_player_button:
			Intent a = new Intent(this, SinglePlayerMenuActivity.class);
			startActivity(a);
			break;
		case R.id.multiplayer_button:
			Toast display = Toast.makeText(this, "Not Yet Implemented", Toast.LENGTH_SHORT);
			display.show();
			break;
		case R.id.create_button:
			Toast display2 = Toast.makeText(this, "Not Yet Implemented", Toast.LENGTH_SHORT);
			display2.show();
			break;
		case R.id.exit_button:
			finish();
		}
	}
	
}
