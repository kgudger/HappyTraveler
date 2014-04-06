package com.example.happytraveler;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Context;
import android.content.Intent;

public class HappyTraveler extends Activity {
	
	Button driver, rider; //the two buttons

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy_traveler);
        addListenersOnButtons(); //Sets buttons to respond to clicks
	}

	public void addListenersOnButtons(){
		
		final Context context = this;
		
		driver = (Button) findViewById(R.id.driver_button);
		rider = (Button) findViewById(R.id.rider_button);
		
		driver.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view){
				
				Intent intent = new Intent(context, IHaveARide.class);
				startActivity(intent);
			}
		});
		
		rider.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view){
				
				Intent intent = new Intent(context, INeedARide.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.happy_traveler, menu);
		return true;
	}

}