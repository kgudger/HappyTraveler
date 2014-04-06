package com.example.happytraveler;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Context;
import android.content.Intent;

public class OngoingRide extends Activity{

	Button finish;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongoing_ride);
        addListenersOnFinishButton();
	}
	
	public void addListenersOnFinishButton(){
		final Context context = this;
		
		finish = (Button) findViewById(R.id.continue_ratings_button);
		
		finish.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view){
				
				Intent intent = new Intent(context, ratingsPage.class);
				startActivity(intent);
			}
		});
	}

}
