package com.example.happytraveler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Context;

/**
 * The waiting page the user sees after submitted a ride request.  Contains the option to cancel the 
 * request.
 * @author Andrew H. Pometta
 *
 */

public class RiderWait extends Activity{
	
	Button cancel;  //the button cancelling the request

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_wait);
        addListenersOnWaitButton();
	}
	
	public void addListenersOnWaitButton(){
		final Context context = this;
		
		cancel = (Button) findViewById(R.id.cancel_button);
		
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view){
				
				Intent intent = new Intent(context, HappyTraveler.class);
				startActivity(intent);
			}
		});
	}
}
