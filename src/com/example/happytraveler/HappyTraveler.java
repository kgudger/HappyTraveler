package com.example.happytraveler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class HappyTraveler extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ride_needed_alt_alt);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.happy_traveler, menu);
		return true;
	}
	
	public void buttonPress(View view) {
		Intent clickedButton = new Intent(this, INeedARide.class);
		startActivity(clickedButton);
	}
}
