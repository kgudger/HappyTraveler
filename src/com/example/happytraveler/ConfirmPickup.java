package com.example.happytraveler;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RatingBar;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;

public class ConfirmPickup extends Activity{
	Button confirm;
	//KEN::: I need you to implement these variables, I put them in the xml for display
	//you have to get them from the server, and it has to do with the previous page
	//I have hardcoded them for now.  -Andrew
	String name = "Leta Block";
	float rating = (float)3.0;
	String location = "111 One Lane";
	TextView displayName, displayLocation;
	RatingBar displayRating;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_pickup);
        displayRiderInformation();
        addListenerOnButton();
	}
	
	private void displayRiderInformation(){
		displayName = (TextView) findViewById(R.id.riderName);
		displayLocation = (TextView) findViewById(R.id.riderLocation);
		displayRating = (RatingBar) findViewById(R.id.riderRating);
		displayName.setText(name);
		displayLocation.setText(location);
		displayRating.setRating(rating);
	}
	
	private void addListenerOnButton(){
		
		final Context context = this;
		
		confirm = (Button) findViewById(R.id.confirm_button);
		
		confirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view){
				
				Intent intent = new Intent(context, OngoingRide.class);
				startActivity(intent);
			}
		});
	}
}