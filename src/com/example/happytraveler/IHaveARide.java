package com.example.happytraveler;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 *
 * @author Brendan
 *
 */
public class IHaveARide extends Activity {
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ihave_aride);
	}


	
	public void submitClick(View v) throws IOException{
		double destinationLat, destinationLong;
		
		EditText locationText = (EditText)findViewById(R.id.editText1);
		String locationStr = locationText.getText().toString();
		EditText zipText = (EditText)findViewById(R.id.editText3);
		String zipStr = zipText.getText().toString();
		EditText stateText = (EditText)findViewById(R.id.editText2);
		String stateStr = stateText.getText().toString();
		Log.w("myApp", Integer.toString((Integer)locationStr.length()));
		Log.w("myApp", Integer.toString((Integer)zipStr.length()));
		Log.w("myApp", Integer.toString((Integer)stateStr.length()));
		Log.w("myApp", "Before if");
		if (locationStr.length() == 0 || zipStr.length() == 0 || stateStr.length() == 0) {
			Toast.makeText(this, "Fields empty", 1).show();
			return;
		}
		Log.w("myApp", "After if");
		
		locationStr = locationStr + " " + stateStr + " " + zipStr;
		
		List<Address> foundGeocode = null;
		// find the addresses  by using getFromLocationName() method with the given address
		Log.w("myApp", "Before Geo");
		foundGeocode = new Geocoder(this).getFromLocationName(locationStr, 1);
		if (foundGeocode.size() == 0) {
			Toast.makeText(this, "Address not found", 1).show();
			return;
		}
		destinationLat = foundGeocode.get(0).getLatitude(); //getting latitude
		destinationLong = foundGeocode.get(0).getLongitude();//getting longitude
		Log.w("myApp", "After Geo");
		 
/****************************************************************************************************
		 // move destinationLat and destinationLong to server
		  
****************************************************************************************************/ 
		 Intent intent = new Intent( this, RiderSelection.class);
		 startActivity(intent);
	}
	
	public void goHome(View v){
		 Intent intent = new Intent(this, HappyTraveler.class);
		 startActivity(intent);
	}
	
	

}
